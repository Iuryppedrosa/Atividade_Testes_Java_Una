package Malopes.Testes;

import Malopes.CriadorDeLeilao;
import Malopes.EncerradorDeLeilao;
import Malopes.Leilao;
import Malopes.LeilaoDao;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;

public class EncerradorDeLeilaoTest {
    @Test
    public void deveEncerrarLeiloesQueComecaramUmaSemanaAntes() {
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);
        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")

                .naData(antiga)
                .constroi();

        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira")

                .naData(antiga)
                .constroi();

        // mas como passo os leilões criados para o EncerradorDeLeilao,
        // já que ele os busca no DAO?

        LeilaoDao dao = new LeilaoDao();
        dao.salva(leilao1);
        dao.salva(leilao2);


        EncerradorDeLeilao encerrador = new EncerradorDeLeilao(dao);
        encerrador.encerra();


        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
    }
}
