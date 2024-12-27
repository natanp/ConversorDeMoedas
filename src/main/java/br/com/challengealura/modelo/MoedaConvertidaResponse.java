package br.com.challengealura.modelo;

import com.google.gson.annotations.SerializedName;

public record MoedaConvertidaResponse(@SerializedName("conversion_result") double valorConvertido,
                                      @SerializedName("time_last_update_utc") String ultimaAtualizacao) {
}
