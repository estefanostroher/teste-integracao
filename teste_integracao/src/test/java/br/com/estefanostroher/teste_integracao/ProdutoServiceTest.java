package br.com.estefanostroher.teste_integracao;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.estefanostroher.teste_integracao.produto.Produto;
import br.com.estefanostroher.teste_integracao.repository.ProdutoRepository;
import br.com.estefanostroher.teste_integracao.service.ProdutoService;

@SpringBootTest
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void SalvarNovoProdutoTest() throws SQLException {
        Produto produto = new Produto(1L, "Bicicleta", "PAGO", BigDecimal.valueOf(3000.00));
        this.produtoService.salvarProduto(produto);

        int size = this.produtoRepository.findAll().size();
        String name = this.produtoRepository.findById(1L).get().getNome();

        // Verifica se o produto foi salvo corretamente
        Assertions.assertEquals(1, size);
        Assertions.assertEquals("Bicicleta", name);
    }

    @Test
    public void LançarUmaExceptionAoSalvarProdutoComStatusDiferenteDePagoTest() {
        Produto produto = new Produto(2L, "PlayStation 5", "PENDENTE", BigDecimal.valueOf(4299.99));
        Assertions.assertThrows(SQLException.class, () ->
            this.produtoService.salvarProduto(produto));
    }

    @Test
    public void LançarUmaExceptionAoRemoverProdutoInexistenteTest() {
        Assertions.assertThrows(SQLException.class, () ->
            this.produtoService.removerProduto(2L));
    }
}
