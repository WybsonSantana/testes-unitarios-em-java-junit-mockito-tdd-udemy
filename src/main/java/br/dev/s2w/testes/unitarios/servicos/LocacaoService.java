package br.dev.s2w.testes.unitarios.servicos;

import br.dev.s2w.testes.unitarios.entidades.Filme;
import br.dev.s2w.testes.unitarios.entidades.Locacao;
import br.dev.s2w.testes.unitarios.entidades.Usuario;
import br.dev.s2w.testes.unitarios.utils.DataUtils;

import java.util.Date;

import static br.dev.s2w.testes.unitarios.utils.DataUtils.adicionarDias;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, Filme filme) {
        Locacao locacao = new Locacao();
        locacao.setFilme(filme);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(filme.getPrecoLocacao());

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return locacao;
    }

    public static void main(String[] args) {
        // Cenário
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = new Usuario("Usuário 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

// Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filme);

// Verificação
        System.out.println(locacao.getValor() == 5.0);
        System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }
}