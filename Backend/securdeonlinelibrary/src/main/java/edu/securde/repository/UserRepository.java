package edu.securde.repository;

import edu.securde.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * Created by Danica C. Sevilla on 7/11/2017.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
