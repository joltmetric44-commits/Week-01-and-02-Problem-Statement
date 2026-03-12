import java.util.*;

class TokenBucket {

    int tokens;
    int maxTokens;
    long lastRefillTime;

    TokenBucket(int maxTokens) {
        this.tokens = maxTokens;
        this.maxTokens = maxTokens;
        this.lastRefillTime = System.currentTimeMillis();
    }

    void refill() {

        long now = System.currentTimeMillis();
        long elapsedSeconds = (now - lastRefillTime) / 1000;

        if (elapsedSeconds > 0) {
            tokens = maxTokens;
            lastRefillTime = now;
        }
    }

    boolean allowRequest() {

        refill();

        if (tokens > 0) {
            tokens--;
            return true;
        }

        return false;
    }
}

public class Problem6_RateLimiter {

    static HashMap<String, TokenBucket> clients = new HashMap<>();

    public static boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(5));

        TokenBucket bucket = clients.get(clientId);

        return bucket.allowRequest();
    }

    public static void main(String[] args) {

        String client = "client123";

        for (int i = 1; i <= 7; i++) {

            boolean allowed = checkRateLimit(client);

            if (allowed) {
                System.out.println("Request " + i + " → Allowed");
            } else {
                System.out.println("Request " + i + " → Rate Limit Exceeded");
            }
        }
    }
}