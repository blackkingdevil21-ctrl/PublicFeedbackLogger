package com.feedback.demo.repository;

import com.feedback.demo.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OfficerRepository extends JpaRepository<Officer, Long> {
    Optional<Officer> findByUsername(String username);
    Optional<Officer> findByDomain(String domain);
    boolean existsByUsername(String username);
}
