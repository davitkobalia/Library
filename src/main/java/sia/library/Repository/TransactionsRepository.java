package sia.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sia.library.Entity.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction,Integer> {
}
