package sia.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sia.library.Security.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
