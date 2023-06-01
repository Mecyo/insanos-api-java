package com.mecyo.spring.common;

import com.mecyo.spring.domain.model.Cliente;

import lombok.Data;

@Data
public class SendEmail {

	private String toName;
	private String fromName;
	private String fromEmailAddress;
	private String subject;
	private String toEmail;
	private String message;

	public SendEmail(Cliente cliente, String assunto) {
		this.toEmail = cliente.getEmail();
		this.toName = cliente.getNome();
		// this.fromName = cliente.getfromName
		// this.fromEmailAddress = cliente.getfromEmailAddress
		this.subject = assunto;
		this.message = "Olá, " + cliente.getNome()
				+ "!/nEstamos melhorando o sistema de cadastro para torneios e geramos a sua senha de acesso: "
				+ cliente.getSenha()
				+ "./nUtilize o seu e-mail de cadastro, juntamente com a senha informada, para efetuar login e se cadastrar para os próximos torneios.";
	}
}
