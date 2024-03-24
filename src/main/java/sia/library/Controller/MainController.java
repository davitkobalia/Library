package sia.library.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sia.library.Entity.Book;
import sia.library.Entity.Loan;
import sia.library.Entity.Transaction;
import sia.library.Security.User;
import sia.library.Service.LoanService;
import sia.library.Service.TransactionsService;
import sia.library.Service.UserService;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    LoanService loanService;
    TransactionsService transactionsService;

    @Autowired
    public MainController(LoanService loanService, TransactionsService transactionsService) {
        this.loanService = loanService;
        this.transactionsService = transactionsService;
    }

    @GetMapping("/")
    public String mainMenu(){
        return "main/main";
    }

    @GetMapping("/overdue-books")
    public String getOverdueBooks(Model model) {
        List<Loan> overdueBooks = loanService.getOverdueBooks();
        model.addAttribute("overdueBooks", overdueBooks);
        return "main/overdue_books";
    }
    @GetMapping("/transaction-history")
    public  String getTransactionHistory(Model model){
        List<Transaction> transactionList = transactionsService.listTransactions();
        Collections.reverse(transactionList);
        model.addAttribute("transactionList",transactionList);
        return "main/transaction_history";
    }


}
