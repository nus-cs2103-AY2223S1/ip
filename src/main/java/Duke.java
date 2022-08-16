import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("------------------------------");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> commands = new ArrayList<>();
        while(true) {
            String command = scanner.nextLine();
            String[] commandArr = command.split(" ");
            System.out.println("------------------------------");
            switch(commandArr[0]) {
                case "mark":
                    int markI;
                    try {
                        markI = Integer.parseInt(commandArr[1]);
                    } catch (NumberFormatException e) {     // if second word not integer
                        continue;
                    }
                    if (markI >= 1 && markI <= commands.size()) {   // ensure i given is within range
                        commands.get(markI - 1).mark();
                        break;
                    }
                case "unmark":
                    int unmarkI;
                    try {
                        unmarkI = Integer.parseInt(commandArr[1]);
                    } catch (NumberFormatException e) {     // if second word not integer
                        continue;
                    }
                    if (unmarkI >= 1 && unmarkI <= commands.size()) {   // ensure i given is within range
                        commands.get(unmarkI - 1).unmark();
                        break;
                    }
                default:
                    switch (command) {
                        case "bye":
                            System.out.println("Bye. Hope to see you again soon!");
                            break;
                        case "list":
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < commands.size(); i++) {
                                System.out.printf("%d. %s\n", i + 1, commands.get(i).toString());
                            }
                            break;
                        default:
                            commands.add(new Task(false, command));
                            System.out.printf("added: %s\n", command);
                            break;
                    }
            }
            System.out.println("------------------------------");
        }
    }
}
