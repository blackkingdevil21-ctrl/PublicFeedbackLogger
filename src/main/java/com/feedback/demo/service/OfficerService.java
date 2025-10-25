package com.feedback.demo.service;

import com.feedback.demo.entity.Officer;
import com.feedback.demo.repository.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OfficerService {
    
    @Autowired
    private OfficerRepository officerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Officer createOfficer(String username, String password, String name, 
                                 String email, String domain) {
        Officer officer = new Officer();
        officer.setUsername(username);
        officer.setPassword(passwordEncoder.encode(password));
        officer.setName(name);
        officer.setEmail(email);
        officer.setDomain(domain);
        return officerRepository.save(officer);
    }
    
    public Optional<Officer> getOfficerByUsername(String username) {
        return officerRepository.findByUsername(username);
    }
    
    public Optional<Officer> getOfficerByDomain(String domain) {
        return officerRepository.findByDomain(domain);
    }
    
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
