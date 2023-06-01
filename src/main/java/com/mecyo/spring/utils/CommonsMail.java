package com.mecyo.spring.utils;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mecyo.spring.common.SendEmail;

@Component
public class CommonsMail {
	
	@Value("${mail.host}")
	private String host;
	@Value("${mail.smtp.port}")
	private String smtpPort;
	@Value("${mail.smtp.user}")
	private String smtpUser;
	@Value("${mail.smtp.pass}")
	private String smtpPass;
	@Value("${mail.from-name}")
	private String fromName;
	@Value("${mail.from-email-address}")
	private String fromEmailAddress;
	
	
	/**
	 * Envia email no formato HTML
	 * @throws EmailException 
	 * @throws MalformedURLException 
	 */
	public void enviaEmailFormatoHtml(SendEmail sendMail) throws EmailException, MalformedURLException {
		
		HtmlEmail email = new HtmlEmail();
		
		if(sendMail.getFromName() == null || sendMail.getFromName().isBlank()) {
			sendMail.setFromName(this.fromName);
		}
		
		if(sendMail.getFromEmailAddress() == null || sendMail.getFromEmailAddress().isEmpty()) {
			sendMail.setFromEmailAddress(this.fromEmailAddress);
		}
		
		// configura a mensagem para o formato HTML
		email.setHtmlMsg("&lt;html&gt;Logo do Apache - <img >&lt;/html&gt;");

		// configure uma mensagem alternativa caso o servidor não suporte HTML
		email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
		
		email.setHostName(host); // o servidor SMTP para envio do e-mail
		email.addTo(sendMail.getToEmail(), sendMail.getToName()); //destinatário
		email.setFrom(sendMail.getFromEmailAddress(), sendMail.getFromName()); // remetente
		email.setSubject(sendMail.getSubject()); // assunto do e-mail
		email.setMsg(sendMail.getMessage()); //conteudo do e-mail
		email.setAuthentication(smtpUser, smtpPass);
		email.setSmtpPort(Integer.parseInt(smtpPort));
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		email.send();
	}
}
