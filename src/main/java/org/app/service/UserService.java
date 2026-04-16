package org.app.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.app.entity.User;
import org.app.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.listAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User updateUser(User user){
        User existingUser = userRepository.findByEmail(user.email);

        if(existingUser == null){
            throw new RuntimeException("User not exists");
        }

        existingUser.name = user.name;
        existingUser.lastname = user.lastname;
        existingUser.phone = user.phone;

        userRepository.persist(existingUser);

        return existingUser;
    }

    @Transactional
    public User createUser(User user){

        User existingUser = userRepository.findByEmail(user.email);

        if(existingUser != null){
            throw new RuntimeException("Email already registered");
        }

        userRepository.persist(user);

        return user;
    }
}