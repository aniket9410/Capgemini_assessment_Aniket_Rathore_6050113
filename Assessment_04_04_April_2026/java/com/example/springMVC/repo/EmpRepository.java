package com.example.springMVC.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springMVC.entity.Emp;


public interface EmpRepository extends JpaRepository<Emp, Integer> {
}
