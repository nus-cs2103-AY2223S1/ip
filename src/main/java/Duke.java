import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> taskList;
    private Scanner scanner;

    public Duke() {
        this.taskList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\tHello I'm Duke\n \tWhat can I do for you?");


        while (scanner.hasNextLine()) {
            String ss = scanner.nextLine();

            String[] splitSS = ss.split(" ");

            switch (splitSS[0]) {
                case "bye":
                    terminate();
                    return;
                case "list":
                    list();
                    break;
                case "mark":
                    int indexOfTask = Integer.parseInt(splitSS[1]) - 1;
                    this.taskList.get(indexOfTask).completed();
                    System.out.println("\t\tNice! I've marked this task as done:");
                    System.out.printf("\t\t\t[%s] %s\n", this.taskList.get(indexOfTask).getStatusIcon(),
                                                         this.taskList.get(indexOfTask));
                    break;
                case "unmark":
                    int indexToUnmark = Integer.parseInt(splitSS[1]) - 1;
                    this.taskList.get(indexToUnmark).uncompleted();
                    System.out.println("\t\tOK, I've marked this task as not done yet:");
                    System.out.printf("\t\t\t[%s] %s\n", this.taskList.get(indexToUnmark).getStatusIcon(),
                                                       this.taskList.get(indexToUnmark));
                    break;
                default:
                    Task task = new Task(ss);
                    add(task);
                    System.out.println("\t\tadded: " + ss);

            }

        }
    }

    private void terminate() {
        System.out.println("\t\tBye. Hope to see you again soon!");
    }

    private void list() {
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.printf("\t\t %d. [%s] %s\n", i + 1, taskList.get(i).getStatusIcon(),
                                                           taskList.get(i));
        }
    }

    private void add(Task s) {
        taskList.add(s);
    }

}
