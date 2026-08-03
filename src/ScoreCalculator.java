public class ScoreCalculator {

    private static final int REWARD_SCORE = 5;
    private static final int URGENCY_SCORE = 10;
    private static final int SUSPICIOUS_LINK_SCORE = 10;
    private static final int FINANCIAL_SCORE = 15;
    private static final int SENSITIVE_DATA_REQUEST_SCORE = 15;
    private static final int INSTALL_REQUEST_SCORE = 15;

    private int score;

    public ScoreCalculator() {
        this.score = 0;
    }

    public void addScore(String category) {
        switch (category.toUpperCase()) {
            case "REWARD" -> score += REWARD_SCORE;
            case "URGENCY" -> score += URGENCY_SCORE;
            case "SUSPICIOUS_LINK" -> score += SUSPICIOUS_LINK_SCORE;
            case "FINANCIAL" -> score += FINANCIAL_SCORE;
            case "SENSITIVE_DATA_REQUEST" -> score += SENSITIVE_DATA_REQUEST_SCORE;
            case "INSTALL_REQUEST" -> score += INSTALL_REQUEST_SCORE;
            default -> {}
        }
    }

    public String scoreResult(){
        if (score <= 30){
            return "[OK] Provavelmente legítima.";
        } else if (score < 70){
            return "[ATENÇÃO] Mensagem suspeita.";
        } else {
            return "[PERIGO] Provavelmente golpe.";
        }
    }

    public int getScore() {
        return score;
    }
}
