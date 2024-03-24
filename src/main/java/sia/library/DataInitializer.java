package sia.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sia.library.Entity.Book;
import sia.library.Entity.Patron;
import sia.library.Repository.BookRepository;
import sia.library.Repository.PatronRepository;
import sia.library.Repository.RoleRepository;
import sia.library.Repository.UserRepository;
import sia.library.Security.Role;
import sia.library.Security.User;

@Component

public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("LIBRARIAN"));
        }



        if (userRepository.findAll().isEmpty()) {
            userRepository.save(new User("admin",
                    "$2a$12$31Lle2EsxWajkkxR9w3CLe9oZUI9Ym4T7ceY8kKmsa7InmgC7gwIC",
                    "ADMIN"));
        }



        if (bookRepository.findAll().isEmpty()) {
            bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4", "Fiction",false));
            bookRepository.save(new Book("1984", "George Orwell", "978-0-452-28423-4", "Science Fiction",false));
            bookRepository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5", "Classic",false));
            bookRepository.save(new Book("Pride and Prejudice", "Jane Austen", "978-1-85326-000-1", "Romance",false));
            bookRepository.save(new Book("The Catcher in the Rye", "J.D. Salinger", "978-0-316-76948-4", "Literary Fiction",false));
        }

        if (patronRepository.findAll().isEmpty()){
            patronRepository.save(new Patron("John","Doe", "john.doe@example.com", "+1234567890",true));
            patronRepository.save(new Patron("Jane", "Smith", "jane.smith@example.com", "+1987654321",true));
            patronRepository.save(new Patron("Alice", "Johnson", "alice.johnson@example.com", "+1122334455",false));
            patronRepository.save(new Patron("Bob", "Brown", "bob.brown@example.com", "+1567890123",true));
            patronRepository.save(new Patron("Emily", "Davis", "emily.davis@example.com", "+1678901234",false));
        }


    }
}
