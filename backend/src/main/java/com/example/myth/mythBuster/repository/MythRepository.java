package com.example.myth.mythBuster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myth.mythBuster.model.Myth;

@Repository
public interface MythRepository extends JpaRepository<Myth, Long> {

}