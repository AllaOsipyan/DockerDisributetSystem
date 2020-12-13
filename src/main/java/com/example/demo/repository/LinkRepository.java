package com.example.demo.repository;

import com.example.demo.models.Link;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface LinkRepository extends CrudRepository<Link, Long> {
    @Query(value = "SELECT * FROM link WHERE id=:linkId", nativeQuery = true)
    public Link findLinkById(@Param("linkId") Long linkId);
}
