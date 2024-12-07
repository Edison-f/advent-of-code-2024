import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day7b {
    public static int[] create(String[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Integer.parseInt(arr[i]);
        }
        return res;
    }

    public static long numLen(long n) {
        if (n == 0) {
            return 1;
        }
        return (long) (Math.log(n) / Math.log(10)) + 1;
    }

    public static boolean solve(long sum, int[] arr, int index, long curr) {
        if (index == arr.length) {
            return curr == sum;
        }
        boolean result = false;
//        if(concat == 0) {
        result |= solve(sum, arr, index + 1, curr + arr[index]);
        result |= solve(sum, arr, index + 1,  (curr * (long) (Math.pow(10, (numLen(arr[index]))))) + arr[index]);
        if (index != 0) {
            result |= solve(sum, arr, index + 1, curr * (arr[index]));
        }
//        } else { // This is from when I thought that it was curr || (concat[i..n]) ...
//            result |= solve(sum, arr, index + 1, curr, concat + arr[index]);
//            if (index != 0) {
//                result |= solve(sum, arr, index + 1, curr, concat * arr[index]);
//            }
//            result |= solve(sum, arr, index, curr * (10 * numLen(concat)) + concat, 0);
//        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day7.txt");
        Scanner in = new Scanner(fs);
        long result = 0;
        while (in.hasNextLine()) {
            String[] split = in.nextLine().split(": ");
            long sum = Long.parseLong(split[0]);
            split = split[1].split(" ");
            int[] arr = create(split);
            if (solve(sum, arr, 0, 0)) {
                result += sum;
            }
        }
        System.out.println(result);
    }
}
