// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// public class TokenLog {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private Token token;

//     private String logMessage;

//     private LocalDateTime loggedAt;

//     // ✅ REQUIRED BY JPA
//     public TokenLog() {
//     }

//     // ✅ REQUIRED BY SERVICE + TEST FILE
//     public TokenLog(Token token, String logMessage) {
//         this.token = token;
//         this.logMessage = logMessage;
//     }

//     // ===== GETTERS =====
//     public Long getId() {
//         return id;
//     }

//     public Token getToken() {
//         return token;
//     }

//     public String getLogMessage() {
//         return logMessage;
//     }

//     public LocalDateTime getLoggedAt() {
//         return loggedAt;
//     }

//     // ===== SETTERS REQUIRED BY TEST FILE =====
//     public void setId(Long id) {
//         this.id = id;
//     }

//     public void setToken(Token token) {
//         this.token = token;
//     }

//     public void setLogMessage(String logMessage) {
//         this.logMessage = logMessage;
//     }

//     public void setLoggedAt(LocalDateTime loggedAt) {
//         this.loggedAt = loggedAt;
//     }

//     // Optional but safe
//     @PrePersist
//     public void onCreate() {
//         this.loggedAt = LocalDateTime.now();
//     }
// }

// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "token_logs")
// public class TokenLog {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @ManyToOne
//     @JoinColumn(name = "token_id", nullable = false)
//     private Token token;
    
//     @Column(nullable = false)
//     private String message;
    
//     @Column(nullable = false)
//     private LocalDateTime loggedAt = LocalDateTime.now();

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
//     public Token getToken() { return token; }
//     public void setToken(Token token) { this.token = token; }
//     public String getMessage() { return message; }
//     public void setMessage(String message) { this.message = message; }
//     public LocalDateTime getLoggedAt() { return loggedAt; }
//     public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
// }
// package com.example.demo.entity;

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "token_logs")
public class TokenLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String message;
    private LocalDateTime loggedAt = LocalDateTime.now();
    
    @ManyToOne
    @JoinColumn(name = "token_id")
    private Token token;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
    
    public Token getToken() { return token; }
    public void setToken(Token token) { this.token = token; }
}