import java.util.Objects;
import java.util.Scanner;

public class Duck {
    public static void main(String[] args) {
        System.out.println("Hello! Got any grapes?");
        Scanner scanner = new Scanner(System.in);
        String word = "";
        while (!word.equals("bye")) {
            word = scanner.next();
            System.out.println(word);
        }
    }
}
