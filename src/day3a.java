import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3a {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day3.txt");
        Scanner in = new Scanner(fs);
        Pattern mulPat = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Pattern doPat = Pattern.compile("do\\(\\)");
        Pattern notPat = Pattern.compile("don't\\(\\)");
        long result = 0;
        boolean enable = true;
        int max = 0;
        while (in.hasNextLine()) {
            String s = in.nextLine();
            max = Math.max(max, s.length());
            Matcher mulMatcher = mulPat.matcher(s);
            Matcher doMatcher = doPat.matcher(s);
            Matcher notMatcher = notPat.matcher(s);
            int nNot = -1;
            int nDo = -1;
            while(mulMatcher.find()) {
                System.out.println(mulMatcher.start());
                String[] curr = mulMatcher.group().split(",");
                int n1 = Integer.parseInt(curr[0].substring(4));
                int n2 = Integer.parseInt(curr[1].substring(0, curr[1].length() - 1));
                result += (long) n1 * n2;
            }
        }
        System.out.println(result);
    }
}
