import java.util.*;

public class Problem9_URLShortener {

    static HashMap<String, String> shortToLong = new HashMap<>();
    static HashMap<String, String> longToShort = new HashMap<>();

    static int counter = 1;

    // Base62 characters
    static String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // Convert number to short code
    public static String encode(int num) {

        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            sb.append(characters.charAt(num % 62));
            num /= 62;
        }

        return sb.toString();
    }

    // Shorten URL
    public static String shortenURL(String longURL) {

        if (longToShort.containsKey(longURL)) {
            return longToShort.get(longURL);
        }

        String shortCode = encode(counter++);

        shortToLong.put(shortCode, longURL);
        longToShort.put(longURL, shortCode);

        return shortCode;
    }

    // Retrieve original URL
    public static String getOriginalURL(String shortCode) {
        return shortToLong.getOrDefault(shortCode, "URL not found");
    }

    public static void main(String[] args) {

        String longURL = "https://www.example.com/my-very-long-url";

        String shortURL = shortenURL(longURL);

        System.out.println("Short URL: " + shortURL);

        String original = getOriginalURL(shortURL);

        System.out.println("Original URL: " + original);
    }
}