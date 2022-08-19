import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final int MAX_TASK_SIZE = 100;

    public static void main(String[] args) {
        //start up sequence
        Scanner sc = new Scanner(System.in);
        List<Task> Tasks = new ArrayList<Task>(MAX_TASK_SIZE);
        greet();

        while (true) {
            String input = sc.nextLine().strip();
            switch (input) {
            case "bye":
                sayonara();
                return;

            case "list":
                showTasks(Tasks);
                break;

            default:
                printLine();
                Task curr = new Task(input);
                Tasks.add(curr);
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

    public static void showTasks(List<Task> Tasks) {
        printLine();
        printIndent("Here are the tasks in your list:");
        for (int i =1; i <= Tasks.size(); i++) {
            printIndent(i + "." + Tasks.get(i-1));
        }
        printLine();
    }

    public static void markTask(int index) {

    }
}
