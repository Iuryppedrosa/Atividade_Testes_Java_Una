package Malopes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Leilao {
    private String produto;

    private Calendar data = Calendar.getInstance();

    private List<Lance> lances = new ArrayList<>();

    public enum Status{
        OPEN,
        CLOSED;
    }
    private Status status = Status.OPEN;

    public Leilao(String produto) {
        this.produto = produto;
    }


    public void encerra() {
        if (lances.isEmpty()) {
            throw new RuntimeException("Não é possível encerrar um leilão sem lances");
        }
        setStatus(Status.CLOSED);
    }

    public boolean isEncerrado() {
        return getStatus() == Status.CLOSED;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Calendar getData() {
        return this.data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }


    public void propoe(Lance lance) {
        if (lances.isEmpty()

                || podeDarLance(lance.getUsuario())) {
            lances.add(lance);
        }
    }

    private boolean podeDarLance(Usuario usuario) {
        return !ultimoLanceDado().getUsuario().equals(usuario)
                && qtdDelancesDo(usuario) < 5;

    }

    private Lance ultimoLanceDado() {
        return lances.get(lances.size() - 1);
    }

    private int qtdDelancesDo(Usuario usuario) {
        int total = 0;
        for (Lance lance : lances) {
            if (lance.getUsuario().equals(usuario))
                total++;

        }
        return total;
    }

    public List<Lance> getLances() {
        if(lances == null){
            lances = new ArrayList<Lance>();
        }
        return lances;
    }
    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }
    public String getProduto() {
        return produto;
    }
    public void setProduto(String produto) {
        this.produto = produto;
    }
}
