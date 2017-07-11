package edu.securde.service;

import java.util.Arrays;
import java.util.HashSet;

import edu.securde.model.User;
import edu.securde.repository.RoleRepository;
import edu.securde.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Danica C. Sevilla on 7/11/2017.
 */
@Service("userService")
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        
    }
}
