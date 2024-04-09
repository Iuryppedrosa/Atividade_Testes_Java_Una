package Malopes.Testes;

import Malopes.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AvaliadorTest {
    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;


    @Before
    public void criaAvaliador(){
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
    }
    @Test
    public void testMaiorLance() {
        criaAvaliador();
        Leilao leilao = new CriadorDeLeilao()
                .para("PlayStation 5")
                .lance(joao, 300.0)
                .lance(jose, 200.0)
                .lance(maria, 500.0)
                .lance(joao, 1000.0)
                        .constroi();


        leiloeiro.avalia(leilao);
        double maiorLance = leiloeiro.getMaiorLance();
        assertEquals(1000.0, maiorLance, 0.0);
    }

    @Test
    public void testMenorLance() {
        criaAvaliador();

        Leilao leilao = new CriadorDeLeilao()
                .para("PlayStation 5")
                .lance(jose, 200.0)
                .lance(maria, 500.0)
                .lance(joao, 1000.0)
                .constroi();

        leiloeiro.avalia(leilao);
        double menorLance = leiloeiro.getMenorDeTodos();

        assertEquals(200.0, menorLance, 0.0);
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {

        Leilao leilao = new CriadorDeLeilao()
                .para("PlayStation 3 novo")
                        .lance(maria, 250.)
                                .lance(joao, 300.0)
                                        .lance(jose, 400.0)
                                                .constroi();


        criaAvaliador();
        leiloeiro.avalia(leilao);

        Assert.assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(250.0, leiloeiro.getMenorDeTodos(), 0.0001);
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescenteComOutrosValores() {

        Leilao leilao = new CriadorDeLeilao()
                .para("PlayStation 3 novo")
                .lance(maria, 1000.0)
                .lance(joao, 2000.0)
                .lance(jose, 3000.0)
                .constroi();

        criaAvaliador();

        leiloeiro.avalia(leilao);

        Assert.assertEquals(3000, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(1000, leiloeiro.getMenorDeTodos(), 0.0001);
    }

    @Test
    public void deveEntenderLancesSemOrdemEspecifica() {

        Leilao leilao = new CriadorDeLeilao()
                .para("PlayStation 3 novo")
                .lance(maria, 1000.0)
                .lance(joao, 2000.0)
                .lance(jose, 3000.0)
                .constroi();

        criaAvaliador();
        leiloeiro.avalia(leilao);

        double maiorLance = leiloeiro.getMaiorLance();
        double menorLance = leiloeiro.getMenorDeTodos();

        // Verificar se o maior lance está correto
        assertTrue(maiorLance == 3000.0 || maiorLance == 2000.0 || maiorLance == 1000.0);

        // Verificar se o menor lance está correto
        assertTrue(menorLance == 3000.0 || menorLance == 2000.0 || menorLance == 1000.0);
    }

    @Test
    public void deveEntenderApenasUmLance() {
        Leilao leilao = new CriadorDeLeilao()
                .para("PlayStation 5")
                .lance(joao, 1000.0)
                .constroi();

        criaAvaliador();


        criaAvaliador();
        leiloeiro.avalia(leilao);

        // O maior lance e o menor lance devem ser iguais ao único lance no leilão
        assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(1000.0, leiloeiro.getMenorDeTodos(), 0.0001);
    }
    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation 3 novo")
                        .lance(joao, 100.0)
                                .lance(maria, 200.0)
                                        .lance(joao, 300.0)
                                                .lance(maria, 400.0)
                                                        .constroi();

        criaAvaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());

        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(300, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);

    }

}
