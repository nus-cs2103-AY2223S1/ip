import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<String> userData = new ArrayList<>();

        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
        System.out.println("-----------------------------------");
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equalsIgnoreCase(line)) {
                for (int i = 0; i < userData.size(); i++) {
                    System.out.format("%s. %s\n", i, userData.get(i));
                }
            } else{
                userData.add(line);
                System.out.format("added: %s\n", line);
            }
            System.out.println("-----------------------------------");
        }
    }


}
