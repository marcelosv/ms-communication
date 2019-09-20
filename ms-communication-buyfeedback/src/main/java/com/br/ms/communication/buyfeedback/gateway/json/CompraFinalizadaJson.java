package com.br.ms.communication.buyfeedback.gateway.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompraFinalizadaJson {

    private CompraChaveJson compraChaveJson;
    private String mensagem;
    private boolean pagamentoOK;

}
