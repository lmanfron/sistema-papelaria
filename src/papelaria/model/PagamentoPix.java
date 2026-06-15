package papelaria.model;

public class PagamentoPix extends papelaria.model.Pagamento {
    private String chavePix;

    public PagamentoPix(int id, double valor, String status, String chavePix) {
        super(id, valor, status);
        this.chavePix = chavePix;
    }

    public String getChavePix() {
        return chavePix;
    }

    @Override
    public void processarPagamento(){
        System.out.println("Pagamento pix realizado");
    }
    @Override
    public String toString() {
        return super.toString() +
                ", Chave Pix: " + chavePix;
    }
}
