package papelaria.model;

public class Pedido {
    private int id;
    private String fornecedor;
    private Produto produto;
    private int quantidadeSolicitada;
    private String status;

    public Pedido(int id, String fornecedor, Produto produto, int quantidadeSolicitada, String status) {
        setId(id);
        setFornecedor(fornecedor);
        setProduto(produto);
        setQuantidadeSolicitada(quantidadeSolicitada);
        setStatus(status);
    }

    public int getId() { return id; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("Id deve ser maior que zero.");
        this.id = id;
    }

    public String getFornecedor() { return fornecedor; }

    public void setFornecedor(String fornecedor) {
        if (fornecedor == null || fornecedor.trim().isEmpty()) {
            throw new IllegalArgumentException("Fornecedor e obrigatorio.");
        }
        this.fornecedor = fornecedor.trim();
    }

    public Produto getProduto() { return produto; }

    public void setProduto(Produto produto) {
        if (produto == null) throw new IllegalArgumentException("Produto e obrigatorio.");
        this.produto = produto;
    }

    public int getQuantidadeSolicitada() { return quantidadeSolicitada; }

    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        if (quantidadeSolicitada <= 0) {
            throw new IllegalArgumentException("Quantidade solicitada deve ser maior que zero.");
        }
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status e obrigatorio.");
        }
        this.status = status.trim();
    }

    public String paraArquivo() {
        return id + ";" + fornecedor + ";" + produto.getCodigo() + ";" + quantidadeSolicitada + ";" + status;
    }

    @Override
    public String toString() {
        return "Id: " + id
                + " | Fornecedor: " + fornecedor
                + " | Produto: " + produto.getNome()
                + " | Quantidade solicitada: " + quantidadeSolicitada
                + " | Status: " + status;
    }
}
