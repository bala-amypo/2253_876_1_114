package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ColdRoom;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ColdRoomRepository;
import com.example.demo.service.ColdRoomService;

@Service
public class ColdRoomServiceImpl implements ColdRoomService {

    private final ColdRoomRepository coldRoomRepository;

    // ✅ Constructor injection
    public ColdRoomServiceImpl(ColdRoomRepository coldRoomRepository) {
        this.coldRoomRepository = coldRoomRepository;
    }

    @Override
    public ColdRoom createColdRoom(ColdRoom coldRoom) {

        // ✅ Null check
        if (coldRoom.getMinAllowed() == null || coldRoom.getMaxAllowed() == null) {
            throw new IllegalArgumentException("Temperature range invalid");
        }

        // ✅ Range validation
        if (coldRoom.getMinAllowed() >= coldRoom.getMaxAllowed()) {
            throw new IllegalArgumentException("Temperature range invalid");
        }

        return coldRoomRepository.save(coldRoom);
    }

    @Override
    public List<ColdRoom> getAllColdRooms() {
        return coldRoomRepository.findAll();
    }

    @Override
    public ColdRoom getColdRoomById(Long id) {
        return coldRoomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("ColdRoom not found"));
    }
}
