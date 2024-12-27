package br.com.challengealura;

import br.com.challengealura.api.ExchangeRateApi;
import br.com.challengealura.modelo.MoedaConvertidaResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Principal
{
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        while (true) {
            String textoMenu = """
                   **************************************************************
                   Bem-vindo(a) ao Conversor de Moedas.
                   
                    1- Dólar Americano para Real Brasileiro (USD->BRL).
                    2- Dólar Americano para Euro (USD->EUR).
                    3- Real Brasileiro para Dólar Americano (BRL->USD).
                    4- Real Brasileiro para Euro (BRL->EUR).
                    5- Euro para Real Brasileiro (EUR->BRL).
                    6- Euro para Dólar Americano (EUR->USD).
                    7- Converter qualquer par de Moedas.
                    0- Sair do Programa.
                   **************************************************************
                   """;
            System.out.println(textoMenu);
            System.out.println("Escolha a opção desejada: ");
            String opcao = entrada.nextLine();
            if (opcao.equals("0")) {
                System.out.println("Saindo do programa. Até logo!");
                break;
            }
            try {
                int escolha = Integer.parseInt(opcao);
                realizarConversao(escolha, entrada);
            } catch (NumberFormatException | InterruptedException e) {
                System.out.println("Opção inválida! Por favor, insira um número entre 0 e 7.");
            }

        }
        entrada.close();
    }

    public static void realizarConversao(int opcao, Scanner entrada) throws InterruptedException {
        double valorOrigem;
        switch (opcao) {
            case 1 -> {
                System.out.print("Digite o valor em USD: ");
                valorOrigem = Double.parseDouble(entrada.nextLine());
                System.out.printf("%.2f USD -> Valor em BRL: %.2f%n",valorOrigem,
                        valorOrigem * ExchangeRateApi.consultaDadosMoeda("USD")
                                .taxaConversao().get("BRL"));
            }
            case 2 -> {
                System.out.print("Digite o valor em USD: ");
                valorOrigem = Double.parseDouble(entrada.nextLine());
                System.out.printf("%.2f USD -> Valor em EUR: %.2f%n",valorOrigem,
                        valorOrigem * ExchangeRateApi.consultaDadosMoeda("USD")
                                .taxaConversao().get("EUR"));
            }
            case 3 -> {
                System.out.print("Digite o valor em BRL: ");
                valorOrigem = Double.parseDouble(entrada.nextLine());
                System.out.printf("%.2f BRL -> Valor em USD: %.2f%n",valorOrigem,
                        valorOrigem * ExchangeRateApi.consultaDadosMoeda("BRL")
                                .taxaConversao().get("USD"));
            }
            case 4 -> {
                System.out.print("Digite o valor em BRL: ");
                valorOrigem = Double.parseDouble(entrada.nextLine());
                System.out.printf("%.2f BRL -> Valor em EUR: %.2f%n",valorOrigem,
                        valorOrigem * ExchangeRateApi.consultaDadosMoeda("BRL")
                                .taxaConversao().get("EUR"));
            }
            case 5 -> {
                System.out.print("Digite o valor em EUR: ");
                valorOrigem = Double.parseDouble(entrada.nextLine());
                System.out.printf("%.2f EUR -> Valor em BRL: %.2f%n",valorOrigem,
                        valorOrigem * ExchangeRateApi.consultaDadosMoeda("EUR")
                                .taxaConversao().get("BRL"));
            }
            case 6 -> {
                System.out.print("Digite o valor em EUR: ");
                valorOrigem = Double.parseDouble(entrada.nextLine());
                System.out.printf("%.2f EUR -> Valor em USD: %.2f%n",valorOrigem,
                        valorOrigem * ExchangeRateApi.consultaDadosMoeda("EUR")
                                .taxaConversao().get("USD"));
            }
            case 7 -> {
                System.out.println("Código das moedas suportadas: ");
                Set<String> listaMoedasSuportadas = ExchangeRateApi.listaMoedasSuportadas();
                System.out.println(listaMoedasSuportadas);
                System.out.print("Digite o código da moeda origem: ");
                String moedaOrigem = entrada.nextLine();
                System.out.print("Digite o código da moeda destino: ");
                String moedaDestino = entrada.nextLine();
                System.out.printf("Digite o valor em %s: ",moedaOrigem.toUpperCase());
                valorOrigem = Double.parseDouble(entrada.nextLine());

                if(listaMoedasSuportadas.containsAll(List.of(moedaOrigem.toUpperCase(),moedaDestino.toUpperCase()))){
                    MoedaConvertidaResponse moedaConvertidaResponse =
                            ExchangeRateApi.converteParMoedas(moedaOrigem,moedaDestino,valorOrigem);
                    System.out.printf("%.2f %s -> Valor em %s: %.2f%n",valorOrigem,moedaOrigem.toUpperCase(),
                            moedaDestino.toUpperCase(),moedaConvertidaResponse.valorConvertido());
                }else {
                    System.err.println("Erro: código da moeda inválido ou não suportado");
                }

            }
            default -> System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 7.");
        }
    }
}
