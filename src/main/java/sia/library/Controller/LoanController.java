package sia.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sia.library.Entity.Book;
import sia.library.Entity.Loan;
import sia.library.Entity.Patron;
import sia.library.Entity.Transaction;
import sia.library.Service.BookService;
import sia.library.Service.LoanService;
import sia.library.Service.PatronService;
import sia.library.Service.TransactionsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class LoanController {
    private final LoanService loanService;
    private final BookService bookService;
    private final PatronService patronService;
    private final TransactionsService transactionsService;

    @Autowired
    public LoanController(LoanService loanService, BookService bookService, PatronService patronService, TransactionsService transactionsService) {
        this.loanService = loanService;
        this.bookService = bookService;
        this.patronService = patronService;
        this.transactionsService = transactionsService;
    }

    @GetMapping("/loan")
    public String books(@RequestParam(name = "title", required = false) String title,
                        @RequestParam(name = "author", required = false) String author,
                        @RequestParam(name = "sort", required = false, defaultValue = "title") String sort,
                        Model model) {
        model.addAttribute("books", loanService.listBooks(title, author,sort));
        return "borrow/booksList";
    }

    @GetMapping("/showPatrons")
    public String showPatrons(@RequestParam(name = "name", required = false) String name,
                              @RequestParam(name = "bookId") int bookId,
                              @RequestParam(name = "sort", required = false, defaultValue = "name") String sort,
                              Model model) {
        model.addAttribute("patrons", loanService.listPatrons(name,sort));
        model.addAttribute("bookId", bookId);
        return "borrow/showPatrons";
    }

    @PostMapping("/loan")
    public String borrowBook(@RequestParam(name = "patronId") int patronId,
                           @RequestParam(name = "bookId") int bookId) {
        Patron patron = patronService.getPatronById(patronId);
        Book book = bookService.getBookById(bookId);
        if (patron != null && book != null) {
            loanService.takeBook(patron, book);
            transactionsService.addTransaction(new Transaction(patron.getName(),patron.getSurname(),
                                               book.getTitle(),book.getAuthor(),"borrow"));
        }
        return "redirect:/borrow-information";
    }


    @GetMapping("/borrow-information")
    public String showLoanInformation(Model model) {
        List<Loan> loans = loanService.getAllLoans();
        model.addAttribute("loans", loans);
        return "borrow/borrow_information";
    }
    @DeleteMapping("/loan/{loanId}")
    public String returnBook(@PathVariable int loanId) {
        Loan loan = loanService.getById(loanId);
        if (loan != null) {
            loanService.returnBook(loan);
            transactionsService.addTransaction(new Transaction(loan.getPatron().getName(),loan.getPatron().getSurname(),
                                                               loan.getBook().getTitle(),loan.getBook().getAuthor(),"return"));
        }
        return "redirect:/borrow-information";
    }
}