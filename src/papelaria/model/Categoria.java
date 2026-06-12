package papelaria.model;

public class Categoria {
    private String nome;
    private String descricao;

    public Categoria(String nome, String descricao) {
        setNome(nome);
        setDescricao(descricao);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da categoria e obrigatorio.");
        }
        this.nome = nome.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descricao da categoria e obrigatoria.");
        }
        this.descricao = descricao.trim();
    }

    public String paraArquivo() {
        return nome + ";" + descricao;
    }

    @Override
    public String toString() {
        return "Categoria: " + nome + " | Descricao: " + descricao;
    }
}
