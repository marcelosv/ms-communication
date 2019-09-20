package com.br.ms.communication.buytrip.gateway.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraChaveJson {

    private String chave;
    private CompraJson compraJson;

}
