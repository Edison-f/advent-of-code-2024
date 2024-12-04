import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/*
MM
SS

SS
MM

MS
MS

SM
SM

 */
public class day4b {
    public static boolean good(String s) {
        return s.equals("MMSS") || s.equals("SSMM") || s.equals("MSMS") || s.equals("SMSM");
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day4.txt");
        Scanner in = new Scanner(fs);
        ArrayList<String> input = new ArrayList<>();
        while(in.hasNextLine()) {
            input.add(in.nextLine());
        }
        int result = 0;
        for (int i = 1; i < input.size() - 1; i++) {
            for (int j = 1; j < input.get(i).length() - 1; j++) {
                if(input.get(i).charAt(j) == 'A' && good("" + input.get(i - 1).charAt(j - 1) + input.get(i - 1).charAt(j + 1) + input.get(i + 1).charAt(j - 1) + input.get(i + 1).charAt(j + 1))) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
