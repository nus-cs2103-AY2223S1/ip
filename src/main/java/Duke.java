import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String LINES = "_________________________\n";
    public static final String START = "Hey there! I'm Duke.\nWhat do you want to do today?\n";
    public static final String END = "Bye! Hope you had fun!";
    public static final String DONE = "Nice! I've marked this task as done:\n";
    public static final String UNDONE =  "OK, I've marked this task as not done yet:\n";
    private ArrayList<Task> tasks;
    private boolean ended;


    public Duke() {
        this.tasks = new ArrayList<>();
        this.ended = false;
    }

    public void printMessage(String message) {
        System.out.print(LINES + message + "\n" + LINES);
    }

    public void markTask(int index) {
        Task task = this.tasks.get(index);
        task.markAsDone();
        printMessage(DONE + task);
    }

    public void unmarkTask(int index) {
        Task task = this.tasks.get(index);
        task.markAsUndone();
        printMessage(UNDONE + task);
    }

    public void end() {
        printMessage(END);
        this.ended = true;
    }

    public void listTasks() {
        StringBuilder allTasks = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); i++) {
            allTasks.append(i + ". " + this.tasks.get(i - 1));
            if (i < this.tasks.size()) {
                allTasks.append("\n");
            }
        }
        printMessage(allTasks.toString());
    }

    public void addTask(String command) {
        printMessage("added: " + command);
        this.tasks.add(new Task(command));
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        printMessage(START);
        while(!ended) {
            String command = sc.nextLine();
            String[] splitCommand = command.split("\\s+");
            switch (splitCommand[0]) {
                case "bye":
                    end();
                    break;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    markTask(Integer.parseInt(splitCommand[1]) - 1);
                    break;
                case "unmark":
                    unmarkTask(Integer.parseInt(splitCommand[1]) - 1);
                    break;
                default:
                    addTask(command);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
