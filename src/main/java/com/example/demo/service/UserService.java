package com.example.demo.service;

import com.example.demo.repository.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        Optional<User> optionalUser = userRepository.findByUserEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new IllegalStateException("User already exists");
        }
        user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
        return userRepository.save(user);
//public boolean isEmailUnique(String email) {
//    return userRepository.existsByEmail(email);
//    }

    }

    public void delete(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User с id " + id + " не существует");
        }
        userRepository.deleteById(id);
        System.out.println("Юзер с id " + id + " удалён");
    }
@Transactional
    public void update(long id, String email, String name) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User с id " + id + " не существует");
        }
        User user = optionalUser.get();

        if (email != null && !email.equals(user.getEmail())) {
            Optional<User> foundByEmail = userRepository.findByUserEmail(email);
            if (foundByEmail.isPresent()) {
                throw new IllegalStateException("User already exists");
            }
            user.setEmail(email);
        }
        if (name != null && !name.equals(user.getName())) {
            user.setName(name);
        }
//        userRepository.save(user);
    }
}
