package com.br.ms.communication.bank.gateway.json;

public class RetornoJson {
	
	private String mensagem;

	public RetornoJson() {
	}
	
	public RetornoJson(String message) {
		mensagem = message;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
