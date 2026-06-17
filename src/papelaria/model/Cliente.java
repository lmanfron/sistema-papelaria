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
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF e obrigatorio.");
        }

        String cpfLimpo = cpf.replaceAll("\\D", "");

        if (cpfLimpo.length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 numeros.");
        }

        this.cpf = cpfLimpo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = validarTexto(email, "Email");

            if (!email.contains("@") || !email.contains(".")) {
                throw new IllegalArgumentException("Email invalido.");
        }

        this.email = email;
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
