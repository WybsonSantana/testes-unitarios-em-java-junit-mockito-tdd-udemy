package br.dev.s2w.testes.unitarios.servicos;

import br.dev.s2w.testes.unitarios.entidades.Filme;
import br.dev.s2w.testes.unitarios.entidades.Locacao;
import br.dev.s2w.testes.unitarios.entidades.Usuario;
import br.dev.s2w.testes.unitarios.exception.FilmeSemEstoqueException;
import br.dev.s2w.testes.unitarios.exception.LocadoraException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {

    private LocacaoService service;

    @Parameter
    public List<Filme> filmes;

    @Parameter(value = 1)
    public Double valorLocacao;

    @Parameter(value = 2)
    public String cenario;

    @Before
    public void setup() {
        service = new LocacaoService();
    }

    private static Filme filme1 = new Filme("Filme 1", 1, 4.0);
    private static Filme filme2 = new Filme("Filme 2", 1, 4.0);
    private static Filme filme3 = new Filme("Filme 3", 1, 4.0);
    private static Filme filme4 = new Filme("Filme 4", 1, 4.0);
    private static Filme filme5 = new Filme("Filme 5", 1, 4.0);
    private static Filme filme6 = new Filme("Filme 6", 1, 4.0);
    private static Filme filme7 = new Filme("Filme 7", 1, 4.0);

    @Parameters(name = "{2}")
    public static Collection<Object[]> getParameters() {
        return List.of(new Object[][]{
                {List.of(filme1, filme2), 8.0, "Locação de dois filmes: sem desconto"},
                {List.of(filme1, filme2, filme3), 11.0, "Locação de três filmes: 25% de desconto no terceiro filme"},
                {List.of(filme1, filme2, filme3, filme4), 13.0, "Locação de quatro filmes: 50% de desconto no quarto filme"},
                {List.of(filme1, filme2, filme3, filme4, filme5), 14.0, "Locação de cinco filmes: 75% de desconto no quinto filme"},
                {List.of(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "Locação de seis filmes: 100% de desconto no sexto filme"},
                {List.of(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18.0, "Locação de sete filmes: sem desconto no sétimo filme"}
        });
    }

    @Test
    public void deveCalcularValorDaLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
        // Cenário
        Usuario usuario = new Usuario("Usuário 1");

        // Ação
        Locacao resultadoLocacao = service.alugarFilme(usuario, filmes);

        // Verificação
        Assert.assertThat(resultadoLocacao.getValor(), is(valorLocacao));
    }
}
