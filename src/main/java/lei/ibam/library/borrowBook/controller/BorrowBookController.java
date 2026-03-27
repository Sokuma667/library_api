package lei.ibam.library.borrowBook.controller;

import jakarta.validation.Valid;
import lei.ibam.library.borrowBook.dto.BorrowInputDto;
import lei.ibam.library.borrowBook.dto.BorrowOuputDto;
import lei.ibam.library.borrowBook.model.BorrowedBookEntity;
import lei.ibam.library.borrowBook.repository.BorrowedBookRepository;
import lei.ibam.library.borrowBook.service.BorrowedBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowBookController {
    private final BorrowedBookService borrowedBookService;

    public BorrowBookController(BorrowedBookService borrowedBookService) {
        this.borrowedBookService = borrowedBookService;
    }

    @PostMapping("/borrow")
    public ResponseEntity <BorrowedBookEntity> borrowbook(@Valid @RequestBody BorrowInputDto borrowInputDto,Authentication authentication){

        String username = authentication.getName();

        BorrowedBookEntity book = borrowedBookService.borrowBook(borrowInputDto,username);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/myBorrowedBooks")
    @ResponseBody
    public List<BorrowOuputDto> getMyBooks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return borrowedBookService.getMyBorrowedBooks(username);
    }

    /*
     * @GetMapping("/my")
     * public List<BorrowedBookEntity> getMyBorrowedBooks(Authentication authentication) {
     *
     *     String username = authentication.getName();
     *
     *     return borrowedBookService.getMyBorrowedBooks(username);
     * }
     */


    @PutMapping("/return/update/{id}")
    public ResponseEntity <Void> returnBook(@PathVariable Long id){
        borrowedBookService.returnBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //l'admin peut voir tous les emprunts
    @GetMapping("/borrowedBooks")
    public ResponseEntity<List<BorrowOuputDto>> getAllBorrowedBooks(){
        return new ResponseEntity<>(borrowedBookService.getAllBorrowedBook(),HttpStatus.OK);
    }

}
