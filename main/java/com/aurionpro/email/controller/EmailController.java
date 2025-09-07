package com.aurionpro.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.email.entity.Email;
import com.aurionpro.email.service.EmailService;

@RestController
@RequestMapping("/app/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping
	public ResponseEntity<String> sendEmail(@RequestBody Email email) {
		boolean isSent = emailService.sendEmail(email);

		if (isSent) {
			return ResponseEntity.ok("Email sent successfully.");
		} else {
			return ResponseEntity.status(500).body("Failed to send email.");
		}
	}
}
