package com.br.ms.communication.bank.gateway.repository;

import java.math.BigDecimal;

import com.br.ms.communication.bank.domain.Cartao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface CartaoRepository extends Repository<Cartao, Long>{

	@Query("select count(obj.id) from Cartao obj where obj.codigoSegurancaCartao = ?1 and obj.nroCartao = ?2")
	Integer findCartaoValido(Integer codigoSegurancaCartao, Integer nroCartao);

	@Query("select count(obj.id) from Cartao obj where obj.codigoSegurancaCartao = ?1 and obj.nroCartao = ?2 and obj.valorCredito >= ?3")
	Integer isSaldoSuficiente(Integer codigoSegurancaCartao, Integer nroCartao, BigDecimal valorCompra);

	@Query("from Cartao obj where obj.codigoSegurancaCartao = ?1 and obj.nroCartao = ?2")
	Cartao findCartao(Integer codigoSegurancaCartao, Integer nroCartao);

	@Modifying
	@Query("update Cartao set valorCredito = (valorCredito - ?3) where codigoSegurancaCartao = ?1 and nroCartao = ?2 ")
	void atualizarSaldo(Integer codigoSegurancaCartao, Integer nroCartao, BigDecimal valorCompra);

}
