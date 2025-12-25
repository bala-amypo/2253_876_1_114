// // package com.example.demo.service.impl;

// // import com.example.demo.entity.User;
// // import com.example.demo.exception.ResourceNotFoundException;
// // import com.example.demo.repository.UserRepository;
// // import com.example.demo.service.UserService;
// // import org.springframework.stereotype.Service;

// // @Service
// // public class UserServiceImpl implements UserService {

// //     private final UserRepository userRepository;

// //     // ✅ EXACT constructor required by test file
// //     public UserServiceImpl(UserRepository userRepository) {
// //         this.userRepository = userRepository;
// //     }

// //     @Override
// //     public User registerUser(User user) {
// //         if (userRepository.findByEmail(user.getEmail()).isPresent()) {
// //             throw new IllegalArgumentException("Email already exists");
// //         }
// //         if (user.getRole() == null) {
// //             user.setRole("STAFF");
// //         }
// //         return userRepository.save(user);
// //     }

// //     // ✅ REQUIRED BY TEST FILE
// //     public User register(User user) {
// //         return registerUser(user);
// //     }

// //     @Override
// //     public User findByEmail(String email) {
// //         return userRepository.findByEmail(email)
// //                 .orElseThrow(() -> new ResourceNotFoundException("User not found"));
// //     }
// // }import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// package com.example.demo.service.impl;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;
// @Service
// public class UserServiceImpl {

//     private final UserRepository userRepository;
//     private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//     public UserServiceImpl(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     public User register(User user) {

//         if (user == null) {
//             throw new IllegalArgumentException("User cannot be null");
//         }

//         if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//             throw new IllegalArgumentException("Email already exists");
//         }

//         user.setPassword(encoder.encode(user.getPassword()));

//         return userRepository.save(user);   // ✅ NEVER NULL
//     }

//     public User findByEmail(String email) {
//         return userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user); // ✅ never null
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
