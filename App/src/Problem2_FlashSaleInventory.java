import java.util.*;

public class Problem2_FlashSaleInventory {

    // product -> stock
    static HashMap<String,Integer> inventory = new HashMap<>();

    // waiting list
    static Queue<Integer> waitingList = new LinkedList<>();

    public static int checkStock(String productId){
        return inventory.getOrDefault(productId,0);
    }

    public static synchronized void purchaseItem(String productId,int userId){

        int stock = inventory.getOrDefault(productId,0);

        if(stock > 0){
            inventory.put(productId, stock-1);
            System.out.println("User "+userId+" purchased item. Remaining stock: "+(stock-1));
        }
        else{
            waitingList.add(userId);
            System.out.println("User "+userId+" added to waiting list. Position: "+waitingList.size());
        }
    }

    public static void main(String[] args){

        inventory.put("IPHONE15_256GB",3);

        System.out.println("Stock: "+checkStock("IPHONE15_256GB"));

        purchaseItem("IPHONE15_256GB",101);
        purchaseItem("IPHONE15_256GB",102);
        purchaseItem("IPHONE15_256GB",103);
        purchaseItem("IPHONE15_256GB",104);
    }
}