import java.util.Scanner;
import java.util.ArrayList;

public class Scruffles {
    public static void main(String[] args) {

        System.out.println("woof! I'm scruffles the task tracking doggo\n" + "what can I woof for you today?");

        Scanner sc = new Scanner(System.in);
        String input = "";

        ArrayList<Task> list = new ArrayList<Task>(100);

        while (true) {

            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("woof see you again woof!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    String output = (i + 1) + "." + list.get(i).toString();
                    System.out.println(output);
                }
            } else if (input.startsWith("mark")) {
                int listRef = input.charAt(4) - 32;
                list.get(listRef).setDone();
                System.out.println("woof! the task is now marked as done woof!\n" + list.get(listRef).toString());
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }
}