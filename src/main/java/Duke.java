import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Duke {

    public static void main(String[] args) {
        intro();
        processCommand();
        outro();
    }

    private static void intro() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void processCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        List<String> userList = new ArrayList<String>();
        int index = 0;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                userList.forEach(System.out::println);
            }
            else {
                index++;
                String labelledCommand = String.format("\t%d. %s", index, command);
                userList.add(labelledCommand);
                System.out.printf("\tadded: %s\n", command);
            }
            command = sc.nextLine();
        }
    }

    private static void outro(){
        System.out.println("\tBye. Hope to see you again");
    }
}
