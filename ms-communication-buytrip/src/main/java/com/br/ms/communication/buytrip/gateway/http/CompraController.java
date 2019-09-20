package com.br.ms.communication.buytrip.gateway.http;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.br.ms.communication.buytrip.gateway.json.CompraChaveJson;
import com.br.ms.communication.buytrip.gateway.json.CompraJson;
import com.br.ms.communication.buytrip.gateway.json.RetornoJson;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraController {

	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Value("${fila.saida}")
	private String nomeFila;
	
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<RetornoJson> pagamento(
			@Valid @NotNull @RequestBody CompraJson compraJson) throws Exception  {

		CompraChaveJson compraChaveJson = new CompraChaveJson();
		compraChaveJson.setCompraJson(compraJson);
		compraChaveJson.setChave(UUID.randomUUID().toString());

		ObjectMapper obj = new ObjectMapper();

		String json = obj.writeValueAsString(compraChaveJson);
		
		rabbitTemplate.convertAndSend(nomeFila, json);
		
		RetornoJson retorno = new RetornoJson();
		retorno.setMensagem("Compra registrada com sucesso. Aguarda a confirmação do pagamento.");
		retorno.setChavePesquisa(compraChaveJson.getChave());
		
		return new ResponseEntity<RetornoJson>(retorno, HttpStatus.OK);
	}
	
}