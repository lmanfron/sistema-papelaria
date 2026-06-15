package papelaria.model;

public abstract class Pagamento {
    private int id;
    private double valor;
    private String status;

    public Pagamento(int id, double valor, String status) {
        this.id = id;
        this.valor = valor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public abstract void processarPagamento();

    @Override
    public String toString() {
        return "ID: " + id +
                ", Valor: " + valor +
                ", Status: " + status;
    }
}
