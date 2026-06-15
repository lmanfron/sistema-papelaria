package papelaria.model;

public class PagamentoCartao extends papelaria.model.Pagamento {
    private int parcelas;

    public PagamentoCartao(int id, double valor, String status, int parcelas) {
        super(id, valor, status);
        this.parcelas = parcelas;
    }

    public int getParcelas() {
        return parcelas;
    }

    @Override
    public void processarPagamento(){
        System.out.println("Pagamento via Cartao realizado");
    }
    @Override
    public String toString() {
        return super.toString() +
                ", Parcelas: " + parcelas;
    }
}
