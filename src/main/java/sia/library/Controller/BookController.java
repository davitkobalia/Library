package sia.library.Controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/book")
    public String books(@RequestParam(name = "title", required = false) String title,
                        @RequestParam(name = "author",required = false) String author,
                        @RequestParam(name = "sort", required = false, defaultValue = "title") String sort,
                        Model model) {
        model.addAttribute("books",bookService.listBooks(title,author,sort));
        return "book/books";
    }

    @GetMapping("/book/create")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/book_create";
    }

        @PostMapping("/book")
        public String createBook(Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "book/book_create";
        book.setReserved(false);
        bookService.saveBook(book);
        return "redirect:/book";
    }

    @GetMapping("/book/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return "redirect:/book";
        }
        model.addAttribute("book", book);
        return "book/book_edit";
    }


    @PatchMapping("/book/{id}")
    public String updateBook(@PathVariable int id,@ModelAttribute Book updatedBook,BindingResult bindingResult) {
        updatedBook.setId(id);
        bookService.saveBook(updatedBook);
        return "redirect:/book";
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable int id) {
        Loan loan =loanService.getLoanByBookId(id);
        if(loan != null) loanService.returnBook(loan);
       bookService.deleteBook(id);
        return "redirect:/book";
    }


}
