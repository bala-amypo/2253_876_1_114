// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// public class ServiceCounter {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String counterName;
//     private String department;
//     private Boolean isActive;

//     public ServiceCounter() {}

//     // ✅ GETTERS
//     public Long getId() { return id; }
//     public String getCounterName() { return counterName; }
//     public String getDepartment() { return department; }
//     public Boolean getIsActive() { return isActive; }

//     // ✅ SETTERS REQUIRED BY TEST
//     public void setId(Long id) { this.id = id; }
//     public void setCounterName(String counterName) { this.counterName = counterName; }
//     public void setDepartment(String department) { this.department = department; }
//     public void setIsActive(Boolean isActive) { this.isActive = isActive; }
// }


// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "service_counters")
// public class ServiceCounter {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(nullable = false)
//     private String counterName;
    
//     @Column(nullable = false)
//     private String department;
    
//     @Column(nullable = false)
//     private Boolean isActive = true;

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
//     public String getCounterName() { return counterName; }
//     public void setCounterName(String counterName) { this.counterName = counterName; }
//     public String getDepartment() { return department; }
//     public void setDepartment(String department) { this.department = department; }
//     public Boolean getIsActive() { return isActive; }
//     public void setIsActive(Boolean isActive) { this.isActive = isActive; }
// }


package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ServiceCounter {

    @Id
    @GeneratedValue
    private Long id;

    private String counterName;
    private String department;

    private Boolean isActive = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCounterName() { return counterName; }
    public void setCounterName(String counterName) { this.counterName = counterName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
