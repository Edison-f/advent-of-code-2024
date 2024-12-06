import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day6a {

    static int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day6.txt");
        Scanner in = new Scanner(fs);
        ArrayList<char[]> grid = new ArrayList<>();
        while(in.hasNextLine()) {
            grid.add(in.nextLine().toCharArray());
        }
        int x = -1;
        int y = -1;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).length; j++) {
                if(grid.get(i)[j] == '^') {
                    x = i;
                    y = j;
                }
            }
        }
        int curr = 0;
        int result = 1;
        grid.get(x)[y] = 'X';
        while(x >= 0 && x < grid.size() && y >= 0 && y < grid.get(x).length) { // cond shouldn't trigger
            if(grid.get(x)[y] == '.') {
                result++;
                grid.get(x)[y] = 'X';
            }
            x += dirs[curr][0];
            y += dirs[curr][1];
            if(x < 0 || x >= grid.size() || y < 0 || y >= grid.get(x).length) { // out of bounds
                break;
            }
            if(grid.get(x)[y] == '#') { // rotate
                x -= dirs[curr][0];
                y -= dirs[curr][1];
                curr = (curr + 1) % 4;
            }
        }
        System.out.println(result);
    }
}
