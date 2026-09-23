import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PacienteDAO pacienteDAO = new PacienteDAO();
        ProntuarioDAO prontuarioDAO = new ProntuarioDAO();

        System.out.println("--- 1. Inserindo Paciente e Prontuário ---");
        Paciente novoPaciente = new Paciente("Maria Clara", LocalDate.of(1998, 5, 20));
        int idGerado = pacienteDAO.inserir(novoPaciente);
        
        if (idGerado != -1) {
            Prontuario novoProntuario = new Prontuario(idGerado, "Paciente em consulta de rotina.");
            prontuarioDAO.inserir(novoProntuario);
            System.out.println("✅ Paciente e prontuário salvos com ID: " + idGerado);
        }

        System.out.println("\n--- 2. Listando Pacientes Cadastrados ---");
        for (Paciente p : pacienteDAO.listarTodos()) {
            System.out.printf("ID: %d | Nome: %s | Nasc: %s%n", p.getId(), p.getNome(), p.getDataNascimento());
        }

        System.out.println("\n--- 3. Atualizando Histórico ---");
        if (idGerado != -1) {
            prontuarioDAO.atualizarHistorico(idGerado, "Retorno agendado para o próximo mês.");
        }

        System.out.println("\n--- 4. Removendo Paciente ---");
        if (idGerado != -1) {
            pacienteDAO.remover(idGerado);
        }
    }
}