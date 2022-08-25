import java.util.Scanner;

public class Ui {

    private Scanner sc;

    private TaskList taskList;

    public Ui(TaskList taskList) {
        sc = new Scanner(System.in);
        this.taskList = taskList;
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(DukeException e) {
        System.out.println(e);
    }


    /**
     * Prints the number of current tasks, as well as how many are completed.
     */
    public void printListCount() {
        System.out.println("  You have " + this.taskList.size() + " tasks currently, " + Task.totalDone + " are completed");
    }

    /**
     * Prints a list of all the tasks.
     */
    public void printList() {
        if (this.taskList.size() == 0) {
            System.out.println("  List is empty!");
        } else {
            System.out.println("  List of tasks:");
            for (int i = 1; i <= this.taskList.size(); i++) {
                System.out.println("\t" + i + ": " + this.taskList.get(i - 1));
            }
            printListCount();
        }
    }

    /**
     * Prints the hello introduction to Duke.
     */
    public void startMessage() {
        String logo = " _    _ ______ _      _      ____     ______ _____   ____  __  __    _____  _    _ _  ________ \n"
                + "| |  | |  ____| |    | |    / __ \\   |  ____|  __ \\ / __ \\|  \\/  |  |  __ \\| |  | | |/ /  ____|\n"
                + "| |__| | |__  | |    | |   | |  | |  | |__  | |__) | |  | | \\  / |  | |  | | |  | | ' /| |__   \n"
                + "|  __  |  __| | |    | |   | |  | |  |  __| |  _  /| |  | | |\\/| |  | |  | | |  | |  < |  __| \n"
                + "| |  | | |____| |____| |___| |__| |  | |    | | \\ \\| |__| | |  | |  | |__| | |__| | . \\| |____\n"
                + "|_|  |_|______|______|______\\____/   |_|    |_|  \\_\\\\____/|_|  |_|  |_____/ \\____/|_|\\_\\______|\n";
        System.out.println(logo);
        System.out.println("  How may I help you today?");
        newLine();
    }

    /**
     * Prints the exit program text.
     */
    public void exitMessage() {
        System.out.println("Bye:( Hope to see you again soon!");
        newLine();
    }

    /**
     * Prints a new line.
     */
    public void newLine() {
        System.out.println("________________________________________________________________________" +
                "_______________________");
    }
}
