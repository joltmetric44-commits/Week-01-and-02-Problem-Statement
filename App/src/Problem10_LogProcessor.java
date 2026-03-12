import java.util.*;

public class Problem10_LogProcessor {

    static HashMap<String, Integer> statusCount = new HashMap<>();
    static HashMap<String, Integer> endpointCount = new HashMap<>();

    public static void processLog(String log) {

        // Example log format
        // "IP METHOD ENDPOINT STATUS"

        String[] parts = log.split(" ");

        String endpoint = parts[2];
        String status = parts[3];

        endpointCount.put(endpoint, endpointCount.getOrDefault(endpoint, 0) + 1);
        statusCount.put(status, statusCount.getOrDefault(status, 0) + 1);
    }

    public static void printStats() {

        System.out.println("Status Code Counts:");

        for (String status : statusCount.keySet()) {
            System.out.println(status + " : " + statusCount.get(status));
        }

        System.out.println("\nMost Requested Endpoints:");

        for (String endpoint : endpointCount.keySet()) {
            System.out.println(endpoint + " : " + endpointCount.get(endpoint));
        }
    }

    public static void main(String[] args) {

        processLog("192.168.1.1 GET /home 200");
        processLog("192.168.1.2 GET /login 200");
        processLog("192.168.1.3 POST /login 401");
        processLog("192.168.1.4 GET /home 200");
        processLog("192.168.1.5 GET /dashboard 500");

        printStats();
    }
}