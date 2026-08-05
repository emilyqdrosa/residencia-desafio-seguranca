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
    private LinkChecker linkChecker;

    public MessageRiskAnalyzer() {
        keywordMap = new HashMap<>();
        linkChecker = new LinkChecker();
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

        String normalizedMessage = MessageScanner.normalize(message);
        String[] words = normalizedMessage.split("\\W+");

        for (String w : words) {
            if (w.isEmpty()) continue;

            if (keywordMap.containsKey(w)) {
                String category = keywordMap.get(w);

                scoreCalculator.addScore(category);
            }
        }

        if (linkChecker.hasSuspiciousLink(message) && linkChecker.contemRisco(message)) {
            scoreCalculator.addScore("SUSPICIOUS_LINK");
        }
        return scoreCalculator;
    }
}

