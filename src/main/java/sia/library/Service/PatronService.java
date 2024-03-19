package sia.library.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sia.library.Entity.Patron;
import sia.library.Repository.PatronRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatronService {
    private final PatronRepository patronRepository;

    public List<Patron> listPatrons(String name, String sort){
        if(name !=null && !name.isEmpty()){
            return patronRepository.findByName(name,Sort.by(sort));
        }
        else{
            return patronRepository.findAll(Sort.by(sort));
        }
    }

    public void savePatron(Patron patron){
        patronRepository.save(patron);
    }

    public void deletePatron(int id){patronRepository.deleteById(id);}
    public Patron getPatronById(int id){return patronRepository.findById(id).orElse(null);}
}
