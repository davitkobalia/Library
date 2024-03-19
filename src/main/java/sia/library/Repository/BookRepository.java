package sia.library.Repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.library.Entity.Book;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
        @Query("SELECT b FROM Book b WHERE b.author LIKE %?1%")
        List<Book> findByAuthor(String author, Sort sort);

        @Query("SELECT b FROM Book b WHERE b.title LIKE %?1%")
        List<Book> findByTitle(String title, Sort sort);
        List<Book> findByReservedFalse(Sort sort);
}
