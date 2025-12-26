// package com.example.demo.service.impl;

// import com.example.demo.entity.ServiceCounter;
// import com.example.demo.repository.ServiceCounterRepository;
// import com.example.demo.service.ServiceCounterService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class ServiceCounterServiceImpl implements ServiceCounterService {

//     private final ServiceCounterRepository repository;

//     public ServiceCounterServiceImpl(ServiceCounterRepository repository) {
//         this.repository = repository;
//     }

//     public ServiceCounter addCounter(ServiceCounter counter) {
//         return repository.save(counter);
//     }

//     public List<ServiceCounter> getActiveCounters() {
//         return repository.findByIsActiveTrue();
//     }
// }

// package com.example.demo.service.impl;

// import com.example.demo.entity.ServiceCounter;
// import com.example.demo.repository.ServiceCounterRepository;
// import java.util.List;

// public class ServiceCounterServiceImpl {

//     private final ServiceCounterRepository repo;

//     public ServiceCounterServiceImpl(ServiceCounterRepository repo) {
//         this.repo = repo;
//     }

//     public ServiceCounter addCounter(ServiceCounter counter) {
//         return repo.save(counter);
//     }

//     public List<ServiceCounter> getActiveCounters() {
//         return repo.findByIsActiveTrue();
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.repository.ServiceCounterRepository;
import java.util.List;

public class ServiceCounterServiceImpl {

    private final ServiceCounterRepository repo;

    public ServiceCounterServiceImpl(ServiceCounterRepository repo) {
        this.repo = repo;
    }

    public ServiceCounter addCounter(ServiceCounter sc) {
        return repo.save(sc);
    }

    public List<ServiceCounter> getActiveCounters() {
        return repo.findByIsActiveTrue();
    }
}
