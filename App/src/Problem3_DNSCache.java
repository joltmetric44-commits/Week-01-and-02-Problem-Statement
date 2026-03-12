import java.util.*;

class DNSEntry {

    String ipAddress;
    long expiryTime;

    DNSEntry(String ip, long ttlSeconds) {
        this.ipAddress = ip;
        this.expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000);
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class Problem3_DNSCache {

    static HashMap<String, DNSEntry> cache = new HashMap<>();

    static int hits = 0;
    static int misses = 0;

    public static String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null && !entry.isExpired()) {
            hits++;
            return "Cache HIT → " + entry.ipAddress;
        }

        misses++;

        // simulate upstream DNS lookup
        String newIP = "172.217." + new Random().nextInt(255) + "." + new Random().nextInt(255);

        cache.put(domain, new DNSEntry(newIP, 10)); // TTL = 10 seconds

        return "Cache MISS → " + newIP;
    }

    public static void getStats() {

        int total = hits + misses;

        double hitRate = (total == 0) ? 0 : (hits * 100.0) / total;

        System.out.println("Cache Hits: " + hits);
        System.out.println("Cache Misses: " + misses);
        System.out.println("Hit Rate: " + hitRate + "%");
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(resolve("google.com"));
        System.out.println(resolve("google.com"));

        Thread.sleep(11000); // wait for TTL to expire

        System.out.println(resolve("google.com"));

        getStats();
    }
}