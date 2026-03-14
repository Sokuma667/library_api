package lei.ibam.library.book.controller;

import jakarta.validation.Valid;
import lei.ibam.library.book.dto.BookInputDto;
import lei.ibam.library.book.model.BookEntity;
import lei.ibam.library.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookEntity> addNewBook(@Valid @RequestBody BookInputDto book){
       BookEntity newBook=bookService.create(book);
       return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<BookEntity>> getAllBooks(Pageable pageable){
    return new ResponseEntity(bookService.getAllBook(pageable),HttpStatus.OK);
    }   


    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getBookByIds(@PathVariable Long id){
       BookEntity bookById = bookService.getBookById(id);
       return ResponseEntity.ok(bookById);

    }


    @PutMapping("/{id}")
    public ResponseEntity <BookEntity> updateBooks(@PathVariable Long id,@Valid @RequestBody BookInputDto book){
        BookEntity b = bookService.updateBook(id,book);
        return ResponseEntity.ok(b);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooks(@PathVariable Long id){

         if (bookService.deleteBook(id)){
             return new ResponseEntity<>(HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
