package com.br.ms.communication.buyprocess.gateway.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraFinalizadaJson {

    private CompraChaveJson compraChaveJson;
    private String mensagem;
    private boolean pagamentoOK;
}
