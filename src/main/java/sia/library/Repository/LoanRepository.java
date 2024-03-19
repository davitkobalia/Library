package sia.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sia.library.Entity.Book;
import sia.library.Entity.Loan;
import sia.library.Entity.Patron;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Integer> {
    Loan findByPatronAndBook(Patron patron, Book book);
    Loan findById(int id);
    @Query("SELECT l.book FROM Loan l WHERE l.patron.id = :patronId")
    List<Book> findBooksByPatronId(@Param("patronId") int patronId);

    Loan findByBook_Id(int id);

    List<Loan> findByReturnDateBefore(LocalDate date);
}
