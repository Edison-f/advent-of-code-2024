import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class day8a {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day8.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        HashMap<Character, ArrayList<int[]>> map = new HashMap<>();
        int row = 0;
        int len = 0;
        while(in.hasNextLine()) {
            String next = in.nextLine();
            len = next.length();
            for (int i = 0; i < len; i++) {
                char c = next.charAt(i);
                if(c != '.') {
                    map.putIfAbsent(c, new ArrayList<>());
                    map.get(c).add(new int[] {row, i});
                }
            }
            row++;
        }
        boolean[][] exist = new boolean[row][len];
        for (char c : map.keySet()) {
            ArrayList<int[]> curr = map.get(c);
            for(int i = 0; i < curr.size() - 1; i++) {
                for (int j = i + 1; j < curr.size(); j++) {
                    int dX = curr.get(i)[0] - curr.get(j)[0];
                    int dY = curr.get(i)[1] - curr.get(j)[1];
                    try {
                        exist[dX + curr.get(i)[0]][dY + curr.get(i)[1]] = true;
                    } catch (Exception ignored) {}
                    try {
                        exist[-1 * dX + curr.get(j)[0]][-1 * dY + curr.get(j)[1]] = true;
                    } catch (Exception ignored) {}
                }
            }
        }
        for (boolean[] inner : exist) {
            for (boolean b : inner) {
                if(b) {
                    result++;
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println(result);
    }
}
