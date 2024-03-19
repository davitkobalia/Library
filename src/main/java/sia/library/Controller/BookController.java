package sia.library.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sia.library.Entity.Book;
import sia.library.Entity.Loan;
import sia.library.Service.BookService;
import sia.library.Service.LoanService;

@Controller
public class BookController {
    final LoanService loanService;
    BookService bookService;
    public BookController(BookService bookService, LoanService loanService){
        this.bookService=bookService;
        this.loanService = loanService;
    }

    @GetMapping("/allBook")
    public String books(@RequestParam(name = "title", required = false) String title,
                        @RequestParam(name = "author",required = false) String author,
                        @RequestParam(name = "sort", required = false, defaultValue = "title") String sort,
                        Model model) {
        model.addAttribute("books",bookService.listBooks(title,author,sort));
        return "book/books";
    }

    @GetMapping("/allBook/book/create")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/book_create";
    }

        @PostMapping("/allBook/book/create")
    public String createBook(Book book) {
        book.setReserved(false);
        bookService.saveBook(book);
        return "redirect:/allBook";
    }

    @GetMapping("/allBook/book/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return "redirect:/allBook";
        }
        model.addAttribute("book", book);
        return "book/book_edit";
    }


    @PostMapping("/allBook/book/edit/{id}")
    public String updateBook(@PathVariable int id, @ModelAttribute Book updatedBook) {
        updatedBook.setId(id);
        bookService.saveBook(updatedBook);
        return "redirect:/allBook";
    }

    @PostMapping("/allBook/book/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        Loan loan =loanService.getLoanByBookId(id);
        if(loan != null) loanService.returnBook(loan);
       bookService.deleteBook(id);
        return "redirect:/allBook";
    }


}
