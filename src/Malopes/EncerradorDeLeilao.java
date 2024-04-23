package Malopes;

import java.util.Calendar;
import java.util.List;

public class EncerradorDeLeilao {
    private int total = 0;
    private LeilaoDao dao;

    public EncerradorDeLeilao(LeilaoDao dao) {
        this.dao = dao;
    }
    //encerra leiloes que começaram a mais de uma semana
    public void encerra(){
        List<Leilao> todosLeiloesCorrentes = dao.correntes();
        
        for(Leilao leilao : todosLeiloesCorrentes){
            if(comecouSemanaPassada(leilao)){
                leilao.encerra();
                total++;
                dao.atualiza(leilao);
            }
        }
    }

    //retorna true se o leilao começou a mais de uma semana
    private boolean comecouSemanaPassada(Leilao leilao) {
        Calendar umaSemanaAtras = Calendar.getInstance();
        umaSemanaAtras.add(Calendar.WEEK_OF_YEAR, -1);
        return leilao.getData().before(umaSemanaAtras);
    }

    //retorna a quantidade de dias entre duas datas
    private int diasEntre(Calendar inicio, Calendar fim) {
        Calendar data = (Calendar) inicio.clone();
        int diasNoIntervalo = 0;
        while (data.before(fim)) {
            data.add(Calendar.DAY_OF_MONTH, 1);
            diasNoIntervalo++;
        }
        return diasNoIntervalo;
    }

    //retorna a quantidade de leiloes encerrados
    public int getTotalEncerrados() {
        return total;
    }


}
