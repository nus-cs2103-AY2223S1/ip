import java.util.Scanner;
import java.util.ArrayList;

public class Scruffles {
    public static void main(String[] args) {

        System.out.println("woof! I'm scruffles the task tracking doggo\n" + "what can I woof for you today?");

        Scanner sc = new Scanner(System.in);
        String input = "";

        ArrayList<String> listRef = new ArrayList<String>(100);

        while (true) {

            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("woof see you again woof!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < listRef.size(); i++) {
                    String output = (i + 1) + ". " + listRef.get(i);
                    System.out.println(output);
                }
            } else {
                listRef.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}