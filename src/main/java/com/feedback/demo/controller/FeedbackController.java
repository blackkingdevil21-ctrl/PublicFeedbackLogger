package com.feedback.demo.controller;

import com.feedback.demo.entity.Feedback;
import com.feedback.demo.entity.User;
import com.feedback.demo.service.FeedbackService;
import com.feedback.demo.service.FileStorageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    
    @Autowired
    private FeedbackService feedbackService;
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @GetMapping("/submit")
    public String showSubmitPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        return "user/submit-feedback";
    }
    
    @PostMapping("/submit")
    public String submitFeedback(@RequestParam String title,
                                @RequestParam String description,
                                @RequestParam(required = false) String location,
                                @RequestParam(required = false) Double latitude,
                                @RequestParam(required = false) Double longitude,
                                @RequestParam(required = false) MultipartFile media,
                                @RequestParam(required = false) Boolean isSensitive,
                                HttpSession session,
                                Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        String mediaPath = null;
        if (media != null && !media.isEmpty()) {
            mediaPath = fileStorageService.storeFile(media);
        }
        
        Feedback feedback = feedbackService.createFeedback(
            user, title, description, location, latitude, longitude, 
            mediaPath, isSensitive != null && isSensitive
        );
        
        model.addAttribute("success", true);
        model.addAttribute("feedbackId", feedback.getId());
        return "user/submit-feedback";
    }
    
    @GetMapping("/track")
    public String trackFeedback(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        List<Feedback> feedbacks = feedbackService.getUserFeedbacks(user);
        model.addAttribute("feedbacks", feedbacks);
        return "user/track-feedback";
    }
}
