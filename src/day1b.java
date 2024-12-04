import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class day1b {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day1.txt");
        Scanner in = new Scanner(fs);
        long result = 0;
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        while(in.hasNextLine()) {
            String[] split = in.nextLine().split(" {3}");
            int n1 = Integer.parseInt(split[0]);
            map1.putIfAbsent(n1, 0);
            map1.put(n1, map1.get(n1) + 1);
            int n2 = Integer.parseInt(split[1]);
            map2.putIfAbsent(n2, 0);
            map2.put(n2, map2.get(n2) + 1);
        }
        for(int i : map1.keySet()) {
            if(map2.containsKey(i)) {
                result += (long) i * map1.get(i) * map2.get(i);
            }
        }
        System.out.println(result);
    }
}
