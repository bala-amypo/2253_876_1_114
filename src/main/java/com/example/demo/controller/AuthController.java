// package com.example.demo.controller;

// import com.example.demo.config.JwtTokenProvider;
// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// @Tag(name = "Auth")
// public class AuthController {

//     private final UserService userService;
//     private final JwtTokenProvider jwtTokenProvider;
//     private final PasswordEncoder passwordEncoder;

//     public AuthController(UserService userService,
//                           JwtTokenProvider jwtTokenProvider,
//                           PasswordEncoder passwordEncoder) {
//         this.userService = userService;
//         this.jwtTokenProvider = jwtTokenProvider;
//         this.passwordEncoder = passwordEncoder;
//     }

//     @PostMapping("/register")
//     @Operation(summary = "Register user")
//     public User register(@RequestBody RegisterRequest request) {

//         User user = new User(
//                 request.getName(),
// //                 request.getEmail(),
// //                 passwordEncoder.encode(request.getPassword()), // ✅ encode here
// //                 request.getRole()
// //         );

// //         return userService.registerUser(user);
// //     }

// //     @PostMapping("/login")
// //     @Operation(summary = "Login user")
// //     public AuthResponse login(@RequestBody AuthRequest request) {

// //         User user = userService.findByEmail(request.getEmail());

// //         if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
// //             throw new IllegalArgumentException("Invalid credentials");
// //         }

// //         String token = jwtTokenProvider.generateToken(
// //                 user.getId(),
// //                 user.getEmail(),
// //                 user.getRole()
// //         );

// //         return new AuthResponse(
// //                 token,
// //                 user.getId(),
// //                 user.getEmail(),
// //                 user.getRole()
// //         );
// //     }
// // // }
// // package com.example.demo.controller;

// // import com.example.demo.config.JwtTokenProvider;
// // import com.example.demo.entity.User;
// // import com.example.demo.service.UserService;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.web.bind.annotation.*;

// // @RestController
// // @RequestMapping("/api/auth")
// // public class AuthController {
// //     private final UserService userService;
// //     private final JwtTokenProvider jwtTokenProvider;
// //     private final PasswordEncoder passwordEncoder;

// //     public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
// //         this.userService = userService;
// //         this.jwtTokenProvider = jwtTokenProvider;
// //         this.passwordEncoder = passwordEncoder;
// //     }

// //     @PostMapping("/register")
// //     public ResponseEntity<User> register(@RequestBody User user) {
// //         User registeredUser = userService.register(user);
// //         return ResponseEntity.ok(registeredUser);
// //     }

// //     @PostMapping("/login")
// //     public ResponseEntity<String> login(@RequestBody User loginRequest) {
// //         try {
// //             User user = userService.findByEmail(loginRequest.getEmail());
// //             if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
// //                 String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole());
// //                 return ResponseEntity.ok(token);
// //             }
// //             return ResponseEntity.badRequest().body("Invalid credentials");
// //         } catch (RuntimeException e) {
// //             return ResponseEntity.badRequest().body("Invalid credentials");
// //         }
// //     }

// //     @GetMapping("/profile")
// //     public ResponseEntity<User> getProfile(@RequestParam String email) {
// //         User user = userService.findByEmail(email);
// //         return ResponseEntity.ok(user);
// //     }
// // }
// package com.example.demo.controller;

// import com.example.demo.entity.User;
// import com.example.demo.service.impl.UserServiceImpl;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {
//     private final UserServiceImpl userService;
    
//     public AuthController(UserServiceImpl userService) {
//         this.userService = userService;
//     }
    
//     @PostMapping("/register")
//     public User register(@RequestBody User user) {
//         return userService.register(user);
//     }
    
//     @PostMapping("/login")
//     public String login(@RequestBody User user) {
//         return "token";
//     }
// }

package com.example.demo.controller;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    
    
    public AuthController(UserServiceImpl userService, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
    
    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return jwtTokenProvider.generateToken(user.getId(), user.getEmail(), "USER");
        }
        throw new RuntimeException("Invalid credentials");
    }
}