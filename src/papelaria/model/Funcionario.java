package papelaria.model;

import papelaria.interfaces.Identificavel;

public class Funcionario extends Pessoa implements Identificavel {

    private String matricula;
    private String cargo;
    private double salario;

    public Funcionario(String nome, String matricula, String telefone, String cargo, double salario) {
        super(nome, telefone);
        setMatricula(matricula);
        setCargo(cargo);
        setSalario(salario);
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = validarTexto(matricula, "Matricula");
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = validarTexto(cargo, "Cargo");
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario < 0) {
            throw new IllegalArgumentException("Salario nao pode ser negativo.");
        }
        this.salario = salario;
    }

    @Override
    public String getTipo() {
        return "Funcionario";
    }

    @Override
    public String getId() {
        return matricula;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Matricula: " + matricula
                + " | Cargo: " + cargo
                + " | Salario: R$ " + String.format("%.2f", salario);
    }
}
