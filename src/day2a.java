import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day2a {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day2.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        while(in.hasNextLine()) {
            String[] split = in.nextLine().split(" ");
            boolean safe = true;
            if(Integer.parseInt(split[0]) < Integer.parseInt(split[1])) {
                for (int i = 0; i < split.length - 1; i++) {
                    int diff = Integer.parseInt(split[i + 1]) - Integer.parseInt(split[i]);
                    if (diff > 3 || diff < 1) {
                        safe = false;
                        break;
                    }
                }
            } else if(Integer.parseInt(split[0]) > Integer.parseInt(split[1])) {
                for (int i = 0; i < split.length - 1; i++) {
                    int diff = Integer.parseInt(split[i]) - Integer.parseInt(split[i + 1]);
                    if (diff > 3 || diff < 1) {
                        safe = false;
                        break;
                    }
                }
            } else {
                safe = false;
            }
            if(safe) {
                result++;
            }
        }
        System.out.println(result);
    }
}
