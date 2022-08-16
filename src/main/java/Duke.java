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
                    System.out.printf("\t\t\t%s\n", this.taskList.get(indexOfTask));
                    break;
                case "unmark":
                    int indexToUnmark = Integer.parseInt(splitSS[1]) - 1;
                    this.taskList.get(indexToUnmark).uncompleted();
                    System.out.println("\t\tOK, I've marked this task as not done yet:");
                    System.out.printf("\t\t\t%s\n", this.taskList.get(indexToUnmark));
                    break;
                case "todo":
                    System.out.println("\t\tGot it. I've added this task");
                    StringBuilder todo = new StringBuilder();

                    for (int i = 1; i < splitSS.length; ++i) {
                        todo.append(' ');
                        todo.append(splitSS[i]);
                    }

                    ToDo todoTask = new ToDo(todo.toString());
                    taskList.add(todoTask);
                    System.out.printf("\t\t\t %s\n", todoTask);
                    System.out.printf("\t\tNow you have %d tasks in the list.\n", this.taskList.size());
                    break;
                case "deadline":
                    System.out.println("\t\tGot it. I've added this task");
                    StringBuilder deadline = new StringBuilder();

                    for (int i = 1; i < splitSS.length; ++i) {
                        if (splitSS[i].equals("/by")) break;
                        deadline.append(" " + splitSS[i]);
                    }

                    String date = ss.split("/by")[1].trim();
                    Deadline deadlineTask = new Deadline(deadline.toString(), date);
                    taskList.add(deadlineTask);
                    System.out.printf("\t\t\t %s\n", deadlineTask);
                    System.out.printf("\t\tNow you have %d tasks in the list.\n", this.taskList.size());
                    break;
                case "event":
                    System.out.println("\t\tGot it. I've added this task");
                    StringBuilder event = new StringBuilder();

                    for (int i = 1; i < splitSS.length; ++i) {
                        if (splitSS[i].equals("/at")) break;
                        event.append(" " + splitSS[i]);
                    }

                    Event eventTask = new Event(event.toString(), ss.split("/at")[1].trim());
                    taskList.add(eventTask);
                    System.out.printf("\t\t\t %s\n", eventTask);
                    System.out.printf("\t\tNow you have %d tasks in the list.\n", this.taskList.size());
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.printf("\t\t %d. %s\n", i + 1, taskList.get(i));
        }
    }

    private void add(Task s) {
        taskList.add(s);
    }

}
