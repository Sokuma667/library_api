package lei.ibam.library.borrowBook.service;

import jakarta.transaction.Transactional;
import lei.ibam.library.GlobalExeptionHandler.BookAlreadyReturnedException;
import lei.ibam.library.GlobalExeptionHandler.BookNotExistsException;
import lei.ibam.library.GlobalExeptionHandler.BookNotInStockException;
import lei.ibam.library.GlobalExeptionHandler.UserNotExistsExeption;
import lei.ibam.library.book.model.BookEntity;
import lei.ibam.library.book.repository.BookRepository;
import lei.ibam.library.borrowBook.dto.BorrowInputDto;
import lei.ibam.library.borrowBook.dto.BorrowOuputDto;
import lei.ibam.library.borrowBook.model.BorrowedBookEntity;
import lei.ibam.library.borrowBook.repository.BorrowedBookRepository;
import lei.ibam.library.security.JwtUtil;
import lei.ibam.library.user.model.UserEntity;
import lei.ibam.library.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class BorrowedBookService {
    private final BookRepository bookRepository;
    private final BorrowedBookRepository borrowedBookRepository;
    private final UserRepository userRepository ;
    private final JwtUtil jwtUtil;
    public BorrowedBookService (BookRepository bookRepository, BorrowedBookRepository borrowedBookRepository, UserRepository userRepository, JwtUtil jwtUtil){
        this.bookRepository=bookRepository;
        this.borrowedBookRepository = borrowedBookRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;

    }

    @Transactional
    public BorrowedBookEntity borrowBook(BorrowInputDto borrowInputDto,String username) throws BookNotExistsException {
        Optional <BookEntity> bookexists = bookRepository.findById(borrowInputDto.getBookID());
       // Optional <UserEntity> userExists = userRepository.findById(borrowInputDto.getUserID());
        UserEntity user = userRepository.findUserEntityByUserName(username)
                .orElseThrow(() -> new UserNotExistsExeption("Utilisateur non connecté"));


        if(bookexists.isPresent()){

                 BookEntity bookexists1 = bookexists.get();
                 if(bookexists1.getQuantity()>0){


                     bookexists1.setQuantity(bookexists1.getQuantity()- 1);
                     bookRepository.save(bookexists1);

                     BorrowedBookEntity borrowedBook = new BorrowedBookEntity();
                        borrowedBook.setBook(bookexists1);
                        borrowedBook.setUser(user);
                        borrowedBook.setBorrowDate(LocalDateTime.now());
                        borrowedBook.setReturnDate(LocalDateTime.now().plusWeeks(3));
                        borrowedBook.setReturned(false);

                     return borrowedBookRepository.save(borrowedBook);

            }
                 throw new BookNotInStockException("Ce document n'est plus disponible !!!");

        }
            throw new BookNotExistsException("Ce document n'existe pas !!!");

    }


    //Le user peut voir ses documents empruntés
    public List<BorrowOuputDto> getMyBorrowedBooks(String username) {

        UserEntity user = userRepository.findUserEntityByUserName(username)
                .orElseThrow(() -> new UserNotExistsExeption("utiliateur non connecté"));

        List<BorrowOuputDto> borrowedBook = borrowedBookRepository.findByUser(user)
                .stream().map(
                        b->{
                            BorrowOuputDto borrowOuputDto = new BorrowOuputDto();

                            borrowOuputDto.setId(b.getBorrowedBook_id());
                            borrowOuputDto.setFirstName(b.getUser().getFirstName());
                            borrowOuputDto.setLastName(b.getUser().getLastName());
                            borrowOuputDto.setBookName(b.getBook().getName());
                            borrowOuputDto.setBorrowDate(b.getBorrowDate());
                            borrowOuputDto.setReturnDate(b.getReturnDate());
                            borrowOuputDto.setReturned(b.isReturned());

                            return borrowOuputDto;
                        })
                  .toList();


        return borrowedBook;


    }


    //L'admin peut voir tous les emprunts
    public List <BorrowOuputDto> getAllBorrowedBook(){
        return borrowedBookRepository.findAll()
                .stream().map(
                        b->{
                            BorrowOuputDto borrowOuputDto = new BorrowOuputDto();

                            borrowOuputDto.setId(b.getBorrowedBook_id());
                            borrowOuputDto.setFirstName(b.getUser().getFirstName());
                            borrowOuputDto.setLastName(b.getUser().getLastName());
                            borrowOuputDto.setBookName(b.getBook().getName());
                            borrowOuputDto.setBorrowDate(b.getBorrowDate());
                            borrowOuputDto.setReturnDate(b.getReturnDate());
                            borrowOuputDto.setReturned(b.isReturned());

                            return borrowOuputDto;
                        }
                )
                .toList();
    }


    @Transactional
    public Boolean returnBook(Long id){
        BorrowedBookEntity bookFound = borrowedBookRepository.findById(id)
               .orElseThrow(()-> new BookNotExistsException("Ce livre n'existe pas"));

            if(bookFound.isReturned()){

                throw new BookAlreadyReturnedException("Ce livre a déjà été retourné");
            }

                bookFound.setReturned(true);
                borrowedBookRepository.save(bookFound);

                BookEntity bookReturned = bookFound.getBook();
                bookReturned.setQuantity(bookReturned.getQuantity()+1);

                bookRepository.save(bookReturned);

                return true;

    }


}
