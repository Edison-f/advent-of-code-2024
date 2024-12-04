import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3b {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day3.txt");
        Scanner in = new Scanner(fs);
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Pattern doPat = Pattern.compile("do\\(\\)");
        Pattern notPat = Pattern.compile("don't\\(\\)");
        long result = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean enable = true;
        while (in.hasNextLine()) {
            String s = in.nextLine();
            Matcher mulMatch = pattern.matcher(s);
            Matcher doMat = doPat.matcher(s);
            Matcher notMat = notPat.matcher(s);
            boolean mLeft = true;
            boolean dLeft = true;
            boolean nLeft = true;
            mLeft = mulMatch.find();
            dLeft = doMat.find();
            nLeft = notMat.find();
            for (int i = 0; i < s.length(); i++) {
                if(mLeft && mulMatch.start() == i) {
                    if(enable) {
                        String[] curr = mulMatch.group().split(",");
                        int n1 = Integer.parseInt(curr[0].substring(4));
                        int n2 = Integer.parseInt(curr[1].substring(0, curr[1].length() - 1));
                        result += (long) n1 * n2;
                    }
                    mLeft = mulMatch.find();
                } else if(dLeft && doMat.start() == i) {
                    enable = true;
                    dLeft = doMat.find();
                } else if (nLeft && notMat.start() == i) {
                    enable = false;
                    nLeft = notMat.find();
                }
            }
        }
        System.out.println(result);
    }
}
