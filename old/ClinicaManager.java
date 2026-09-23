import java.sql.*;

public class ClinicaManager {
    
    private static final String URL = "jdbc:postgresql://aws-0-us-west-2.pooler.supabase.com:6543/postgres";
    private static final String USER = "postgres.yfbyuwmgekuluzoazeta";
    private static final String PASSWORD = "Hehe2505.26"; 

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void inserirPacienteEProntuario(String nome, String dataNascimento, String historico) {
        String sqlPaciente = "INSERT INTO Paciente (nome, data_nascimento) VALUES (?, ?)";
        String sqlProntuario = "INSERT INTO Prontuario (paciente_id, historico_medico) VALUES (?, ?)";

        try (Connection conn = connect()) {
            conn.setAutoCommit(false); 

            try (PreparedStatement pstmtPac = conn.prepareStatement(sqlPaciente, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement pstmtPron = conn.prepareStatement(sqlProntuario)) {

                pstmtPac.setString(1, nome);
                pstmtPac.setDate(2, Date.valueOf(dataNascimento)); 
                pstmtPac.executeUpdate();

                ResultSet rs = pstmtPac.getGeneratedKeys();
                if (rs.next()) {
                    int pacienteId = rs.getInt(1);

                    pstmtPron.setInt(1, pacienteId);
                    pstmtPron.setString(2, historico);
                    pstmtPron.executeUpdate();
                }
                
                conn.commit(); 
                System.out.println("✅ Paciente e prontuário inseridos com sucesso!");
            } catch (SQLException e) {
                conn.rollback(); 
                System.out.println("❌ Erro ao inserir dados: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro de conexão: " + e.getMessage());
        }
    }

    public void atualizarHistorico(int pacienteId, String novoHistorico) {
        String sql = "UPDATE Prontuario SET historico_medico = ? WHERE paciente_id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, novoHistorico);
            pstmt.setInt(2, pacienteId);
            
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("✅ Histórico atualizado com sucesso!");
            } else {
                System.out.println("⚠️ Prontuário para o paciente ID " + pacienteId + " não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar: " + e.getMessage());
        }
    }

    public void listarRegistros() {
        String sql = "SELECT p.id, p.nome, p.data_nascimento, pr.historico_medico " +
                     "FROM Paciente p " +
                     "LEFT JOIN Prontuario pr ON p.id = pr.paciente_id " +
                     "ORDER BY p.id ASC";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Lista de Pacientes ---");
            boolean temRegistros = false;
            
            while (rs.next()) {
                temRegistros = true;
                System.out.printf("ID: %d | Nome: %s | Nasc: %s | Histórico: %s%n",
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento"),
                        rs.getString("historico_medico") != null ? rs.getString("historico_medico") : "Sem prontuário");
            }
            
            if (!temRegistros) {
                System.out.println("Nenhum paciente cadastrado ainda.");
            }
            System.out.println("--------------------------\n");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar registros: " + e.getMessage());
        }
    }

    public void removerPaciente(int id) {
        String sql = "DELETE FROM Paciente WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("✅ Paciente ID " + id + " e seu prontuário foram removidos com sucesso!");
            } else {
                System.out.println("⚠️ Paciente ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao remover: " + e.getMessage());
        }
    }

 
    }
