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

    @Override
    public ServiceCounter addCounter(ServiceCounter counter) {
        return counterRepository.save(counter);
    }

    @Override
    public List<ServiceCounter> getActiveCounters() {
        return counterRepository.findByIsActiveTrue();
    }

    @Override
    public ServiceCounter getById(Long id) {
        return counterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Counter not found"));
    }
}