package br.com.challengealura.api;

import br.com.challengealura.modelo.MoedaResponse;
import br.com.challengealura.modelo.MoedaConvertidaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExchangeRateApi {

    private ExchangeRateApi(){}

    public static MoedaResponse consultaDadosMoeda(String codigoMoeda) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.
                newBuilder(URI.create("https://v6.exchangerate-api.com/v6/8098d7dabea77583c2ddc39f/latest/"+ codigoMoeda))
                .build();
        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            MoedaResponse moedaResponse = gson.fromJson(response.body(), MoedaResponse.class);
            imprimeDataProcessada(moedaResponse.ultimaAtualizacao());
            return moedaResponse;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao fazer a requisição: " + e.getMessage(), e);
        }

    }

    public static MoedaConvertidaResponse converteParMoedas(String moedaOrigem, String moedaDestino, double quantidade) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://v6.exchangerate-api.com/v6/8098d7dabea77583c2ddc39f/pair/"
                        + moedaOrigem + "/" + moedaDestino + "/" + quantidade))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao fazer a requisição: " + e.getMessage(), e);
        }
        if (response.statusCode() != 200 || response.body().contains("unsupported-code")) {
            throw new RuntimeException("Erro na conversão de moedas: Código de resposta não esperado ou código de moeda não suportado.");
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        MoedaConvertidaResponse moedaConvertida = gson.fromJson(response.body(), MoedaConvertidaResponse.class);
        imprimeDataProcessada(moedaConvertida.ultimaAtualizacao());
        return moedaConvertida;
    }

    public static Set<String> listaMoedasSuportadas() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.
                newBuilder(URI.create("https://v6.exchangerate-api.com/v6/8098d7dabea77583c2ddc39f/latest/BRL"))
                .build();
        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            MoedaResponse moedaResponse = gson.fromJson(response.body(), MoedaResponse.class);
            return moedaResponse.taxaConversao().keySet();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao fazer a requisição: " + e.getMessage(), e);
        }

    }

    private static void imprimeDataProcessada(String rfc1123Date){
        DateTimeFormatter rfc1123Formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        ZonedDateTime ultimaAtualizacao = ZonedDateTime.parse(rfc1123Date, rfc1123Formatter);
        Locale localeAtual = Locale.getDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy HH:mm:ss 'UTC'Z", localeAtual);
        String dataFormatada = ultimaAtualizacao.format(formatter);
        System.out.println("\nData da última cotação: " + dataFormatada);
    }
}
