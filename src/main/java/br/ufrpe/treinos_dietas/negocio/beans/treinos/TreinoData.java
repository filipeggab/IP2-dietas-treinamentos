package br.ufrpe.treinos_dietas.negocio.beans.treinos;

public class TreinoData {
    private static String focoSelecionado;

    public static void setFocoSelecionado(String foco) {
        focoSelecionado = foco;
    }

    public static String getFocoSelecionado() {
        return focoSelecionado;
    }
}
