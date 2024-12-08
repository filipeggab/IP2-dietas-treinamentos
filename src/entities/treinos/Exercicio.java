package entities.treinos;

public class Exercicio {
    private String nome;
    private String descricao;
    private String materiais;
    private String dificuldade;
    private String objetivo;
    private double percaCaloricaMedia;

    public Exercicio(String nome, String descricao, String materiais, String dificuldade, double percaCaloricaMedia) {
        this.nome = nome;
        this.descricao = descricao;
        this.materiais = materiais;
        this.dificuldade = dificuldade;
        this.percaCaloricaMedia = percaCaloricaMedia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMateriais() {
        return materiais;
    }

    public void setMateriais(String materiais) {
        this.materiais = materiais;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public double getPercaCaloricaMedia(){
        return percaCaloricaMedia;
    }

    public void setPercaCaloricaMedia(double percaCaloricaMedia){
        this.percaCaloricaMedia = percaCaloricaMedia;
    }
}
