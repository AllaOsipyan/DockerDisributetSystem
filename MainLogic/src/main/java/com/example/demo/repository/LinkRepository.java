package com.example.demo.repository;

import com.example.demo.models.Link;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface LinkRepository extends CrudRepository<Link, Long> {
    @Query(value = "SELECT * FROM status WHERE id=:linkId", nativeQuery = true)
    public Link findLinkById(@Param("linkId") Long linkId);

    @Modifying
    @Query(value = "UPDATE status SET status = :newStatus WHERE id = :linkId", nativeQuery = true)
    public void updateStatus(@Param("linkId") Long linkId, @Param("newStatus") int newStatus);
}
