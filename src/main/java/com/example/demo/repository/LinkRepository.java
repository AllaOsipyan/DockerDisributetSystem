package com.example.demo.repository;

import com.example.demo.models.Link;
import org.springframework.data.repository.CrudRepository;


public interface LinkRepository extends CrudRepository<Link, Long> {
}
