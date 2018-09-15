package pl.coderslab.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  User findByUsername(String name);
}
