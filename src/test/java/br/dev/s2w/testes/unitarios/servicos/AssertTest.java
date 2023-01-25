package br.dev.s2w.testes.unitarios.servicos;

import br.dev.s2w.testes.unitarios.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void test() {
        Assert.assertTrue(true);
        Assert.assertFalse(false);

        Assert.assertEquals("Erro de comparação", 1, 1);
        Assert.assertEquals(0.51234, 0.512, 0.001);
        Assert.assertEquals(Math.PI, 3.14, 0.01);

        int number1 = 5;
        Integer number2 = 5;
        Assert.assertEquals(Integer.valueOf(number1), number2);
        Assert.assertEquals(number1, number2.intValue());

        Assert.assertEquals("bola", "bola");
        Assert.assertNotEquals("bola", "casa");
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("bola".startsWith("bo"));

        Usuario usuario1 = new Usuario("Usuário 1");
        Usuario usuario2 = new Usuario("Usuário 1");
        Usuario usuario3 = null;

        Assert.assertEquals(usuario1, usuario2);

        Assert.assertSame(usuario2, usuario2);
        Assert.assertNotSame(usuario1, usuario2);

        Assert.assertNull(usuario3);
        Assert.assertNotNull(usuario1);
    }
}
