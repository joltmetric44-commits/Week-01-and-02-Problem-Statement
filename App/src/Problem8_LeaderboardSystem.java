import java.util.*;

class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

public class Problem8_LeaderboardSystem {

    static HashMap<String, Integer> scores = new HashMap<>();

    // Add or update score
    public static void addScore(String player, int score) {
        scores.put(player, scores.getOrDefault(player, 0) + score);
    }

    // Show leaderboard
    public static void showLeaderboard() {

        List<Map.Entry<String, Integer>> list = new ArrayList<>(scores.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Leaderboard:");

        int rank = 1;
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(rank + ". " + entry.getKey() + " - " + entry.getValue());
            rank++;
        }
    }

    public static void main(String[] args) {

        addScore("Alice", 50);
        addScore("Bob", 80);
        addScore("Charlie", 60);
        addScore("Alice", 30);

        showLeaderboard();
    }
}