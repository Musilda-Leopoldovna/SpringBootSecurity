package ru.kata.spring.boot_security.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.entity.User;
import ru.kata.spring.boot_security.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> getListOfUsers() {
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void addNewUser(User user) {
        userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void removeUserByID(Long ID) {
        userRepository.deleteById(ID);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void changeUser(User updUser) {
        User existingUser = userRepository.findById(updUser.getID()).orElse(new User());
        existingUser.setFirstName(updUser.getFirstName());
        existingUser.setLastName(updUser.getLastName());
        existingUser.setAge(updUser.getAge());
        existingUser.setEmail(updUser.getEmail());
        userRepository.save(existingUser);
    }

    public User getUserByID(Long userId) {
        return userRepository.findById(userId).orElse(new User());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

