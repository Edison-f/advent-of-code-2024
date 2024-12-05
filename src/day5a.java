import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class day5a {
    public static int[] create(String[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Integer.parseInt(arr[i]);
        }
        return res;
    }

    public static boolean subset(HashSet<Integer> seen, HashSet<Integer> req, HashSet<Integer> inside) {
        HashSet<Integer> res = new HashSet<>(req);
        res.retainAll(inside);
        res.addAll(seen);
        return res.size() == seen.size();
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day5.txt");
        Scanner in = new Scanner(fs);
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        String input;
        while(in.hasNextLine() && !(input = in.nextLine()).isEmpty()) {
            String[] split = input.split("\\|");
            int key = Integer.parseInt(split[1]);
            map.putIfAbsent(key, new HashSet<>());
            map.get(key).add(Integer.parseInt(split[0]));
        }
        int result = 0;
        while(in.hasNextLine()) {
            String[] split = in.nextLine().split(",");
            HashSet<Integer> seen = new HashSet<>();
            HashSet<Integer> needed = new HashSet<>();
            int[] arr = create(split);
            for (int i : arr) {
                needed.add(i);
            }
            boolean good = true;
            for(int i : arr) {
                if(map.containsKey(i) && !subset(seen, map.get(i), needed)) {
                    good = false;
                    break;
                }
                seen.add(i);
            }
            if(good) {
                result += arr[arr.length / 2];
            }
        }
        System.out.println(result);
    }
}
