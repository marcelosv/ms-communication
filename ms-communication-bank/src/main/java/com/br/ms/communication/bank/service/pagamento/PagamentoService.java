package com.br.ms.communication.bank.service.pagamento;

import javax.transaction.Transactional;

import com.br.ms.communication.bank.domain.Pagamento;
import com.br.ms.communication.bank.exceptions.PagamentoException;
import com.br.ms.communication.bank.gateway.json.PagamentoJson;
import com.br.ms.communication.bank.gateway.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ms.communication.bank.service.cartao.CartaoService;

@Service
public class PagamentoService{

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private CartaoService cartaoService;
	
	@Transactional
	public void pagamento(PagamentoJson pagamentoJson){
	
		if( !cartaoService.isValido(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao()) ){
			throw new PagamentoException("Cartão inválido.");
		}
		
		if( !cartaoService.isSaldoSuficiente(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao(), pagamentoJson.getValorCompra()) ){
			throw new PagamentoException("Cartão não possui saldo suficiente.");
		}

		Pagamento pagamento = new Pagamento();
		pagamento.setValorCompra(pagamentoJson.getValorCompra());
		pagamento.setCartao(cartaoService.getCartao(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao()));
		
		pagamentoRepository.save(pagamento);
		
		cartaoService.atualizarSaldo(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao(), pagamentoJson.getValorCompra());
	}
	
}
