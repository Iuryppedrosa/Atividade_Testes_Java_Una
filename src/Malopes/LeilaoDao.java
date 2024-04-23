package Malopes;

import java.util.ArrayList;
import java.util.List;



public class LeilaoDao {

    private List<Leilao> leiloes = new ArrayList<>();

    public List<Leilao> correntes() {
        return new ArrayList<>(leiloes);
    }

    public void atualiza(Leilao leilao) {
        int index = leiloes.indexOf(leilao);
        if(index != -1) {
            leiloes.set(index, leilao);
        }
    }

    public void salva(Leilao leilao) {
        leiloes.add(leilao);
    }
}
