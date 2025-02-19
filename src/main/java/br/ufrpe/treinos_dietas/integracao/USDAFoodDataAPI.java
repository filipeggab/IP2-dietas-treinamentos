package br.ufrpe.treinos_dietas.integracao;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.HttpUrl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Comida;
import java.io.IOException;

public class USDAFoodDataAPI {
    private static final String API_KEY = "0gIcOignv5M5qm97NNCZi0Vyh6gEfMMdywz1dhkW"; //provavelmente n é uma boa ideia só deixar a minha chave da API solta aqui, mas por enquanto fica assim
    private static final String BASE_URL = "https://api.nal.usda.gov/fdc/v1/foods/search";

    private OkHttpClient client;

    public USDAFoodDataAPI() {
        this.client = new OkHttpClient();
    }

    public Comida buscarInformacoesNutricionais(String nomeComida) throws IOException {
        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("query", nomeComida)
                .addQueryParameter("pageSize", "1")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na requisição: " + response.code());
            }

            String responseBody = response.body().string();
            return parseComida(responseBody);
        }
    }

    private Comida parseComida(String jsonResponse) {
        JsonObject root = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonArray foods = root.getAsJsonArray("foods");

        if (foods.size() == 0) {
            throw new RuntimeException("Nenhum alimento encontrado");
        }

        JsonObject food = foods.get(0).getAsJsonObject();
        JsonArray nutrients = food.getAsJsonArray("foodNutrients");

        String nome = food.has("description") && !food.get("description").isJsonNull()
                ? food.get("description").getAsString()
                : "Desconhecido";

        double quantidade = food.has("servingSize") && !food.get("servingSize").isJsonNull()
                ? food.get("servingSize").getAsDouble()
                : 0.0;

        String unidade = food.has("servingSizeUnit") && !food.get("servingSizeUnit").isJsonNull()
                ? food.get("servingSizeUnit").getAsString()
                : "";

        double proteinas = 0.0, carboidratos = 0.0, gorduras = 0.0, calorias = 0.0;

        for (int i = 0; i < nutrients.size(); i++) {
            JsonObject nutrient = nutrients.get(i).getAsJsonObject();

            if (nutrient.has("nutrientId") && nutrient.has("value") && !nutrient.get("value").isJsonNull()) {
                int nutrientId = nutrient.get("nutrientId").getAsInt();
                double value = nutrient.get("value").getAsDouble();

                switch (nutrientId) {
                    case 1003: proteinas = value; break;
                    case 1005: carboidratos = value; break;
                    case 1004: gorduras = value; break;
                    case 1008: calorias = value; break;
                }
            }
        }

        return new Comida(
                nome,
                quantidade + " " + unidade,
                proteinas,
                carboidratos,
                gorduras,
                calorias
        );
    }

}