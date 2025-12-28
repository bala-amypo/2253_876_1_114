// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// public class Token {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String tokenNumber;

//     private String status;

//     @ManyToOne
//     private ServiceCounter serviceCounter;

//     private LocalDateTime issuedAt;

//     private LocalDateTime completedAt;

//     // ✅ REQUIRED BY JPA
//     public Token() {
//     }

//     // ✅ REQUIRED BY SERVICE + TEST FILE
//     public Token(String tokenNumber,
//                  ServiceCounter serviceCounter,
//                  String status,
//                  LocalDateTime issuedAt) {
//         this.tokenNumber = tokenNumber;
//         this.serviceCounter = serviceCounter;
//         this.status = status;
//         this.issuedAt = issuedAt;
//     }

//     // ===== GETTERS =====
//     public Long getId() {
//         return id;
//     }

//     public String getTokenNumber() {
//         return tokenNumber;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public ServiceCounter getServiceCounter() {
//         return serviceCounter;
//     }

//     public LocalDateTime getIssuedAt() {
//         return issuedAt;
//     }

//     public LocalDateTime getCompletedAt() {
//         return completedAt;
//     }

//     // ===== SETTERS REQUIRED BY TEST FILE =====
//     public void setId(Long id) {
//         this.id = id;
//     }

//     public void setTokenNumber(String tokenNumber) {
//         this.tokenNumber = tokenNumber;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }

//     public void setServiceCounter(ServiceCounter serviceCounter) {
//         this.serviceCounter = serviceCounter;
//     }

//     public void setIssuedAt(LocalDateTime issuedAt) {
//         this.issuedAt = issuedAt;
//     }

//     public void setCompletedAt(LocalDateTime completedAt) {
//         this.completedAt = completedAt;
//     }
// }


// // package com.example.demo.entity;

// // import jakarta.persistence.*;
// // import java.time.LocalDateTime;

// // @Entity
// // @Table(name = "tokens")
// // public class Token {
// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;
    
// //     @Column(unique = true, nullable = false)
// //     private String tokenNumber;
    
// //     @Column(nullable = false)
// //     private String status = "WAITING";
    
// //     @Column(nullable = false)
// //     private LocalDateTime issuedAt = LocalDateTime.now();
    
// //     private LocalDateTime completedAt;
    
// //     @ManyToOne
// //     @JoinColumn(name = "service_counter_id", nullable = false)
// //     private ServiceCounter serviceCounter;

// //     public Long getId() { return id; }
// //     public void setId(Long id) { this.id = id; }
// //     public String getTokenNumber() { return tokenNumber; }
// //     public void setTokenNumber(String tokenNumber) { this.tokenNumber = tokenNumber; }
// //     public String getStatus() { return status; }
// //     public void setStatus(String status) { this.status = status; }
// //     public LocalDateTime getIssuedAt() { return issuedAt; }
// //     public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
// //     public LocalDateTime getCompletedAt() { return completedAt; }
// //     public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
// //     public ServiceCounter getServiceCounter() { return serviceCounter; }
// //     public void setServiceCounter(ServiceCounter serviceCounter) { this.serviceCounter = serviceCounter; }
// // }
// // package com.example.demo.entity;

// // import jakarta.persistence.*;
// // import java.time.LocalDateTime;

// // @Entity
// // @Table(name = "tokens")
// // public class Token {
// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;
    
// //     @Column(unique = true)
// //     private String tokenNumber;
    
// //     private String status;
// //     private LocalDateTime issuedAt;
// //     private LocalDateTime completedAt;
    
// //     @ManyToOne
// //     @JoinColumn(name = "service_counter_id")
// //     private ServiceCounter serviceCounter;

// //     public Long getId() { return id; }
// //     public void setId(Long id) { this.id = id; }
    
// //     public String getTokenNumber() { return tokenNumber; }
// //     public void setTokenNumber(String tokenNumber) { this.tokenNumber = tokenNumber; }
    
// //     public String getStatus() { return status; }
// //     public void setStatus(String status) { this.status = status; }
    
// //     public LocalDateTime getIssuedAt() { return issuedAt; }
// //     public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
    
// //     public LocalDateTime getCompletedAt() { return completedAt; }
// //     public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    
// //     public ServiceCounter getServiceCounter() { return serviceCounter; }
// //     public void setServiceCounter(ServiceCounter serviceCounter) { this.serviceCounter = serviceCounter; }
// // }


package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String tokenNumber;
    
    private String status = "WAITING";
    
    @ManyToOne
    @JoinColumn(name = "service_counter_id")
    private ServiceCounter serviceCounter;
    
    private LocalDateTime issuedAt = LocalDateTime.now();
    private LocalDateTime completedAt;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTokenNumber() { return tokenNumber; }
    public void setTokenNumber(String tokenNumber) { this.tokenNumber = tokenNumber; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public ServiceCounter getServiceCounter() { return serviceCounter; }
    public void setServiceCounter(ServiceCounter serviceCounter) { this.serviceCounter = serviceCounter; }
    
    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
    
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}
