package cadastrobd.model;

public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica() {
    }

    public PessoaJuridica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Pessoa Jurídica [ID: " + getId() +
               ", Nome: " + getNome() +
               ", Logradouro: " + getLogradouro() +
               ", Cidade: " + getCidade() +
               ", Estado: " + getEstado() +
               ", Telefone: " + getTelefone() +
               ", Email: " + getEmail() +
               ", CNPJ: " + getCnpj() + "]";
    }
}
