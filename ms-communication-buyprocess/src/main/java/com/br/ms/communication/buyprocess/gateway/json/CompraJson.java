package com.br.ms.communication.buyprocess.gateway.json;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraJson {

	private Integer codigoPassagem;
	
	private Integer nroCartao;
	
	private Integer codigoSegurancaCartao;
	
	private BigDecimal valorPassagem;

}
