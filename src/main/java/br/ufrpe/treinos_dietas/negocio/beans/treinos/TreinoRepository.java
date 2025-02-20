package br.ufrpe.treinos_dietas.negocio.beans.treinos;

import java.util.*;

public class TreinoRepository {
    private static final Map<String, List<Treino>> TREINOS = new HashMap<>();

    static {
        TREINOS.put("FORÇA_MUSCULAR", Arrays.asList(
                new Treino("Treino A", "Agachamento, Levantamento Terra"),
                new Treino("Treino B", "Supino, Desenvolvimento"),
                new Treino("Treino C", "Remada, Barra Fixa")
        ));

        TREINOS.put("HIPERTROFIA", Arrays.asList(
                new Treino("Treino A", "Cadeira extensora, Supino reto"),
                new Treino("Treino B", "Rosca direta, Tríceps francês"),
                new Treino("Treino C", "Leg press, Crucifixo inclinado")
        ));

        // Adicionar mais focos e treinos aqui...
    }

    public static List<Treino> getTreinosPorFoco(String foco) {
        return TREINOS.getOrDefault(foco, new ArrayList<>());
    }
}
