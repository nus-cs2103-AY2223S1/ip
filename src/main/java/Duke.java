import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("Hello! I'm Milk");
        System.out.println("what can I do for you?");
        Scanner sc = new Scanner(System.in);

        while (!exit) {
            String userReply = sc.nextLine();

            switch (userReply) {
                case "bye":
                    exit = true;
                    break;

                case "list":
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1 + "." + list.get(i));
                    }
                    break;

                default:
                    System.out.println("added: " + userReply);
                    list.add(userReply);
                    break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
