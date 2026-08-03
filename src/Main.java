import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MessageRiskAnalyzer messageRiskAnalyzer = new MessageRiskAnalyzer();

        System.out.println("=== VERIFICADOR DE GOLPE ===");
        System.out.println("[ATENÇÃO] A verificação é um guia, não uma garantia absoluta de segurança.");
        System.out.print("Insira a mensagem: ");
        String message = sc.nextLine();

        ScoreCalculator result = messageRiskAnalyzer.processRisk(message);

        System.out.println("Pontuação Total de Risco: " + result.getScore());
        result.scoreResult();

        sc.close();
    }
}