public class TesteInserir {
    public static void main(String[] args) {
        System.out.println("--- Testando Inserção de Dados ---");
        ClinicaManager db = new ClinicaManager();
        
        db.inserirPacienteEProntuario("Luiza Souza", "2002-05-20", "Paciente relata dor de cabeça.");
        db.listarRegistros();
    }
}