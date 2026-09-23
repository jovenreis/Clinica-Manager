public class Prontuario {
    private int id;
    private int pacienteId;
    private String historicoMedico;

    public Prontuario() {}

    public Prontuario(int pacienteId, String historicoMedico) {
        this.pacienteId = pacienteId;
        this.historicoMedico = historicoMedico;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }
    public String getHistoricoMedico() { return historicoMedico; }
    public void setHistoricoMedico(String historicoMedico) { this.historicoMedico = historicoMedico; }
}