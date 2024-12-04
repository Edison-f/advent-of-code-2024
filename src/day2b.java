import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day2b {
    public static int[] create(String[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Integer.parseInt(arr[i]);
        }
        return res;
    }

    public static boolean possible(int[] arr, int skip) {
        if (skip == -1) {
            if (arr[0] < arr[1]) {
                for (int i = 0; i < arr.length - 1; i++) {
                    int diff = arr[i + 1] - arr[i];
                    if (diff > 3 || diff < 1) {
                        return false;
                    }
                }
            } else if (arr[0] > arr[1]) {
                for (int i = 0; i < arr.length - 1; i++) {
                    int diff = arr[i] - arr[i + 1];
                    if (diff > 3 || diff < 1) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            boolean inc = false;
            if (skip == 0) {
                inc = arr[2] > arr[1];
            } else if (skip == 1) {
                inc = arr[2] > arr[0];
            } else {
                inc = arr[1] > arr[0];
            }
            for (int i = 0; i < arr.length - 1; i++) {
                if (i == skip - 1) {
                    try {
                        int diff = arr[i] - arr[i + 2];
                        if ((!inc && (diff > 3 || diff < 1)) || (inc && (diff < -3 || diff > -1))) {
                            return false;
                        }
                    } catch (Exception ignore) {
                    }
                } else if (i != skip) {
                    int diff = arr[i] - arr[i + 1];
                    if ((!inc && (diff > 3 || diff < 1)) || (inc && (diff < -3 || diff > -1))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day2.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        while (in.hasNextLine()) {
            String[] split = in.nextLine().split(" ");
            boolean safe = false;
            int[] arr = create(split);
            for (int i = -1; i < split.length; i++) {
                if(possible(arr, i)) {
                    result++;
                    break;
                }
            }
            continue;
//            if (Integer.parseInt(split[0]) < Integer.parseInt(split[1])) {
//                for (int i = 0; i < split.length - 1; i++) {
//                    int diff = Integer.parseInt(split[i + 1]) - Integer.parseInt(split[i]);
//                    if (diff > 3 || diff < 1) {
//                        safe = false;
//                        break;
//                    }
//                }
//            } else if (Integer.parseInt(split[0]) > Integer.parseInt(split[1])) {
//                for (int i = 0; i < split.length - 1; i++) {
//                    int diff = Integer.parseInt(split[i]) - Integer.parseInt(split[i + 1]);
//                    if (diff > 3 || diff < 1) {
//                        safe = false;
//                        break;
//                    }
//                }
//            } else {
//                safe = false;
//            }
//            if (safe) {
//                result++;
//            } else {
//
//            }
        }
        System.out.println(result);
    }
}
