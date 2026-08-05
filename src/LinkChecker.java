import java.net.IDN;
import java.net.URI;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class LinkChecker {
        private static final Pattern SUSPICIOUS_LINK_PATTERN = Pattern.compile(
                "(https?://\\S+|www\\.\\S+|\\bbit\\.ly/\\S+|\\S+\\.(?:xyz|top|click|info|link|ru)\\b)",
                Pattern.CASE_INSENSITIVE
        );

        private static final Pattern IP_PATTERN = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");
        public boolean hasSuspiciousLink(String message) {
            if (message == null || message.isBlank()) {
                return false;
            }

            Matcher matcher = SUSPICIOUS_LINK_PATTERN.matcher(message);
            return matcher.find();
        }

        public static boolean hasRink(String urlString) {
            try {
                URI uri = new URL(urlString).toURI();
                String scheme = uri.getScheme();
                String host = uri.getHost();
                String authority = uri.getAuthority();

                if (host == null) return true;

                if (!"https".equalsIgnoreCase(scheme)) return true;
                if (IP_PATTERN.matcher(host).matches()) return true;
                if (IDN.toASCII(host).startsWith("xn--")) return true;
                if (authority != null && authority.contains("@")) return true;
                if (host.chars().filter(ch -> ch == '.').count() > 4) return true;

                return false;

            } catch (Exception e) {
                return true;
            }
        }
    }

