package com.aurionpro.email.service;

import com.aurionpro.email.entity.Email;

public interface EmailService {
	boolean sendEmail(Email email);
}
