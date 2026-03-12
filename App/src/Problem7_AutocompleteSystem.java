import java.util.*;

class TrieNode {

    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isWord = false;
}

public class Problem7_AutocompleteSystem {

    static TrieNode root = new TrieNode();

    // Insert word into Trie
    public static void insert(String word) {

        TrieNode node = root;

        for (char c : word.toCharArray()) {

            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.isWord = true;
    }

    // DFS to collect suggestions
    public static void dfs(TrieNode node, String prefix, List<String> results) {

        if (node.isWord) {
            results.add(prefix);
        }

        for (char c : node.children.keySet()) {

            dfs(node.children.get(c), prefix + c, results);
        }
    }

    // Get autocomplete suggestions
    public static List<String> autocomplete(String prefix) {

        TrieNode node = root;

        for (char c : prefix.toCharArray()) {

            if (!node.children.containsKey(c)) {
                return new ArrayList<>();
            }

            node = node.children.get(c);
        }

        List<String> results = new ArrayList<>();
        dfs(node, prefix, results);

        return results;
    }

    public static void main(String[] args) {

        insert("apple");
        insert("app");
        insert("application");
        insert("apply");
        insert("banana");

        String query = "app";

        List<String> suggestions = autocomplete(query);

        System.out.println("Suggestions for '" + query + "':");

        for (String word : suggestions) {
            System.out.println(word);
        }
    }
}