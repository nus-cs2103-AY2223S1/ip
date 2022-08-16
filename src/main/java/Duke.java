import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<String> commandList;
    private Scanner scanner;

    public Duke() {
        this.commandList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\tHello I'm Duke\n \tWhat can I do for you?");


        while (scanner.hasNextLine()) {
            String ss = scanner.nextLine();


            switch (ss) {
                case "bye":
                    terminate();
                    return;
                case "list":
                    list();
                    break;
                default:
                    add(ss);
                    System.out.println("\t\tadded: " + ss);

            }
        }
    }

    private void terminate() {
        System.out.println("\t\tBye. Hope to see you again soon!");
    }

    private void list() {
        for (int i = 0 ; i < commandList.size(); ++i) {
            System.out.printf("\t\t %d. %s\n", i + 1, commandList.get(i));
        }
    }

    private void add(String s) {
        commandList.add(s);
    }

}
