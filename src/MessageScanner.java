import java.text.Normalizer;
import java.util.regex.Pattern;

public class MessageScanner {

    private static final Pattern ACCENTS =
            Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    public static String normalize(String text) {

        if(text == null)
            return null;

        //Texto em maiúsculo
        text = text.toUpperCase();

        text = Normalizer.normalize(text, Normalizer.Form.NFD);

        return ACCENTS.matcher(text).replaceAll("");
    }
}