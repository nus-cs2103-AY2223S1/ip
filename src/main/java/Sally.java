import java.util.ArrayList;
import java.util.Scanner;

public class Sally {
    private static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        border();
        System.out.println("Hello! I'm Sally \uD83D\uDE04");
        System.out.println("What can I do for you?");
        border();
        messaging();
    }

    private static void messaging() {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        if (message.equals("bye")) {
            border();
            System.out.println("Until next time!");
            border();
        } else if (message.equals("list")) {
            border();
            System.out.println("Here's your current list:");
            System.out.println(printList());
            border();
            messaging();
        } else {
            border();
            list.add(message);
            System.out.println("'" + message + "' added to your list!");
            border();
            messaging();
        }
    }

    private static String printList() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            int number = i + 1;
            output = output + number + ". " + list.get(i) + "\n";
        }
        return output;
    }

    private static void border () {
        System.out.println("-------------------------------------------------------------------------------------");
    }
}