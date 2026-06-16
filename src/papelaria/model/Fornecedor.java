package papelaria.model;

import papelaria.enums.StatusFornecedor;
import papelaria.exception.CnpjInvalidoException;
import papelaria.exception.IdInvalidoException;
import papelaria.exception.TelefoneInvalidoException;

import java.util.HashSet;
import java.util.Set;

public class Fornecedor {

    private int id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private final Set<Categoria> categoriasFornecidas;
    private StatusFornecedor status;

    public Fornecedor(int id, String nome, String cnpj, String telefone, String email) throws IdInvalidoException, TelefoneInvalidoException, CnpjInvalidoException {

        setId(id);
        setNome(nome);
        setCnpj(cnpj);
        setTelefone(telefone);
        setEmail(email);
        this.status = StatusFornecedor.ATIVO;
        this.categoriasFornecidas = new HashSet<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) throws IdInvalidoException {

        if (id <= 0) {
            throw new IdInvalidoException("Id deve ser maior que 0");
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) throws CnpjInvalidoException {

        if (cnpj.length() < 14) {
            throw new CnpjInvalidoException("CNPJ inválido");
        }
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws TelefoneInvalidoException {

        if (telefone.length() < 10 || telefone.length() > 11) {
            throw new TelefoneInvalidoException("Número de telefone inválido");
        }
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean estaAtivo() {

        return this.status == StatusFornecedor.ATIVO;
    }

    public void ativar() {

        this.status = StatusFornecedor.ATIVO;
    }

    public void destivar() {

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

    public String getTipo() {

        return "Fornecedor";
    }

    @Override
    public String toString() {
        return  "------------------------------------------------" +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nCNPJ: " + cnpj +
                "\nTelefone: " + telefone +
                "\nEmail: " + email +
                "\nStatus: " + status +
                "\n------------------------------------------------";
    }
}
