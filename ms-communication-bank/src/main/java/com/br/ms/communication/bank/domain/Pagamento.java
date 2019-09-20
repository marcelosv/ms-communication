package com.br.ms.communication.bank.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pagamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Cartao cartao;

	private BigDecimal valorCompra;

}
