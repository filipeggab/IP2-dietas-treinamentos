package br.ufrpe.treinos_dietas.integracao;

import br.ufrpe.treinos_dietas.negocio.beans.dietas.Comida;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class USDAFoodDataAPI {
    private static final String API_KEY = "0gIcOignv5M5qm97NNCZi0Vyh6gEfMMdywz1dhkW";
    private static final String BASE_URL = "https://api.nal.usda.gov/fdc/v1/foods/search";
    private static final OkHttpClient CLIENT = new OkHttpClient();

    public Comida buscarInformacoesNutricionais(String nomeComida, double quantidadeEmGramas) throws IOException {
        HttpUrl url = HttpUrl.parse(BASE_URL);
        if (url == null) {
            throw new IllegalStateException("URL da API inválida.");
        }
        url = url.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("query", nomeComida)
                .addQueryParameter("pageSize", "1")
                .build();

        Request request = new Request.Builder().url(url).build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na requisição: " + response.code());
            }

            String responseBody = response.body().string();
            return parseComida(responseBody, quantidadeEmGramas);
        }
    }

    private Comida parseComida(String jsonResponse, double quantidadeEmGramas) {
        JsonObject root = JsonParser.parseString(jsonResponse).getAsJsonObject();
        if (!root.has("foods") || root.get("foods").isJsonNull()) {
            System.out.println("Resposta da API não contém alimentos.");
            return null;
        }

        JsonArray foods = root.getAsJsonArray("foods");
        if (foods.size() == 0) {
            System.out.println("Nenhum alimento encontrado.");
            return null;
        }

        JsonObject food = foods.get(0).getAsJsonObject();
        JsonArray nutrients = food.has("foodNutrients") ? food.getAsJsonArray("foodNutrients") : new JsonArray();

        String nome = food.has("description") && !food.get("description").isJsonNull()
                ? food.get("description").getAsString()
                : "Desconhecido";

        double proteinas = 0.0, carboidratos = 0.0, gorduras = 0.0, calorias = 0.0;

        for (JsonElement element : nutrients) {
            JsonObject nutrient = element.getAsJsonObject();
            if (!nutrient.has("nutrientName") || !nutrient.has("value") || nutrient.get("value").isJsonNull()) {
                continue;
            }

            String nomeNutriente = nutrient.get("nutrientName").getAsString();
            double value = nutrient.get("value").getAsDouble();

            switch (nomeNutriente.toLowerCase()) {
                case "protein": proteinas = value; break;
                case "carbohydrate, by difference": carboidratos = value; break;
                case "total lipid (fat)": gorduras = value; break;
                case "energy": calorias = value; break;
            }
        }

        // Ajuste para a quantidade informada
        proteinas = (proteinas / 100) * quantidadeEmGramas;
        carboidratos = (carboidratos / 100) * quantidadeEmGramas;
        gorduras = (gorduras / 100) * quantidadeEmGramas;
        calorias = (calorias / 100) * quantidadeEmGramas;

        return new Comida(nome, quantidadeEmGramas + " g", proteinas, carboidratos, gorduras, calorias);
    }

}
