package com.example.myth.mythBuster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myth.mythBuster.model.Myth;
import java.util.List;

@Repository
public interface MythRepository extends JpaRepository<Myth, Long> {

    // New: fetch myths by user id
    List<Myth> findByUserId(Long userId);
}
