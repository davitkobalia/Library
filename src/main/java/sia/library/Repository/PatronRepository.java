package sia.library.Repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sia.library.Entity.Book;
import sia.library.Entity.Patron;

import java.util.List;

public interface PatronRepository extends JpaRepository<Patron,Integer> {
    @Query("SELECT p FROM Patron p WHERE p.name LIKE %?1%")
    List<Patron> findByName(String name, Sort sort);

    List<Patron> findByMembershipStatusTrue(Sort sort);
    List<Patron> findByMembershipStatusTrueAndName(String name,Sort sort);
}
