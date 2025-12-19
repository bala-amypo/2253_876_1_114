package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TokenLog;

public interface TokenLogRepository
        extends JpaRepository<TokenLog, Long> {

    List<TokenLog> findByTokenIdOrderByLoggedAtAsc(Long tokenId);
}
