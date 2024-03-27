package Malopes;

import org.junit.Test;

import java.util.List;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;

    public void avalia(Leilao leilao) {

        for (Lance lance : leilao.getLances()) {
            if (leilao.getLances() != null && !leilao.getLances().isEmpty()) {

                double valor = lance.getValor();
                if (valor > maiorDeTodos) {
                    maiorDeTodos = valor;
                }
                if (valor < menorDeTodos) {
                    menorDeTodos = valor;
                }

            }

        }
        if (leilao.getLances() == null || leilao.getLances().isEmpty()) {
            maiorDeTodos = 0.0;
            menorDeTodos = 0.0;
        }
    }
    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorDeTodos() {
        return menorDeTodos;
    }
}

