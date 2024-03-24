package sia.library.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sia.library.Entity.Book;
import sia.library.Entity.Loan;
import sia.library.Entity.Patron;
import sia.library.Repository.BookRepository;
import sia.library.Repository.LoanRepository;
import sia.library.Repository.PatronRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public List<Book> listBooks(String title, String author,String sort) {
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitle(title,Sort.by(sort));
        } else if (author != null && !author.isEmpty()) {
            return bookRepository.findByAuthor(author, Sort.by(sort));
        } else {
            return bookRepository.findByReservedFalse(Sort.by(sort));
        }
    }

    public List<Patron> listPatrons(String name,String sort) {
        if (name != null && !name.isEmpty()) {
            return patronRepository.findByMembershipStatusTrueAndName(name,Sort.by(sort));
        } else {
            return patronRepository.findByMembershipStatusTrue(Sort.by(sort));
        }
    }

    public void takeBook(Patron patron, Book book) {
        Loan loan = new Loan();
        loan.setPatron(patron);
        loan.setBook(book);
        book.setReserved(true);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(LocalDate.now().plusWeeks(1));
        loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public void returnBook(Loan loan) {
        Book book = loan.getBook();
        book.setReserved(false);

        loanRepository.deleteById(loan.getId());
    }

    public List<Loan> getPatronBooks(int patronId) {
        return loanRepository.findLoansByPatronId(patronId);
    }

    public Loan getById(int id) {
        return loanRepository.findById(id);
    }

    public Loan getLoanByBookId(int id) {
        return loanRepository.findByBook_Id(id);
    }

    public List<Loan> getOverdueBooks() {
        return loanRepository.findByReturnDateBefore(LocalDate.now());
    }
}


