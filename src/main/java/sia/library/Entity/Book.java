package sia.library.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name="isbn")
    private String ISBN;
    @Column(name = "genre")
    private String genre;
    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;
    @Column(name = "reserved", columnDefinition = "boolean default false")
    private Boolean reserved;

    public Book(String title, String author, String ISBN, String genre, Boolean reserved) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.reserved = reserved;
    }
}
