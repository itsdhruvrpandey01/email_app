package com.aurionpro.email.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
	private List<String> receiverEmails;
	private List<String> ccEmails;
	private List<String> bccEmails;
	private String subject;
	private String content;
}
