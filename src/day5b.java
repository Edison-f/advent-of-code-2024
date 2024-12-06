import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class day5b {
    public static ArrayList<Integer> create(String[] arr) {
        ArrayList<Integer> res = new ArrayList<>();
        for (String s : arr) {
            res.add(Integer.parseInt(s));
        }
        return res;
    }

    public static boolean subset(HashSet<Integer> seen, HashSet<Integer> req, HashSet<Integer> inside) {
        HashSet<Integer> res = new HashSet<>(req);
        res.retainAll(inside);
        res.addAll(seen);
        return res.size() == seen.size();
    }

    // Not in seen, but required and inside
    public static ArrayList<Integer> after(HashSet<Integer> seen, HashSet<Integer> req, HashSet<Integer> inside) {
        HashSet<Integer> res = new HashSet<>(req);
        res.retainAll(inside);
        res.removeAll(seen);
        return new ArrayList<>(res);
    }

    public static int indexOf(ArrayList<Integer> arr, int n) {
        int i = 0;
        while(n != arr.get(i)) {
            i++;
        }
        return i;
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
            ArrayList<Integer> arr = create(split);
            HashSet<Integer> inside = new HashSet<>(arr);
            boolean good = true;
            for (int i = 0; i < arr.size(); i++) {
                int curr = arr.get(i);
                if (map.containsKey(curr) && !subset(seen, map.get(curr), inside)) {
                    good = false;
                    ArrayList<Integer> needed = after(seen, map.get(curr), inside);
                    int index = 0;
                    for (int k : needed) {
                        index = Math.max(index, indexOf(arr, k));
                    }
                    int temp = arr.get(i);
                    arr.set(i, arr.get(index));
                    arr.set(index, temp);
                    seen.clear();
                    i = -1; // I love inefficiency
                } else {
                    seen.add(curr);
                }
            }
            if(!good) {
                result += arr.get(arr.size() / 2);
            }
        }
        System.out.println(result);
    }
}
