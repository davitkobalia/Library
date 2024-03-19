package sia.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sia.library.Security.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByUsername(String username);
}
