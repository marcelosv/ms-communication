package com.br.ms.communication.buyprocess.service.bank;

import java.io.IOException;

import com.br.ms.communication.buyprocess.gateway.json.BankRetornoJson;
import com.br.ms.communication.buyprocess.gateway.json.PagamentoJson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.br.ms.communication.buyprocess.gateway.json.CompraChaveJson;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BankService {

	@Value("${bank.link}")
	private String link;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public PagamentoRetorno pagar(CompraChaveJson compraChaveJson) throws IOException {
		
		PagamentoJson json = new PagamentoJson();
		json.setNroCartao(compraChaveJson.getCompraJson().getNroCartao());
		json.setCodigoSegurancaCartao(compraChaveJson.getCompraJson().getCodigoSegurancaCartao());
		json.setValorCompra(compraChaveJson.getCompraJson().getValorPassagem());
		
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PagamentoJson> entity = new HttpEntity<PagamentoJson>(json, headers);

		try {
			ResponseEntity<BankRetornoJson> bankRetorno = restTemplate.exchange(link, HttpMethod.POST, entity, BankRetornoJson.class);
			return new PagamentoRetorno(bankRetorno.getBody().getMensagem(), true);
		}catch(HttpClientErrorException ex){
			if( ex.getStatusCode() == HttpStatus.BAD_REQUEST ) {
				ObjectMapper mapper = new ObjectMapper();
				BankRetornoJson obj = mapper.readValue(ex.getResponseBodyAsString(), BankRetornoJson.class);
				return new PagamentoRetorno(obj.getMensagem(), false);
			}
			throw ex;
		}catch (RuntimeException ex) {
			throw ex;
		}

	}

}
