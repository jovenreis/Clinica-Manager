public class TesteRemover {
    public static void main(String[] args) {
        System.out.println("--- Testando Remoção de Dados ---");
        ClinicaManager db = new ClinicaManager();
        
        int idParaRemover = 2;
        
        db.removerPaciente(idParaRemover);
        db.listarRegistros();
    }
}