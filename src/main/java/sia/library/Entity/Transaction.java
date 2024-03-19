package sia.library.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String  name;
    private String  surname;

    private String title;
    private String author;

    private String transactionType;

    public Transaction(String name, String surname, String title, String author, String transactionType) {
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.author = author;
        this.transactionType = transactionType;
    }
}
