
# Conversor de Moedas - Challenge Alura 2024

Este programa é uma aplicação em Java que realiza a conversão de moedas, utilizando a API [ExchangeRate API](https://www.exchangerate-api.com/). Ele permite a conversão entre diferentes pares de moedas, como USD (Dólar Americano), EUR (Euro), e BRL (Real Brasileiro), oferecendo taxas de conversão atualizadas.




## Funcionalidades

- Conversão entre pares de moedas: O programa permite realizar a conversão de diferentes moedas, como:
  - Dólar Americano (USD) para Real Brasileiro (BRL)
  - Dólar Americano (USD) para Euro (EUR)
  - Real Brasileiro (BRL) para Dólar Americano (USD)
  - Real Brasileiro (BRL) para Euro (EUR)
  - Euro (EUR) para Real Brasileiro (BRL)
  - Euro (EUR) para Dólar Americano (USD)
  - Qualquer outro par de moeda pelo código _ISO 4217_ 
- Consulta de taxas de conversão em tempo real: As taxas de câmbio são recuperadas em tempo real através da API de câmbio.  
- Informações sobre a última atualização da taxa de câmbio: O programa também exibe a data e hora da última atualização das taxas de conversão.



## Como Usar

1. Clone este repositório:
   ```bash
   git clone https://github.com/natanp/ConversorDeMoedas.git
   ```

2. Compile o código:

   Navegue até o diretório do projeto e execute o seguinte comando Maven:
   ```bash
   mvn clean package
   ```

3. Executar o programa: 
   ```bash
   java -jar target/ConversorDeMoedas-1.0-SNAPSHOT.jar
   ```

4. Navegar pelas opções no console Java
O programa solicitará que você escolha as opções de conversão e insira o valor a ser convertido. O programa irá exibir o valor convertido e a data da última atualização das taxas de câmbio.