package sia.library.Service;

import org.springframework.stereotype.Service;
import sia.library.Entity.Transaction;
import sia.library.Repository.TransactionsRepository;

import java.util.List;

@Service
public class TransactionsService {
    final
    TransactionsRepository transactionsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public void addTransaction(Transaction transaction){
        transactionsRepository.save(transaction);
    }

    public List<Transaction> listTransactions(){
        return transactionsRepository.findAll();
    }
}
