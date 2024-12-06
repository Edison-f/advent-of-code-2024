import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day6b {

    static int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static ArrayList<char[]> copy(ArrayList<char[]> arr) {
        ArrayList<char[]> copy = new ArrayList<>();
        for (char[] inner : arr) {
            copy.add(new char[inner.length]);
            System.arraycopy(inner, 0, copy.get(copy.size() - 1), 0, inner.length);
        }
        return copy;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day6.txt");
        Scanner in = new Scanner(fs);
        ArrayList<char[]> mgrid = new ArrayList<>();
        while(in.hasNextLine()) {
            mgrid.add(in.nextLine().toCharArray());
        }
        int startX = -1;
        int startY = -1;
        for (int i = 0; i < mgrid.size(); i++) {
            for (int j = 0; j < mgrid.get(i).length; j++) {
                if(mgrid.get(i)[j] == '^') {
                    startX = i;
                    startY = j;
                    break;
                }
            }
        }
        int curr;
        int result = 0;
        for (int i = 0; i < mgrid.size(); i++) {
            for (int j = 0; j < mgrid.get(i).length; j++) { // brute force place obstacles
                ArrayList<char[]> grid = copy(mgrid); // can't init using constructor, mgrid gets changed as well
                if(grid.get(i)[j] == '#') { // don't check already blocked
                    continue;
                }
                curr = 0;
                int x = startX;
                int y = startY;
                grid.get(i)[j] = 'O'; // could also keep as # (except for debugging)
                grid.get(startX)[startY] = '.'; // if caret, messes up bit checks
                while(x >= 0 && x < grid.size() && y >= 0 && y < grid.get(x).length) {
                    if(grid.get(x)[y] == '.') { // store position
                        grid.get(x)[y] = (char) (1 << curr);
                    } else {
//                        System.out.println(grid.get(x)[y] + " " + (int) grid.get(x)[y]);
                        if((grid.get(x)[y] | (1 << curr)) == grid.get(x)[y]) { // position already visited with given rotation
                            result++;
                            break;
                        }
                        grid.get(x)[y] |= (char) (1 << curr); // store rotation
                    }
                    x += dirs[curr][0];
                    y += dirs[curr][1];
                    if(x < 0 || x >= grid.size() || y < 0 || y >= grid.get(x).length) { // out of bounds
                        break;
                    }
                    if(grid.get(x)[y] == '#' || grid.get(x)[y] == 'O') {
                        x -= dirs[curr][0];
                        y -= dirs[curr][1];
                        curr = (curr + 1) % 4;
                    }
                }
            }
        }
        
        System.out.println(result);
    }
}
