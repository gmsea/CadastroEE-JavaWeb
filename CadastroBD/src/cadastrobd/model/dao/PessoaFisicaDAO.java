package cadastrobd.model.dao;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    public void incluir(PessoaFisica pf) {
        String sqlP = """
            INSERT INTO Pessoa
              (id_pessoa, nome, logradouro, cidade, estado, telefone, email)
            VALUES
              (?, ?, ?, ?, ?, ?, ?)
            """;
        String sqlF = """
            INSERT INTO PessoaFisica
              (id_pessoa, cpf)
            VALUES
              (?, ?)
            """;

        try (Connection c = ConectorBD.getConnection();
             PreparedStatement p1 = c.prepareStatement(sqlP);
             PreparedStatement p2 = c.prepareStatement(sqlF)) {

            int id = SequenceManager.getValue("seq_pessoa");
            pf.setId(id);

            p1.setInt(1, id);
            p1.setString(2, pf.getNome());
            p1.setString(3, pf.getLogradouro());
            p1.setString(4, pf.getCidade());
            p1.setString(5, pf.getEstado());
            p1.setString(6, pf.getTelefone());
            p1.setString(7, pf.getEmail());
            p1.executeUpdate();

            p2.setInt(1, id);
            p2.setString(2, pf.getCpf());
            p2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaFisica pf) {
        String sqlP = """
            UPDATE Pessoa
            SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ?
            WHERE id_pessoa = ?
            """;
        String sqlF = """
            UPDATE PessoaFisica
            SET cpf = ?
            WHERE id_pessoa = ?
            """;

        try (Connection c = ConectorBD.getConnection();
             PreparedStatement p1 = c.prepareStatement(sqlP);
             PreparedStatement p2 = c.prepareStatement(sqlF)) {

            p1.setString(1, pf.getNome());
            p1.setString(2, pf.getLogradouro());
            p1.setString(3, pf.getCidade());
            p1.setString(4, pf.getEstado());
            p1.setString(5, pf.getTelefone());
            p1.setString(6, pf.getEmail());
            p1.setInt(7, pf.getId());
            p1.executeUpdate();

            p2.setString(1, pf.getCpf());
            p2.setInt(2, pf.getId());
            p2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlF = "DELETE FROM PessoaFisica WHERE id_pessoa = ?";
        String sqlP = "DELETE FROM Pessoa WHERE id_pessoa = ?";

        try (Connection c = ConectorBD.getConnection();
             PreparedStatement p2 = c.prepareStatement(sqlF);
             PreparedStatement p1 = c.prepareStatement(sqlP)) {

            p2.setInt(1, id);
            p2.executeUpdate();

            p1.setInt(1, id);
            p1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PessoaFisica getPessoa(int id) {
        String sql = """
            SELECT p.id_pessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email,
                   pf.cpf
            FROM Pessoa p
            JOIN PessoaFisica pf ON p.id_pessoa = pf.id_pessoa
            WHERE p.id_pessoa = ?
            """;

        try (Connection c = ConectorBD.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PessoaFisica(
                        rs.getInt("id_pessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> lista = new ArrayList<>();
        String sql = """
            SELECT p.id_pessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email,
                   pf.cpf
            FROM Pessoa p
            JOIN PessoaFisica pf ON p.id_pessoa = pf.id_pessoa
            """;

        try (Connection c = ConectorBD.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new PessoaFisica(
                    rs.getInt("id_pessoa"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
