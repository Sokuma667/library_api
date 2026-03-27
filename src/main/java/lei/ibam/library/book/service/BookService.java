package lei.ibam.library.book.service;

import jakarta.validation.Valid;
import lei.ibam.library.GlobalExeptionHandler.BookAlreadyExistsException;
import lei.ibam.library.GlobalExeptionHandler.BookCategoryNotExistsException;
import lei.ibam.library.GlobalExeptionHandler.BookNotExistsException;
import lei.ibam.library.book.dto.BookInputDto;
import lei.ibam.library.book.dto.BookOutputDto;
import lei.ibam.library.book.model.BookEntity;
import lei.ibam.library.book.model.Category;
import lei.ibam.library.book.repository.BookRepository;

import lei.ibam.library.view.bookView.model.BookView;
import lei.ibam.library.view.bookView.repository.BookViewRepo;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookViewRepo bookViewRepo;

    public BookService(BookRepository bookRepository, BookViewRepo bookViewRepo) {
        this.bookRepository = bookRepository;
        this.bookViewRepo = bookViewRepo;
    }


    //Creation d'un livre
    public BookEntity create(@Valid BookInputDto bookInputDto) {
        Optional<BookEntity> existingBook = bookRepository.findByNameAndPages(
                bookInputDto.getBookName(), bookInputDto.getBookPages());

       if (existingBook.isPresent()) {
            throw new BookAlreadyExistsException("Le livre existe déjà!!!");
        }



        BookEntity book = new BookEntity();
          book.setName(bookInputDto.getBookName());
          book.setPages(bookInputDto.getBookPages());
          book.setCategory(bookInputDto.getBookCategory());
          book.setAuthor(bookInputDto.getBookAuthor());
          book.setQuantity(bookInputDto.getBookQuantity());

        return bookRepository.save(book);


    }

    //Affichage de livres
    public Page <BookView> getAllBook(Pageable pageable){
        return bookViewRepo.findAll(pageable);
    }

    //Afficher un livre en connaissant son id
    public BookOutputDto getBookById(Long id) {
       BookView bookView = bookViewRepo.findById(id)
                .orElseThrow(() -> new BookNotExistsException("Ce livre n'existe pas"));

        BookOutputDto bookOutputDto = new BookOutputDto();
        bookOutputDto.setBookOutputName(bookView.getName());
        bookOutputDto.setBookOutputPage(bookView.getPages());
        bookOutputDto.setBookOutputAuthor(bookView.getAuthor());
        bookOutputDto.setBookOutputCategory(bookView.getCategory());
        bookOutputDto.setBookOutputQuantity(bookView.getQuantity());

        return bookOutputDto;
    }


    //Mettre les infos d'un livre à jour
    public BookEntity updateBook(Long id,BookInputDto book){
      return bookRepository.findById(id)
              .map(book1 -> {
               book1.setName(book.getBookName());
              book1.setPages(book.getBookPages());
              book1.setAuthor(book.getBookAuthor());
              book1.setCategory(book.getBookCategory());

              boolean bookExists= bookRepository.findByNameAndPages(book1.getName(),book1.getPages()).isPresent();
              if (bookExists){
                  throw new BookAlreadyExistsException("ce livre existe déjà");
              }
                 return bookRepository.save(book1);
              })
              .orElseThrow(()->new BookNotExistsException("Ce livre n'existe pas"));
    }



    //Supprimer un livre dans la bd
    public boolean deleteBook(Long id){

      if(bookRepository.findById(id).isPresent()){
        BookEntity book = bookRepository.findById(id).get();
          bookRepository.delete(book);
          return true;
      }
       return false;
    }


}
