import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day4a {
    public static boolean good(char c1, char c2, char c3, char c4) {
        return (c1 == 'X' && c2 == 'M' && c3 == 'A' && c4 == 'S') || (c1 == 'S' && c2 == 'A' && c3 == 'M' && c4 == 'X');
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day4.txt");
        Scanner in = new Scanner(fs);
        ArrayList<String> input = new ArrayList<>();
        while(in.hasNextLine()) {
            input.add(in.nextLine());
        }
        int result = 0;
        for(String s : input) {
            for (int i = 0; i < s.length() - 3; i++) {
                if (good(s.charAt(i), s.charAt(i + 1), s.charAt(i + 2), s.charAt(i + 3))) {
                    result++;
                }
            }
        }
        for(int i = 0; i < input.size() - 3; i++) {
            for (int j = 0; j < input.get(i).length() - 3; j++) {
                if (good(input.get(i).charAt(j), input.get(i + 1).charAt(j + 1), input.get(i + 2).charAt(j + 2), input.get(i + 3).charAt(j + 3))) {
                    result++;
                }
                if (good(input.get(i + 3).charAt(j), input.get(i + 2).charAt(j + 1), input.get(i + 1).charAt(j + 2), input.get(i).charAt(j + 3))) {
                    result++;
                }
            }
        }
        for(int i = 0; i < input.size() - 3; i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (good(input.get(i).charAt(j), input.get(i + 1).charAt(j), input.get(i + 2).charAt(j), input.get(i + 3).charAt(j))) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
