package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ColdRoom;

public interface ColdRoomRepository extends JpaRepository<ColdRoom, Long> {
}
