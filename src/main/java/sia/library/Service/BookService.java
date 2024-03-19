package sia.library.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sia.library.Entity.Book;
import sia.library.Repository.BookRepository;
import sia.library.Repository.UserRepository;
import sia.library.Security.User;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> listBooks(String title,String author,String sort) {
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitle(title, Sort.by(sort));
        }else if(author != null && !author.isEmpty()){
            return bookRepository.findByAuthor(author,Sort.by(sort));
        }else {
            return bookRepository.findAll(Sort.by(sort));
        }
    }

    public void saveBook(Book book) {
        log.info("Saving new {}", book);
        bookRepository.save(book);
    }

    public void deleteBook(int id){bookRepository.deleteById(id);}

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

}