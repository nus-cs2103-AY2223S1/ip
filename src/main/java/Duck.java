import java.util.ArrayList;
import java.util.Scanner;

public class Duck {
    public static void main(String[] args) {
        System.out.println("Hello! Got any grapes?");
        Scanner scanner = new Scanner(System.in);
        String word = "";
        ArrayList<String> list = new ArrayList<>();
        while (!word.equals("bye")) {
            word = scanner.next();
            if (word.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            }
            System.out.println("added: " + word);
            list.add(word);
        }
        System.out.println("Quack!");
    }
}
