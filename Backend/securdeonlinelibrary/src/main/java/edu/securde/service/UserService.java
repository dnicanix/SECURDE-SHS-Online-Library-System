package edu.securde.service;

import edu.securde.model.User;

/**
 * Created by Danica C. Sevilla on 7/11/2017.
 */
public interface UserService {
    public User findUserByUsername(String username);
    public void saveUser(User user);
}
