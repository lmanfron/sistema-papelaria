package papelaria.model;

public abstract class Pessoa {
    protected String nome;
    protected String telefone;

    public Pessoa(String nome, String telefone) {
        setNome(nome);
        setTelefone(telefone);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = validarTexto(nome, "Nome");
    }

    public String getTelefone() {
        return telefone;
    }

        public void setTelefone(String telefone) {
            if (telefone == null || telefone.trim().isEmpty()) {
                throw new IllegalArgumentException("Telefone e obrigatorio.");
        }

        String telefoneLimpo = telefone.replaceAll("\\D", "");

        if (telefoneLimpo.length() < 10 || telefoneLimpo.length() > 11) {
            throw new IllegalArgumentException("Telefone deve ter 10 ou 11 numeros.");
        }

        this.telefone = telefoneLimpo;
    }

    public abstract String getTipo();

    protected String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " e obrigatorio.");
        }
        return valor.trim();
    }

    @Override
    public String toString() {
        return getTipo() + " | Nome: " + nome + " | Telefone: " + telefone;
    }
}
