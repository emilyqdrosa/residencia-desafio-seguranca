public class ScoreCalculator {

    private static final int REWARD_SCORE = 5;
    private static final int URGENCY_SCORE = 10;
    private static final int SUSPICIOUS_LINK_SCORE = 10;
    private static final int FINANCIAL_SCORE = 15;
    private static final int SENSITIVE_DATA_REQUEST_SCORE = 15;
    private static final int INSTALL_REQUEST_SCORE = 15;

    private static int score;

    public static void scoreResult(){
        if (score <= 30){
            System.out.println("[OK] Provavelmente legítima.");
        } else if (score < 70){
            System.out.println("[ATENÇÃO] Mensagem suspeita.");
        } else {
            System.out.println("[PERIGO] Provavelmente golpe.");
        }
    }

    public ScoreCalculator() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }
}
