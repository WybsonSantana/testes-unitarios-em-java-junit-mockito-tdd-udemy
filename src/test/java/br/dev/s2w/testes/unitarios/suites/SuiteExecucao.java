package br.dev.s2w.testes.unitarios.suites;

import br.dev.s2w.testes.unitarios.servicos.CalculadoraTest;
import br.dev.s2w.testes.unitarios.servicos.CalculoValorLocacaoTest;
import br.dev.s2w.testes.unitarios.servicos.LocacaoServiceTest;
import org.junit.runners.Suite.SuiteClasses;

//@RunWith(Suite.class)
@SuiteClasses({
        CalculadoraTest.class,
        CalculoValorLocacaoTest.class,
        LocacaoServiceTest.class
})
public class SuiteExecucao {
}
