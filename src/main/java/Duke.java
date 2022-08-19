import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final int MAX_TASK_SIZE = 100;

    public static void main(String[] args) {
        //start up sequence
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>(MAX_TASK_SIZE);
        greet();

        while (true) {
            String input = sc.nextLine().strip();
            String action = input.split(" ")[0];
            switch (action) {
            case "bye":
                sayonara();
                return;

            case "list":
                showTasks(tasks);
                break;

            case "mark":
                markTask(tasks, Integer.parseInt(input.split(" ")[1]));
                break;

            case "unmark":
                unMarkTask(tasks, Integer.parseInt(input.split(" ")[1]));
                break;

            default:
                printLine();
                Task task = new Task(input);
                tasks.add(task);
                printIndent("added: " + task);
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

    public static void printBlock(String s) {
        printLine();
        printIndent(s);
        printLine();
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
        printBlock("Boo! Bye bye... :(");
    }

    public static void showTasks(List<Task> Tasks) {
        printBlock("Here are the tasks in your list:");
        for (int i =1; i <= Tasks.size(); i++) {
            printIndent(i + "." + Tasks.get(i - 1));
        }
    }

    public static void markTask(List<Task> Tasks, int index) {
        Task task = Tasks.get(index - 1);
        task.setDone(true);
        printBlock(String.format("Hehe okay guess this is now done\n"
                + "  %s", task));
    }

    public static void unMarkTask(List<Task> Tasks, int index) {
        Task task = Tasks.get(index - 1);
        task.setDone(false);
        printBlock(String.format("Ooops, you haven't done this yet? Here ya go:\n"
                + "  %s", task));
    }
}
