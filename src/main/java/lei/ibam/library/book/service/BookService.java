package lei.ibam.library.book.service;

import jakarta.validation.Valid;
import lei.ibam.library.GlobalExeptionHandler.BookAlreadyExistsException;
import lei.ibam.library.GlobalExeptionHandler.BookNotExistsException;
import lei.ibam.library.book.dto.BookInputDto;
import lei.ibam.library.book.model.BookEntity;
import lei.ibam.library.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

        return bookRepository.save(book);


    }

    //Affichage de livres
    public List<BookEntity> getAllBook(){
        return bookRepository.findAll();
    }

    //Afficher un livre en connaissant son id
    public BookEntity getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotExistsException("Ce livre n'existe pas"));
    }


    //Mettre les infos d'un livre à jour
    public BookEntity updateBook(Long id,BookInputDto book){
      return bookRepository.findById(id)
              .map(book1 -> {
               book1.setName(book.getBookName());
              book1.setPages(book.getBookPages());

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
