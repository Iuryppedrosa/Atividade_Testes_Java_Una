package Malopes.Testes;

import Malopes.Avaliador;
import Malopes.Lance;
import Malopes.Leilao;
import Malopes.Usuario;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AvaliadorTest {
    @Test
    public void testMaiorLance() {
        Avaliador avaliador = new Avaliador();
        Leilao leilao = new Leilao("PlayStation 5");

        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        leilao.propoe(new Lance(jose, 200.0));

        leilao.propoe(new Lance(maria, 500.0));

        leilao.propoe(new Lance(joao, 1000.0));
        avaliador.avalia(leilao);

        double maiorLance = avaliador.getMaiorLance();
        assertEquals(1000.0, maiorLance, 0.0);
    }

    @Test
    public void testMenorLance() {
        Avaliador avaliador = new Avaliador();
        Leilao leilao = new Leilao("PlayStation 5");

        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        leilao.propoe(new Lance(jose, 200.0));

        leilao.propoe(new Lance(maria, 500.0));

        leilao.propoe(new Lance(joao, 1000.0));

        avaliador.avalia(leilao);
        double menorLance = avaliador.getMenorDeTodos();

        assertEquals(200.0, menorLance, 0.0);
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria, 250.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(jose, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        Assert.assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(250.0, leiloeiro.getMenorDeTodos(), 0.0001);
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescenteComOutrosValores() {
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria, 1000.0));
        leilao.propoe(new Lance(joao, 2000.0));
        leilao.propoe(new Lance(jose, 3000.0));

        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);

        Assert.assertEquals(3000, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(1000, leiloeiro.getMenorDeTodos(), 0.0001);
    }

    @Test
    public void testAvaliaComListaDeLancesNula() {
        Leilao leilao = new Leilao("Playstation 3 Novo");
        leilao.setLances(null);

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao); // Supondo que o método avalia esteja em escopo acessível

        // Verifica se as variáveis maiorDeTodos e menorDeTodos não foram alteradas
        Assert.assertEquals(0, leiloeiro.getMenorDeTodos(), 0.0001);
        Assert.assertEquals(0, leiloeiro.getMenorDeTodos(), 0.0001);
    }
}
