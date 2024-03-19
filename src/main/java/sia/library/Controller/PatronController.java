package sia.library.Controller;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sia.library.Entity.Book;
import sia.library.Entity.Loan;
import sia.library.Entity.Patron;
import sia.library.Service.LoanService;
import sia.library.Service.PatronService;

import java.util.List;

@Controller
public class PatronController {
    private final LoanService loanService;
    private final PatronService patronService;
    public PatronController(PatronService patronService, LoanService loanService){
        this.patronService = patronService;
        this.loanService = loanService;
    }

    @GetMapping("/patrons")
    public String patrons(@RequestParam(name="name",required = false)String name,
                          @RequestParam(name = "sort", required = false, defaultValue = "name") String sort,
                          Model model){
        model.addAttribute("patrons",patronService.listPatrons(name,sort));
        return "patrons/patrons";
    }
    @GetMapping("/patron/create")
    public String createPatronForm(Model model){
        model.addAttribute("patron",new Patron());
        return "patrons/patron_create";
    }
    @PostMapping("/patron/create")
    public String savePatron(Patron patron){
        patronService.savePatron(patron);
        return "redirect:/patrons";
    }

    @GetMapping("/patron/edit/{id}")
    public String showPatronEditForm(@PathVariable int id,Model model){
        Patron patron = patronService.getPatronById(id);

        if(patron == null){
            return "redirect:/patrons";
        }
        model.addAttribute("patron",patron);
        return "patrons/patron_edit";
    }

    @PostMapping("/patron/edit/{id}")
    public String updatePatronInfo(@PathVariable int id,@ModelAttribute Patron patron){
        patron.setId(id);

        patronService.savePatron(patron);

        return "redirect:/patrons";

    }
    @PostMapping("/patron/delete/{id}")
    public String deletePatron(@PathVariable int id) {
        patronService.deletePatron(id);
        return "redirect:/patrons";
    }
    @GetMapping("/patron/{patronId}/books")
    public String getPatronBooks(@PathVariable int patronId, Model model) {
        List<Book> patronBooks = loanService.getPatronBooks(patronId);
        model.addAttribute("patronBooks", patronBooks);
        return "patrons/patron_books";
    }
    @PostMapping("patron/return/{id}")
    public String returnPatronsBook(@PathVariable int id){
        Loan loan = loanService.getLoanByBookId(id);
        loanService.returnBook(loan);
        return "redirect:/patron/" + loan.getPatron().getId() + "/books";

    }

}
