package cadastrobd.model.dao;

import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {

    public void incluir(PessoaJuridica pj) {
        String sqlPessoa = """
            INSERT INTO Pessoa (id_pessoa, nome, logradouro, cidade, estado, telefone, email)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        String sqlJuridica = """
            INSERT INTO PessoaJuridica (id_pessoa, cnpj)
            VALUES (?, ?)
        """;

        try (Connection c = ConectorBD.getConnection();
             PreparedStatement psPessoa = c.prepareStatement(sqlPessoa);
             PreparedStatement psJuridica = c.prepareStatement(sqlJuridica)) {

            int id = SequenceManager.getValue("seq_pessoa");
            pj.setId(id);

            psPessoa.setInt(1, id);
            psPessoa.setString(2, pj.getNome());
            psPessoa.setString(3, pj.getLogradouro());
            psPessoa.setString(4, pj.getCidade());
            psPessoa.setString(5, pj.getEstado());
            psPessoa.setString(6, pj.getTelefone());
            psPessoa.setString(7, pj.getEmail());
            psPessoa.executeUpdate();

            psJuridica.setInt(1, id);
            psJuridica.setString(2, pj.getCnpj());
            psJuridica.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaJuridica pj) {
        String sqlPessoa = """
            UPDATE Pessoa
            SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ?
            WHERE id_pessoa = ?
        """;
        String sqlJuridica = """
            UPDATE PessoaJuridica
            SET cnpj = ?
            WHERE id_pessoa = ?
        """;

        try (Connection c = ConectorBD.getConnection();
             PreparedStatement psPessoa = c.prepareStatement(sqlPessoa);
             PreparedStatement psJuridica = c.prepareStatement(sqlJuridica)) {

            psPessoa.setString(1, pj.getNome());
            psPessoa.setString(2, pj.getLogradouro());
            psPessoa.setString(3, pj.getCidade());
            psPessoa.setString(4, pj.getEstado());
            psPessoa.setString(5, pj.getTelefone());
            psPessoa.setString(6, pj.getEmail());
            psPessoa.setInt(7, pj.getId());
            psPessoa.executeUpdate();

            psJuridica.setString(1, pj.getCnpj());
            psJuridica.setInt(2, pj.getId());
            psJuridica.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlJuridica = "DELETE FROM PessoaJuridica WHERE id_pessoa = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id_pessoa = ?";

        try (Connection c = ConectorBD.getConnection();
             PreparedStatement psJuridica = c.prepareStatement(sqlJuridica);
             PreparedStatement psPessoa = c.prepareStatement(sqlPessoa)) {

            psJuridica.setInt(1, id);
            psJuridica.executeUpdate();

            psPessoa.setInt(1, id);
            psPessoa.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PessoaJuridica getPessoa(int id) {
        String sql = """
            SELECT p.id_pessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email,
                   pj.cnpj
            FROM Pessoa p
            JOIN PessoaJuridica pj ON p.id_pessoa = pj.id_pessoa
            WHERE p.id_pessoa = ?
        """;

        try (Connection c = ConectorBD.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PessoaJuridica(
                        rs.getInt("id_pessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> lista = new ArrayList<>();
        String sql = """
            SELECT p.id_pessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email,
                   pj.cnpj
            FROM Pessoa p
            JOIN PessoaJuridica pj ON p.id_pessoa = pj.id_pessoa
        """;

        try (Connection c = ConectorBD.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PessoaJuridica pj = new PessoaJuridica(
                    rs.getInt("id_pessoa"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
                lista.add(pj);
            }

        } catch (SQLException e) {
            
            e.printStackTrace();
        }

        return lista;
    }
}
