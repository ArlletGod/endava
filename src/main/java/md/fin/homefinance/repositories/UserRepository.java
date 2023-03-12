package md.fin.homefinance.repositories;

import md.fin.homefinance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByName(String name);
}
