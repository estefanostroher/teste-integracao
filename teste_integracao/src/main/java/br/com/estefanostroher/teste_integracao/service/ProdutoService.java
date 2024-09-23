package br.com.estefanostroher.teste_integracao.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanostroher.teste_integracao.produto.Produto;
import br.com.estefanostroher.teste_integracao.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ValidacaoService validacaoService;

    public void salvarProduto(Produto produto) throws SQLException {
        if(this.validacaoService.validarProduto(produto)) {
            this.produtoRepository.save(produto);
        } else {
            throw new SQLException("Produto com status diferente de PAGO");
        }
    }

    public void removerProduto(Long id) throws SQLException {
        Optional<Produto> produto = this.produtoRepository.findById(id);
        if(produto.isPresent()) {
            this.produtoRepository.delete(produto.get());
        } else {
            throw new SQLException("Produto não encontrado");
        }
    }
}
