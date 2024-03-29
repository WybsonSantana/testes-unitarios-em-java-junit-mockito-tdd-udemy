package br.dev.s2w.testes.unitarios.servicos;

import br.dev.s2w.testes.unitarios.entidades.Filme;
import br.dev.s2w.testes.unitarios.entidades.Locacao;
import br.dev.s2w.testes.unitarios.entidades.Usuario;
import br.dev.s2w.testes.unitarios.exception.FilmeSemEstoqueException;
import br.dev.s2w.testes.unitarios.exception.LocadoraException;
import br.dev.s2w.testes.unitarios.utils.DataUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.dev.s2w.testes.unitarios.matchers.MatchersProprios.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class LocacaoServiceTest {

    private LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        service = new LocacaoService();
    }

    @Test
    public void deveAlugarFilme() throws Exception {
        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
        // Cenário
        Usuario usuario = new Usuario("Usuário 1");
        List<Filme> filmes = List.of(new Filme("Filme 1", 1, 5.0));

        // Ação
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificação
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(locacao.getDataLocacao(), eHoje());
        error.checkThat(locacao.getDataRetorno(), eHojeComDiferencaDias(1));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void NaoDeveAlugarFilmeSemEstoque() throws Exception {
        // Cenário
        Usuario usuario = new Usuario("Usuário 1");
        List<Filme> filmes = List.of(new Filme("Filme 1", 0, 4.0));

        // Ação
        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
        // Cenário
        List<Filme> filmes = List.of(new Filme("Filme 1", 1, 5.0));

        // Ação
        try {
            service.alugarFilme(null, filmes);
            Assert.fail();
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), is("Usuário vazio"));
        }
    }

    @Test
    public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
        // Cenário
        Usuario usuario = new Usuario("Usuário 1");

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        // Ação
        service.alugarFilme(usuario, null);
    }

    @Test
    public void deveDevolverFilmeNaSegundaSeAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
        // Cenário
        Usuario usuario = new Usuario("Usuário 1");
        List<Filme> filmes = List.of(new Filme("Filme 1", 1, 5.0));

        // Ação
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificação
        Assert.assertThat(locacao.getDataRetorno(), caiNumaSegunda());
    }


}