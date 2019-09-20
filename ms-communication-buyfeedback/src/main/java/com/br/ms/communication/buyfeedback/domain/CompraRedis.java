package com.br.ms.communication.buyfeedback.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("compra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRedis {

	@Id
	private String id;
	private String mensagem;
	
	private Integer codigoPassagem;
	private Integer nroCartao;
	private BigDecimal valorPassagem;
	
	private boolean pagamentoOK;

	
	
}
