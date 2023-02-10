package br.dev.s2w.testes.unitarios.matchers;

import br.dev.s2w.testes.unitarios.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class DataDiferencaDiasMatcher extends TypeSafeMatcher<Date> {

    private Integer quantidadeDias;

    public DataDiferencaDiasMatcher(Integer quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public void describeTo(Description description) {
// TODO Auto-generated method stub
    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(quantidadeDias));
    }
}
