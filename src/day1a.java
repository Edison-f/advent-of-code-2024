import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class day1a {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day1.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        PriorityQueue<Integer> queue1 = new PriorityQueue<>();
        PriorityQueue<Integer> queue2 = new PriorityQueue<>();
        while(in.hasNextLine()) {
            String[] split = in.nextLine().split(" {3}");
            queue1.add(Integer.parseInt(split[0]));
            queue2.add(Integer.parseInt(split[1]));
        }
        while(!queue1.isEmpty()) {
            result += Math.abs(queue1.poll() - queue2.poll());
        }
        System.out.println(result);
    }
}
