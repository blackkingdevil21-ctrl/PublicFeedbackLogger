package com.feedback.demo.service;

import com.feedback.demo.entity.Feedback;
import com.feedback.demo.entity.Officer;
import com.feedback.demo.entity.User;
import com.feedback.demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @Autowired
    private MLClassifierService mlClassifierService;
    
    @Autowired
    private OfficerService officerService;
    
    @Autowired
    private EmailService emailService;
    
    public Feedback createFeedback(User user, String title, String description, 
                                   String location, Double latitude, Double longitude,
                                   String mediaPath, Boolean isSensitive) {
        String domain = mlClassifierService.classifyText(description);
        
        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setTitle(title);
        feedback.setDescription(description);
        feedback.setDomain(domain);
        feedback.setLocation(location);
        feedback.setLatitude(latitude);
        feedback.setLongitude(longitude);
        feedback.setMediaPath(mediaPath);
        feedback.setIsSensitive(isSensitive != null && isSensitive);
        
        Optional<Officer> officer = officerService.getOfficerByDomain(domain);
        if (officer.isPresent()) {
            feedback.setOfficer(officer.get());
            emailService.sendNewFeedbackNotification(officer.get(), feedback);
        }
        
        return feedbackRepository.save(feedback);
    }
    
    public List<Feedback> getUserFeedbacks(User user) {
        return feedbackRepository.findByUser(user);
    }
    
    public List<Feedback> getOfficerFeedbacks(Officer officer) {
        return feedbackRepository.findByOfficer(officer);
    }
    
    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }
    
    public Feedback updateFeedbackStatus(Long feedbackId, String status) {
        Optional<Feedback> feedbackOpt = feedbackRepository.findById(feedbackId);
        if (feedbackOpt.isPresent()) {
            Feedback feedback = feedbackOpt.get();
            feedback.setStatus(Feedback.FeedbackStatus.valueOf(status));
            
            if ("RESOLVED".equals(status)) {
                feedback.setResolvedAt(LocalDateTime.now());
                emailService.sendResolutionNotification(feedback.getUser(), feedback);
            }
            
            return feedbackRepository.save(feedback);
        }
        return null;
    }
}
