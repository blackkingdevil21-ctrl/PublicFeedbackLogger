package com.feedback.demo.service;

import com.feedback.demo.entity.Feedback;
import com.feedback.demo.entity.Officer;
import com.feedback.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    public void sendNewFeedbackNotification(Officer officer, Feedback feedback) {
        System.out.println("==============================================");
        System.out.println("📧 EMAIL NOTIFICATION");
        System.out.println("To: " + officer.getEmail());
        System.out.println("Subject: New Feedback Assigned - " + feedback.getTitle());
        System.out.println("Domain: " + feedback.getDomain());
        System.out.println("==============================================");
    }
    
    public void sendResolutionNotification(User user, Feedback feedback) {
        System.out.println("==============================================");
        System.out.println("📧 EMAIL NOTIFICATION");
        System.out.println("To: " + user.getEmail());
        System.out.println("Subject: Feedback Resolved - " + feedback.getTitle());
        System.out.println("==============================================");
    }
}
