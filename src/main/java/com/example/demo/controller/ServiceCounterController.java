// package com.example.demo.controller;

// import com.example.demo.entity.ServiceCounter;
// import com.example.demo.service.ServiceCounterService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/counters")
// @Tag(name = "Service Counters")
// public class ServiceCounterController {

//     private final ServiceCounterService serviceCounterService;

//     public ServiceCounterController(ServiceCounterService serviceCounterService) {
//         this.serviceCounterService = serviceCounterService;
//     }

//     @PostMapping
//     @Operation(summary = "Add service counter")
//     public ServiceCounter addCounter(@RequestBody ServiceCounter counter) {
//         return serviceCounterService.addCounter(counter);
//     }

//     @GetMapping("/active")
//     @Operation(summary = "Get active counters")
//     public List<ServiceCounter> getActiveCounters() {
//         return serviceCounterService.getActiveCounters();
//     }
// }
package com.example.demo.controller;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.service.ServiceCounterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/counters")
public class ServiceCounterController {
    private final ServiceCounterService counterService;

    public ServiceCounterController(ServiceCounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping
    public ResponseEntity<ServiceCounter> addCounter(@RequestBody ServiceCounter counter) {
        ServiceCounter created = counterService.addCounter(counter);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/active")
    public ResponseEntity<List<ServiceCounter>> getActiveCounters() {
        List<ServiceCounter> counters = counterService.getActiveCounters();
        return ResponseEntity.ok(counters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCounter> getCounter(@PathVariable Long id) {
        ServiceCounter counter = counterService.getById(id);
        return ResponseEntity.ok(counter);
    }
}