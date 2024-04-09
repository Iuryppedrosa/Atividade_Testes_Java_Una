package Malopes;

import org.junit.Test;

import java.util.*;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private List<Lance> maiores;

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
        pegaOsMaioresNo(leilao);
    }
    private void pegaOsMaioresNo(Leilao leilao) {
        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
            public int compare(Lance o1, Lance o2) {
                if (o1.getValor() < o2.getValor())
                    return 1;
                if (o1.getValor() > o2.getValor())
                    return -1;
                return 0;
            }
        });
        maiores = maiores.subList(0,
                maiores.size() > 3 ? 3 : maiores.size());
    }
    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorDeTodos() {
        return menorDeTodos;
    }
    public List<Lance> getTresMaiores() {
        return this.maiores;
    }
}

