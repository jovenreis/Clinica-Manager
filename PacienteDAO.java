import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public int inserir(Paciente paciente) {
        String sql = "INSERT INTO Paciente (nome, data_nascimento) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, paciente.getNome());
            stmt.setDate(2, Date.valueOf(paciente.getDataNascimento()));
            stmt.executeUpdate();

            // Pega o ID que foi gerado automaticamente pelo banco de dados
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(" Erro ao inserir paciente: " + e.getMessage());
        }
        return -1;
    }

    public List<Paciente> listarTodos() {
        String sql = "SELECT id, nome, data_nascimento FROM Paciente ORDER BY id ASC";
        List<Paciente> lista = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Paciente p = new Paciente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDate("data_nascimento").toLocalDate()
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println(" Erro ao listar pacientes: " + e.getMessage());
        }
        return lista;
    }

    public void remover(int id) {
        String sql = "DELETE FROM Paciente WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Paciente ID " + id + " removido com sucesso!");
            } else {
                System.out.println("Nenhum paciente encontrado com o ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover paciente: " + e.getMessage());
        }
    }
}