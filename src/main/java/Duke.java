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
        String content = command.getContent();
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
                try {
                    if (command.hasValidKeywork() && command.hasValidTaskDesc()) {
                        id++;
                        if (keyword.equals("todo")) {
                            Task newTask = new Todo(content, id, 'T');
                            userList.add(newTask);
                            addTaskConfirmation(newTask, id);
                        } else if (keyword.equals("deadline")) {
                            Task newTask = new Deadline(content, id, 'D');
                            userList.add(newTask);
                            addTaskConfirmation(newTask, id);
                        } else if (keyword.equals("event")) {
                            Task newTask = new Event(content, id, 'E');
                            userList.add(newTask);
                            addTaskConfirmation(newTask, id);
                        }
                    }
                } catch (DukeException ex){
                    System.out.println(ex);
                }
            }
            command = new Command(sc.nextLine());
            keyword = command.getKeyword();
            content = command.getContent();
        }
    }

    private static void addTaskConfirmation(Task task, int id) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.taskStatus());
        System.out.printf("Now you have %d tasks in the list.\n", id);
    }

    private static void outro(){
        System.out.println("\tBye. Hope to see you again");
    }
}
