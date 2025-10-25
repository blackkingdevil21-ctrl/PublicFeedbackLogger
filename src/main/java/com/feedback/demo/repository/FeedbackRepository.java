package com.feedback.demo.repository;

import com.feedback.demo.entity.Feedback;
import com.feedback.demo.entity.Officer;
import com.feedback.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUser(User user);
    List<Feedback> findByOfficer(Officer officer);
    List<Feedback> findByDomain(String domain);
    List<Feedback> findByUserAndIsSensitive(User user, Boolean isSensitive);
}
