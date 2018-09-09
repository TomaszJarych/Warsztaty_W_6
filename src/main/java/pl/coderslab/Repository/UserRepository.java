package pl.coderslab.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.Entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findByEmail(String email);

}