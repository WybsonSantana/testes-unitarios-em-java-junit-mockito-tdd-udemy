package br.dev.s2w.testes.unitarios.servicos;

import br.dev.s2w.testes.unitarios.exception.NaoPodeDividirPorZeroException;

public class Calculadora {

    public int somar(int a, int b) {
        return a + b;
    }

    public int subtrair(int a, int b) {
        return a - b;
    }

    public int dividir(int a, int b) throws NaoPodeDividirPorZeroException {
        if (b == 0) {
            throw new NaoPodeDividirPorZeroException();
        }
        return a / b;
    }

    public int multiplicar(int a, int b) {
        return a * b;
    }
}
