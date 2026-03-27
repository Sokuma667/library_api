package lei.ibam.library.book.controller;

import jakarta.validation.Valid;
import lei.ibam.library.book.dto.BookInputDto;
import lei.ibam.library.book.dto.BookOutputDto;
import lei.ibam.library.book.model.BookEntity;
import lei.ibam.library.book.model.Category;
import lei.ibam.library.book.service.BookService;
import lei.ibam.library.view.bookView.model.BookView;
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
    public ResponseEntity<Page<BookOutputDto>> getAllBooks(Pageable pageable){
        Page<BookOutputDto> booksDto = bookService.getAllBook(pageable)
                .map(bookView -> {
                    BookOutputDto bookOutputDto = new BookOutputDto();
                    bookOutputDto.setBookOutputName(bookView.getName());
                    bookOutputDto.setBookOutputPage(bookView.getPages());
                    bookOutputDto.setBookOutputAuthor(bookView.getAuthor());
                    bookOutputDto.setBookOutputCategory(bookView.getCategory());
                    bookOutputDto.setBookOutputQuantity(bookView.getQuantity());
                    return bookOutputDto;
                });
        return ResponseEntity.ok(booksDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookOutputDto> getBookByIds(@PathVariable Long id){
       BookOutputDto bookById = bookService.getBookById(id);
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
