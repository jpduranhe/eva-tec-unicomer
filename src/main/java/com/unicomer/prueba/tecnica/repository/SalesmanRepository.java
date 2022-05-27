package com.unicomer.prueba.tecnica.repository;

import com.unicomer.prueba.tecnica.entity.Salesman;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesmanRepository extends JpaRepository<Salesman,Integer> {
    
}