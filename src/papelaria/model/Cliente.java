package papelaria.model;

import papelaria.interfaces.Identificavel;

public class Cliente extends Pessoa implements Identificavel {

    private String cpf;
    private String email;

    public Cliente(String nome, String cpf, String telefone, String email) {
        super(nome, telefone);
        setCpf(cpf);
        setEmail(email);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = validarTexto(cpf, "CPF");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validarTexto(email, "Email");
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }

    @Override
    public String getId() {
        return cpf;
    }

    @Override
    public String toString() {
        return super.toString() + " | CPF: " + cpf + " | Email: " + email;
    }
}
