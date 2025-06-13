package cadastrobd.model;

public class PessoaFisica extends Pessoa {
    private String cpf;

    public PessoaFisica() {
    }

    public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Pessoa Física [ID: " + getId() +
               ", Nome: " + getNome() +
               ", Logradouro: " + getLogradouro() +
               ", Cidade: " + getCidade() +
               ", Estado: " + getEstado() +
               ", Telefone: " + getTelefone() +
               ", Email: " + getEmail() +
               ", CPF: " + getCpf() + "]";
    }
}
