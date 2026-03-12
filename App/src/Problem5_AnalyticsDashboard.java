import java.util.*;

public class Problem5_AnalyticsDashboard {

    // page -> total views
    static HashMap<String,Integer> pageViews = new HashMap<>();

    // page -> unique users
    static HashMap<String,Set<String>> uniqueVisitors = new HashMap<>();

    // traffic source -> count
    static HashMap<String,Integer> trafficSource = new HashMap<>();

    public static void processEvent(String url, String userId, String source){

        pageViews.put(url, pageViews.getOrDefault(url,0)+1);

        uniqueVisitors.putIfAbsent(url,new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        trafficSource.put(source,trafficSource.getOrDefault(source,0)+1);
    }

    public static void showDashboard(){

        System.out.println("Top Pages:");

        for(String page: pageViews.keySet()){

            int views = pageViews.get(page);
            int unique = uniqueVisitors.get(page).size();

            System.out.println(page+" - "+views+" views ("+unique+" unique)");
        }

        System.out.println("\nTraffic Sources:");

        for(String source: trafficSource.keySet()){

            System.out.println(source+" : "+trafficSource.get(source));
        }
    }

    public static void main(String[] args){

        processEvent("/article/breaking-news","user1","google");
        processEvent("/article/breaking-news","user2","facebook");
        processEvent("/sports/championship","user3","google");
        processEvent("/sports/championship","user3","direct");

        showDashboard();
    }
}