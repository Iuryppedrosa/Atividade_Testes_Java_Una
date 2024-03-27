package Malopes;

import Malopes.Avaliador;
import Malopes.Lance;
import Malopes.Leilao;
import Malopes.Usuario;
import org.junit.Test;

class TesteDoAvaliador {
    @Test
    public static void main(String[] args) {
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria,250.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(jose,400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        System.out.println(leiloeiro.getMaiorLance());
        System.out.println(leiloeiro.getMenorDeTodos());
    }
}