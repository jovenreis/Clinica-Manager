import java.sql.*;

public class ProntuarioDAO {

    public void inserir(Prontuario prontuario) {
        String sql = "INSERT INTO Prontuario (paciente_id, historico_medico) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, prontuario.getPacienteId());
            stmt.setString(2, prontuario.getHistoricoMedico());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir prontuário: " + e.getMessage());
        }
    }

    public void atualizarHistorico(int pacienteId, String novoHistorico) {
        String sql = "UPDATE Prontuario SET historico_medico = ? WHERE paciente_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, novoHistorico);
            stmt.setInt(2, pacienteId);
            
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("✅ Histórico atualizado com sucesso!");
            } else {
                System.out.println("⚠️ Prontuário para o paciente ID " + pacienteId + " não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar histórico: " + e.getMessage());
        }
    }
}