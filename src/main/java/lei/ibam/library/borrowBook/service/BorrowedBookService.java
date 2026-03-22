package lei.ibam.library.borrowBook.service;

import jakarta.transaction.Transactional;
import lei.ibam.library.GlobalExeptionHandler.BookNotExistsException;
import lei.ibam.library.GlobalExeptionHandler.BookNotInStockException;
import lei.ibam.library.GlobalExeptionHandler.UserNotExistsExeption;
import lei.ibam.library.book.model.BookEntity;
import lei.ibam.library.book.repository.BookRepository;
import lei.ibam.library.borrowBook.dto.BorrowInputDto;
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

    public List<BorrowedBookEntity> getMyBorrowedBooks(String username) {

        UserEntity user = userRepository.findUserEntityByUserName(username)
                .orElseThrow(() -> new UserNotExistsExeption("utiliateur non connecté"));

        return borrowedBookRepository.findByUser(user);
    }

}
