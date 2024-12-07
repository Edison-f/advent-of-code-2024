import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day7a {
    public static int[] create(String[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Integer.parseInt(arr[i]);
        }
        return res;
    }

    public static boolean solve(long sum, int[] arr, int index, long curr) {
        if(index == arr.length) {
            return curr == sum;
        }
        boolean result = false;
        result |= solve(sum, arr, index + 1, curr + arr[index]);
        if(index != 0) {
            result |= solve(sum, arr, index + 1, curr * (arr[index]));
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day7.txt");
        Scanner in = new Scanner(fs);
        long result = 0;
        while(in.hasNextLine()) {
            String[] split = in.nextLine().split(": ");
            long sum = Long.parseLong(split[0]);
            split = split[1].split(" ");
            int[] arr = create(split);
            if(solve(sum, arr, 0, 0)) {
                result += sum;
            }
        }
        System.out.println(result);
    }
}
