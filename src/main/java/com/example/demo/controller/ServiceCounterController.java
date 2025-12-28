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

// package com.example.demo.controller;

// import com.example.demo.entity.ServiceCounter;
// import com.example.demo.service.impl.ServiceCounterServiceImpl;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/counters")
// public class ServiceCounterController {
//     private final ServiceCounterServiceImpl counterService;
    
//     public ServiceCounterController(ServiceCounterServiceImpl counterService) {
//         this.counterService = counterService;
//     }
    
//     @PostMapping("/")
//     public ServiceCounter addCounter(@RequestBody ServiceCounter counter) {
//         return counterService.addCounter(counter);
//     }
    
//     @GetMapping("/active")
//     public List<ServiceCounter> getActiveCounters() {
//         return counterService.getActiveCounters();
//     }
// }


// package com.example.demo.controller;

// import com.example.demo.entity.ServiceCounter;
// import com.example.demo.service.impl.ServiceCounterServiceImpl;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/counters")
// public class ServiceCounterController {
//     private final ServiceCounterServiceImpl counterService;
    
//     public ServiceCounterController(ServiceCounterServiceImpl counterService) {
//         this.counterService = counterService;
//     }
    
//     @PostMapping("/")
//     public ServiceCounter addCounter(@RequestBody ServiceCounter counter) {
//         return counterService.addCounter(counter);
//     }
    
//     @GetMapping("/active")
//     public List<ServiceCounter> getActiveCounters() {
//         return counterService.getActiveCounters();
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.service.ServiceCounterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/counters")
@Tag(name = "Service Counters", description = "Service counter management")
@SecurityRequirement(name = "bearerAuth")
public class ServiceCounterController {
    private final ServiceCounterService counterService;

    public ServiceCounterController(ServiceCounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping("/")
    @Operation(summary = "Add new counter", description = "Create a new service counter")
    @ApiResponse(responseCode = "200", description = "Counter created successfully")
    public ServiceCounter addCounter(@RequestBody ServiceCounter counter) {
        return counterService.addCounter(counter);
    }

    @GetMapping("/active")
    @Operation(summary = "Get active counters", description = "Retrieve all active service counters")
    @ApiResponse(responseCode = "200", description = "Active counters retrieved successfully")
    public List<ServiceCounter> getActiveCounters() {
        return counterService.getActiveCounters();
    }
}
