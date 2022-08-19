import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final int MAX_TASK_SIZE = 100;

    public static void main(String[] args) {
        //start up sequence
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<Task>(MAX_TASK_SIZE);
        greet();

        while (true) {
            String input = sc.nextLine().strip();
            switch (input) {
            case "bye":
                sayonara();
                return;

            case "list":
                showList(list);
                break;

            default:
                printLine();
                Task curr = new Task(input);
                list.add(curr);
                printIndent("added: " + curr);
                printLine();
            }
        }
    }

    public static void printIndent(String s) {
        System.out.println("    " + s);
    }

    public static void printLine() {
        printIndent("____________________________________________________________");
    }

    public static void greet() {
        printLine();
        printIndent("Hello from\n");
        String logo = "_ .-') _     ('-.         .-') _  \n"
                + "( (  OO) )   ( OO ).-.    ( OO ) ) \n"
                + " \\     .'_   / . --. /,--./ ,--,'  \n"
                + " ,`'--..._)  | \\-.  \\ |   \\ |  |\\  \n"
                + " |  |  \\  '.-'-'  |  ||    \\|  | ) \n"
                + " |  |   ' | \\| |_.'  ||  .     |/  \n"
                + " |  |   / :  |  .-.  ||  |\\    |   \n"
                + " |  '--'  /  |  | |  ||  | \\   |   \n"
                + " `-------'   `--' `--'`--'  `--'   \n";

        printIndent(logo + "Ouuuuuuuuuhhhhhh Spo0ky");
        printIndent("What can I do for you?");
        printLine();
    }

    public static void sayonara() {
        printLine();
        printIndent("Boo! Bye bye... :(");
        printLine();
    }

    public static void showList(List<Task> list) {
        printLine();
        printIndent("Here are the tasks in your list:");
        for (int i =1; i <= list.size(); i++) {
            printIndent(i + "." + list.get(i-1));
        }
        printLine();
    }
}
