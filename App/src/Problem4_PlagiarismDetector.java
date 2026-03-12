import java.util.*;

public class Problem4_PlagiarismDetector {

    // Generate n-grams
    public static Set<String> generateNGrams(String text, int n) {

        String[] words = text.split(" ");
        Set<String> ngrams = new HashSet<>();

        for (int i = 0; i <= words.length - n; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < n; j++) {
                gram.append(words[i + j]).append(" ");
            }

            ngrams.add(gram.toString().trim());
        }

        return ngrams;
    }

    // Calculate similarity
    public static double calculateSimilarity(String doc1, String doc2) {

        Set<String> grams1 = generateNGrams(doc1, 3);
        Set<String> grams2 = generateNGrams(doc2, 3);

        Set<String> intersection = new HashSet<>(grams1);
        intersection.retainAll(grams2);

        return (intersection.size() * 100.0) / grams1.size();
    }

    public static void main(String[] args) {

        String document1 = "machine learning is very useful in modern applications";
        String document2 = "machine learning is widely used in modern software";

        double similarity = calculateSimilarity(document1, document2);

        System.out.println("Similarity: " + similarity + "%");

        if (similarity > 50) {
            System.out.println("PLAGIARISM DETECTED");
        } else {
            System.out.println("Documents are mostly original");
        }
    }
}