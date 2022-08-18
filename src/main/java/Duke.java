import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Duke {

    public static void main(String[] args) {
        intro();
        processCommand();
        outro();
    }

    private static void intro() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void processCommand() {
        Scanner sc = new Scanner(System.in);
        Command command = new Command(sc.nextLine());
        String keyword = command.getKeyword();
        List<Task> userList = new ArrayList<Task>();
        int id = 0;
        while (!keyword.equals("bye")) {
            if (keyword.equals("list")) {
                userList.forEach(System.out::println);
            } else if (keyword.equals("mark")) {
                int index = command.getContentId() - 1;
                if (index <= id) {
                    userList.set(index, userList.get(index).performTask());
                }
            } else if (keyword.equals("unmark")) {
                int index = command.getContentId() - 1;
                if (index <= id) {
                    userList.set(index, userList.get(index).undoTask());
                }
            } else {
                id++;
                if (command.getKeyword().equals("todo")) {
                    Task newTask = new Todo(command.getContent(), id, 'T');
                    userList.add(newTask);
                    addTaskConfirmation(newTask, id);
                } else if (command.getKeyword().equals("deadline")) {
                    Task newTask = new Deadline(command.getContent(), id, 'D');
                    userList.add(newTask);
                    addTaskConfirmation(newTask, id);
                } else if (command.getKeyword().equals("event")) {
                    Task newTask = new Event(command.getContent(), id, 'E');
                    userList.add(newTask);
                    addTaskConfirmation(newTask, id);
                }
                System.out.printf("\tadded: %s\n", command.getContent());
            }
            command = new Command(sc.nextLine());
            keyword = command.getKeyword();
        }
    }

    private static void addTaskConfirmation(Task task, int id) {
        System.out.println("Got it. I've added this task:");
        System.out.printf("\t%s\n", task.taskStatus());
        System.out.printf("Now you have %d tasks in the list.", id);
    }

    private static void outro(){
        System.out.println("\tBye. Hope to see you again");
    }
}
