package papelaria.model;

import papelaria.enums.StatusFornecedor;
import papelaria.exception.CnpjInvalidoException;
import papelaria.exception.IdInvalidoException;

import java.util.HashSet;
import java.util.Set;

public class Fornecedor extends Pessoa {

    private int id;
    private String cnpj;
    private String email;
    private final Set<Categoria> categoriasFornecidas;
    private StatusFornecedor status;

    public Fornecedor(int id, String nome, String cnpj, String telefone, String email) throws IdInvalidoException, CnpjInvalidoException {

        super(nome, telefone);

        setId(id);
        setCnpj(cnpj);
        setEmail(email);
        setTelefone(telefone);

        this.status = StatusFornecedor.ATIVO;
        this.categoriasFornecidas = new HashSet<>();
    }

    public int getIdFornecedor() {
        return id;
    }

    public void setId(int id) throws IdInvalidoException {
        if (id <= 0) {
            throw new IdInvalidoException("Id deve ser maior que 0");
        }

        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) throws CnpjInvalidoException {
        if (cnpj == null || cnpj.trim().length() < 14) {
            throw new CnpjInvalidoException("CNPJ inválido");
        }

        this.cnpj = cnpj.trim();
    }

    @Override
    public void setTelefone(String telefone) {
        if (telefone == null) {
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }

        String telefoneLimpo = telefone.replaceAll("\\D", "");

        if (telefoneLimpo.length() < 10 || telefoneLimpo.length() > 11) {
            throw new IllegalArgumentException("Número de telefone inválido");
        }

        this.telefone = telefoneLimpo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validarTexto(email, "E-mail");
    }

    public StatusFornecedor getStatus() {
        return status;
    }

    public boolean estaAtivo() {
        return this.status == StatusFornecedor.ATIVO;
    }

    public void ativar() {
        this.status = StatusFornecedor.ATIVO;
    }

    public void desativar() {
        this.status = StatusFornecedor.INATIVO;
    }

    public void adicionarCategoria(Categoria categoria) {
        categoriasFornecidas.add(categoria);
    }

    public void removerCategoria(Categoria categoria) {
        categoriasFornecidas.remove(categoria);
    }

    public boolean forneceCategoria(Categoria categoria) {
        return categoriasFornecidas.contains(categoria);
    }

    public String paraArquivo() {
        return id + ";" + nome + ";" + cnpj + ";" + telefone + ";" + email + ";" + status;
    }

    @Override
    public String getTipo() {
        return "Fornecedor";
    }

    @Override
    public String toString() {
        return "------------------------------------------------" +
                "\nTipo: " + getTipo() +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nCNPJ: " + cnpj +
                "\nTelefone: " + telefone +
                "\nEmail: " + email +
                "\nStatus: " + status +
                "\n------------------------------------------------";
    }
}