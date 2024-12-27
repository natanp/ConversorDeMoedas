package br.com.challengealura.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record MoedaResponse(@SerializedName("conversion_rates") Map<String,Double> taxaConversao,
                            @SerializedName("time_last_update_utc") String ultimaAtualizacao) {
}
