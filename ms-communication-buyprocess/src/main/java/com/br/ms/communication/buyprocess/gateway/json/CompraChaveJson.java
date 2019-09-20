package com.br.ms.communication.buyprocess.gateway.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompraChaveJson {

	private String chave;
	private CompraJson compraJson;
}
