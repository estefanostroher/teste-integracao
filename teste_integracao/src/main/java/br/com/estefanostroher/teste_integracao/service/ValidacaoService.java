package br.com.estefanostroher.teste_integracao.service;

import org.springframework.stereotype.Service;

import br.com.estefanostroher.teste_integracao.produto.Produto;

@Service
public class ValidacaoService {

    public Boolean validarProduto(Produto produto) {
        return "PAGO".equals(produto.getStatus());
    }
}
