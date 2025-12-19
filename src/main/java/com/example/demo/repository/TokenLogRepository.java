package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TokenLog;

@Repository
public interface TokenLogRepository extends JpaRepository<TokenLog, Long> {
    List<TokenLog> findByTokenId(Long tokenId);
}
