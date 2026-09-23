public class TesteListar {
    public static void main(String[] args) {
        System.out.println("--- Testando Listagem de Dados ---");
        ClinicaManager db = new ClinicaManager();
        
        db.listarRegistros();
    }
}