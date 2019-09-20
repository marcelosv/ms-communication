package com.br.ms.communication.buyfeedback.service.finalizar;

import java.io.IOException;

import com.br.ms.communication.buyfeedback.domain.CompraRedis;
import com.br.ms.communication.buyfeedback.gateway.json.CompraFinalizadaJson;
import com.br.ms.communication.buyfeedback.gateway.repository.CompraRedisRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ListenerService {

	@Autowired
	private CompraRedisRepository compraRedisRepository;
	
	@RabbitListener(queues="${fila.finalizado}")
    public void onMessage(Message message) throws JsonParseException, JsonMappingException, IOException  {
		
		String json = new String(message.getBody(), "UTF-8");
		
		System.out.println("Mensagem recebida:"+json);
		
		ObjectMapper mapper = new ObjectMapper();
		CompraFinalizadaJson compraChaveJson = mapper.readValue(json, CompraFinalizadaJson.class);

		CompraRedis credis = new CompraRedis();
		credis.setId(compraChaveJson.getCompraChaveJson().getChave());
		credis.setMensagem(compraChaveJson.getMensagem());
		credis.setNroCartao(compraChaveJson.getCompraChaveJson().getCompraJson().getNroCartao());
		credis.setValorPassagem(compraChaveJson.getCompraChaveJson().getCompraJson().getValorPassagem());
		credis.setCodigoPassagem(compraChaveJson.getCompraChaveJson().getCompraJson().getCodigoPassagem());
		
		System.out.println("Gravando no redis....");
		compraRedisRepository.save(credis);
    }

}