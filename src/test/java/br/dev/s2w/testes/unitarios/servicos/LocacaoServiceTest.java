package br.dev.s2w.testes.unitarios.servicos;

import br.dev.s2w.testes.unitarios.entidades.Filme;
import br.dev.s2w.testes.unitarios.entidades.Locacao;
import br.dev.s2w.testes.unitarios.entidades.Usuario;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static br.dev.s2w.testes.unitarios.utils.DataUtils.isMesmaData;
import static br.dev.s2w.testes.unitarios.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testeLocacao() throws Exception {
        // Cenário
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = new Usuario("Usuário 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        // Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filme);

        // Verificação
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

    @Test(expected = Exception.class)
    public void testeLocacalFilmeSemEstoque() throws Exception {
        // Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuário 1");
        Filme filme = new Filme("Filme 2", 0, 4.0);

        // Ação
        service.alugarFilme(usuario, filme);
    }

    @Test
    public void testeLocacaoFilmeSemEstoque2() {
        // Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuário 1");
        Filme filme = new Filme("Filme 2", 0, 4.0);

        // Ação
        try {
            service.alugarFilme(usuario, filme);
            Assert.fail("Deveria ter lançado uma exceção");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), is("Filme sem estoque"));
        }
    }

    @Test
    public void testeLocacaoFilmeSemEstoque3() throws Exception {
        // Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuário 1");
        Filme filme = new Filme("Filme 2", 0, 4.0);

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque");

        // Ação
        service.alugarFilme(usuario, filme);
    }


}