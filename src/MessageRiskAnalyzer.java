import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageRiskAnalyzer {

    private static final String CSV_PATH = "src/suspiciousKeywords.csv";
    private Map<String, String> keywordMap;

    public MessageRiskAnalyzer() {
        keywordMap = new HashMap<>();
        loadCsv();
    }

    private void loadCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] col = line.split(",");

                keywordMap.put(col[0].trim().toUpperCase(), col[1].trim().toUpperCase());
            }
        } catch (IOException e) {
            System.err.println("[ERRO] Não foi possível ler o CSV: " + e.getMessage());
        }
    }

    public ScoreCalculator processRisk(String message) {
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        MessageScanner messageScanner = new MessageScanner();
        message = messageScanner.normalize(message);

        String[] words = message.split("\\W+");

        for (String w : words){
            if (w.isEmpty()) continue;

            if (keywordMap.containsKey(w)){
                String category = keywordMap.get(w);

                scoreCalculator.addScore(category);
            }
        }
        return scoreCalculator;
    }
}
