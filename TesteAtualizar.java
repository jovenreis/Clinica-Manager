public class TesteAtualizar {
    public static void main(String[] args) {
        System.out.println("--- Testando Atualização de Dados ---");
        ClinicaManager db = new ClinicaManager();
        
        int idParaAtualizar = 2; 
        
        db.atualizarHistorico(idParaAtualizar, "Exames realizados. Receitou dipirona.");
        db.listarRegistros();
    }
}