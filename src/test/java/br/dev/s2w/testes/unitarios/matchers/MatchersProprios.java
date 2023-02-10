package br.dev.s2w.testes.unitarios.matchers;

import java.util.Calendar;

public class MatchersProprios {

    public static DiaSemanaMatcher caiEm(Integer diaSemana) {
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNumaSegunda() {
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }

    public static DataDiferencaDiasMatcher eHojeComDiferencaDias(Integer quantidadeDias) {
        return new DataDiferencaDiasMatcher(quantidadeDias);
    }

    public static DataDiferencaDiasMatcher eHoje() {
        return new DataDiferencaDiasMatcher(0);
    }
}
