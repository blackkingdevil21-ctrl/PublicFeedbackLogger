package com.feedback.demo.controller;

import com.feedback.demo.entity.Feedback;
import com.feedback.demo.entity.Officer;
import com.feedback.demo.service.FeedbackService;
import com.feedback.demo.service.OfficerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/officer")
public class OfficerController {
    
    @Autowired
    private OfficerService officerService;
    
    @Autowired
    private FeedbackService feedbackService;
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "officer/login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username,
                       @RequestParam String password,
                       HttpSession session,
                       Model model) {
        Optional<Officer> officerOpt = officerService.getOfficerByUsername(username);
        
        if (officerOpt.isPresent()) {
            Officer officer = officerOpt.get();
            if (officerService.validatePassword(password, officer.getPassword())) {
                session.setAttribute("officer", officer);
                return "redirect:/officer/dashboard";
            }
        }
        
        model.addAttribute("error", "Invalid credentials");
        return "officer/login";
    }
    
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Officer officer = (Officer) session.getAttribute("officer");
        if (officer == null) {
            return "redirect:/officer/login";
        }
        
        List<Feedback> feedbacks = feedbackService.getOfficerFeedbacks(officer);
        model.addAttribute("officer", officer);
        model.addAttribute("feedbacks", feedbacks);
        return "officer/dashboard";
    }
    
    @GetMapping("/feedback/{id}")
    public String viewFeedback(@PathVariable Long id, HttpSession session, Model model) {
        Officer officer = (Officer) session.getAttribute("officer");
        if (officer == null) {
            return "redirect:/officer/login";
        }
        
        Optional<Feedback> feedback = feedbackService.getFeedbackById(id);
        if (feedback.isPresent()) {
            model.addAttribute("feedback", feedback.get());
            return "officer/feedback-details";
        }
        
        return "redirect:/officer/dashboard";
    }
    
    @PostMapping("/feedback/{id}/update-status")
    public String updateStatus(@PathVariable Long id, 
                              @RequestParam String status,
                              HttpSession session) {
        Officer officer = (Officer) session.getAttribute("officer");
        if (officer == null) {
            return "redirect:/officer/login";
        }
        
        feedbackService.updateFeedbackStatus(id, status);
        return "redirect:/officer/feedback/" + id;
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
