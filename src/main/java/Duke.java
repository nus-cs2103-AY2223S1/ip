import java.util.*;
public class Duke {
    public static void main(String[] args) {
        initProgram();
        program();
        exitProgram();
    }

    public static void initProgram() {
        String logo = " _    _ ______ _      _      ____     ______ _____   ____  __  __    _____  _    _ _  ________ \n"
                    + "| |  | |  ____| |    | |    / __ \\   |  ____|  __ \\ / __ \\|  \\/  |  |  __ \\| |  | | |/ /  ____|\n"
                    + "| |__| | |__  | |    | |   | |  | |  | |__  | |__) | |  | | \\  / |  | |  | | |  | | ' /| |__   \n"
                    + "|  __  |  __| | |    | |   | |  | |  |  __| |  _  /| |  | | |\\/| |  | |  | | |  | |  < |  __| \n"
                    + "| |  | | |____| |____| |___| |__| |  | |    | | \\ \\| |__| | |  | |  | |__| | |__| | . \\| |____\n"
                    + "|_|  |_|______|______|______\\____/   |_|    |_|  \\_\\\\____/|_|  |_|  |_____/ \\____/|_|\\_\\______|\n";
        System.out.println(logo);
        System.out.println("How may I help you today?");
        newLine();
    }

    public static void program() {
        Scanner sc = new Scanner(System.in);
        String input = null;
        LinkedList<String> lst = new LinkedList<>();
        do {
            input = sc.nextLine();
            newLine();
            switch(input) {
                case "bye":
                    break;
                case "list":
                    printList(lst);
                    break;
                default:
                    lst.add(input);
                    System.out.println("added: " + input);
                    newLine();
            }
        } while (!input.equals("bye"));
    }

    public static void printList(List lst) {
        if (lst.size() == 0) {
            System.out.println("List is empty!");
        }
        for (int i = 1; i <= lst.size(); i++) {
            System.out.println(i + ": " + lst.get(i - 1));
        }
        newLine();
    }


    public static void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        newLine();
    }

    public static void newLine() {
        System.out.println("____________________________________________________________");
    }
}
