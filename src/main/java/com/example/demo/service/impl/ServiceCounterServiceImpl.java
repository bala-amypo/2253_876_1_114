// // // package com.example.demo.service.impl;

// // // import com.example.demo.entity.ServiceCounter;
// // // import com.example.demo.repository.ServiceCounterRepository;
// // // import com.example.demo.service.ServiceCounterService;
// // // import org.springframework.stereotype.Service;

// // // import java.util.List;

// // // @Service
// // // public class ServiceCounterServiceImpl implements ServiceCounterService {

// // //     private final ServiceCounterRepository repository;

// // //     public ServiceCounterServiceImpl(ServiceCounterRepository repository) {
// // //         this.repository = repository;
// // //     }

// // //     public ServiceCounter addCounter(ServiceCounter counter) {
// // //         return repository.save(counter);
// // //     }

// // //     public List<ServiceCounter> getActiveCounters() {
// // //         return repository.findByIsActiveTrue();
// // //     }
// // // }
// // package com.example.demo.service.impl;

// // import com.example.demo.entity.ServiceCounter;
// // import com.example.demo.repository.ServiceCounterRepository;
// // import org.springframework.stereotype.Service;

// // import java.util.List;
// // @Service
// // public class ServiceCounterServiceImpl {

// //     private final ServiceCounterRepository counterRepository;

// //     public ServiceCounterServiceImpl(ServiceCounterRepository counterRepository) {
// //         this.counterRepository = counterRepository;
// //     }

// //     public ServiceCounter addCounter(ServiceCounter counter) {

// //         if (counter == null) {
// //             throw new IllegalArgumentException("Counter cannot be null");
// //         }

// //         // 🔴 NEVER save null
// //         return counterRepository.save(counter);
// //     }

// //     public List<ServiceCounter> getActiveCounters() {
// //         return counterRepository.findByIsActiveTrue();
// //     }
// // }
// package com.example.demo.service.impl;

// import com.example.demo.entity.ServiceCounter;
// import com.example.demo.repository.ServiceCounterRepository;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class ServiceCounterServiceImpl {

//     private final ServiceCounterRepository counterRepository;

//     public ServiceCounterServiceImpl(ServiceCounterRepository counterRepository) {
//         this.counterRepository = counterRepository;
//     }

//     public ServiceCounter addCounter(ServiceCounter counter) {

//         if (counter == null) {
//             throw new IllegalArgumentException("Counter cannot be null");
//         }

//         return counterRepository.save(counter); // ✅
//     }

//     public List<ServiceCounter> getActiveCounters() {
//         return counterRepository.findByIsActiveTrue();
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.service.ServiceCounterService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceCounterServiceImpl implements ServiceCounterService {
    private final ServiceCounterRepository counterRepository;

    public ServiceCounterServiceImpl(ServiceCounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    public ServiceCounter addCounter(ServiceCounter counter) {
        return counterRepository.save(counter);
    }

    public List<ServiceCounter> getActiveCounters() {
        return counterRepository.findByIsActiveTrue();
    }
}