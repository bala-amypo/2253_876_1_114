// // package com.example.demo.entity;

// // import jakarta.persistence.*;
// // import java.time.LocalDateTime;

// // @Entity
// // public class QueuePosition {

// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;

// //     @OneToOne
// //     private Token token;

// //     private Integer position;

// //     private LocalDateTime updatedAt;

// //     // ✅ REQUIRED BY JPA
// //     public QueuePosition() {
// //     }

// //     // ✅ REQUIRED BY SERVICE + TEST FILE
// //     public QueuePosition(Token token,
// //                          Integer position,
// //                          LocalDateTime updatedAt) {
// //         this.token = token;
// //         this.position = position;
// //         this.updatedAt = updatedAt;
// //     }

// //     // ===== GETTERS =====
// //     public Long getId() {
// //         return id;
// //     }

// //     public Token getToken() {
// //         return token;
// //     }

// //     public Integer getPosition() {
// //         return position;
// //     }

// //     public LocalDateTime getUpdatedAt() {
// //         return updatedAt;
// //     }

// //     // ===== SETTERS REQUIRED BY TEST FILE =====
// //     public void setId(Long id) {
// //         this.id = id;
// //     }

// //     public void setToken(Token token) {
// //         this.token = token;
// //     }

// //     public void setPosition(Integer position) {
// //         this.position = position;
// //     }

// //     public void setUpdatedAt(LocalDateTime updatedAt) {
// //         this.updatedAt = updatedAt;
// //     }
// // // }

// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// public class QueuePosition {

//     @Id
//     @GeneratedValue
//     private Long id;

//     private Integer position;

//     @OneToOne
//     private Token token;

//     public QueuePosition() {}

//     public QueuePosition(Token token, Integer position) {
//         this.token = token;
//         this.position = position;
//     }

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public Integer getPosition() { return position; }
//     public void setPosition(Integer position) { this.position = position; }

//     public Token getToken() { return token; }
//     public void setToken(Token token) { this.token = token; }
// }

package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "queue_positions")
public class QueuePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer position;
    
    @OneToOne
    @JoinColumn(name = "token_id")
    private Token token;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }
    
    public Token getToken() { return token; }
    public void setToken(Token token) { this.token = token; }
}