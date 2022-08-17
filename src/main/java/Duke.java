import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static Scanner input = new Scanner(System.in);

    public static void responseRepeater() {
        String response = input.nextLine();
        //Task[] taskStore = new Task[100];
        //int counter = 0;
        ArrayList<Task> taskStore = new ArrayList<>();

        while (!response.equals("bye")) {
            if (response.equals("list")) {
                System.out.println("     The following are your saved tasks: ");
                for (int i = 0; i < taskStore.size(); i++) {
                    Task t = taskStore.get(i);
                    System.out.println("       "
                                      + (i + 1)
                                      + "."
                                      + t.toString());
                }
            } else if (response.length() > 4 && response.substring(0, 4).equals("mark")) {
                int taskNumber = Integer.parseInt(response.substring(5, 6)) - 1;
                Task t = taskStore.get(taskNumber);
                t.markAsDone();
            } else if (response.length() > 6 && response.substring(0, 6).equals("unmark")) {
                int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
                Task t = taskStore.get(taskNumber);
                t.markAsUnDone();
            } else if (response.length() > 3 && response.substring(0, 4).equals("todo")) {
                if (response.length() <= 5) {
                    System.out.println("     Please add a task after 'todo'!");
                } else {
                    Task newTodo = new Todo(response.substring(5));
                    taskStore.add(newTodo);
                    //counter += 1;
                    System.out.println("     Ok! I have added this Todo task:\n"
                            + "       " + newTodo.toString() + "\n"
                            + "     You now have a total of " + taskStore.size() + " tasks!");
                }
            } else if (response.length() > 4 && response.substring(0, 5).equals("event")) {
                if (response.length() <= 6) {
                    System.out.println("     Please add a task after 'event'!");
                } else {
                    int separatorPosition = response.indexOf("/");
                    Task newEvent = new Event(
                            response.substring(6, separatorPosition - 1),
                            response.substring(separatorPosition + 4));
                    taskStore.add(newEvent);
                    //counter += 1;
                    System.out.println("     Ok! I have added this Event task:\n"
                            + "       " + newEvent.toString() + "\n"
                            + "     You now have a total of " + taskStore.size() + " tasks!");
                }
            } else if (response.length() > 7 && response.substring(0, 8).equals("deadline")) {
                if (response.length() <= 9) {
                    System.out.println("     Please add a task after 'deadline'!");
                } else {
                    int separatorPosition = response.indexOf("/");
                    Task newDeadline = new Deadline(
                            response.substring(9, separatorPosition - 1),
                            response.substring(separatorPosition + 4));
                    taskStore.add(newDeadline);
                    //counter += 1;
                    System.out.println("     Ok! I have added this Deadline task:\n"
                            + "       " + newDeadline.toString() + "\n"
                            + "     You now have a total of " + taskStore.size() + " tasks!");
                }
            } else if (response.length() > 5 && response.substring(0, 6).equals("delete")) {
                int taskNumber = Integer.parseInt(response.substring(7, 8)) - 1;
                Task t = taskStore.get(taskNumber);
                taskStore.remove(taskNumber);
                System.out.println("     Ok! I have removed the following task!: \n"
                                  + "       " + t.toString() + "\n"
                                  + "     You now have a total of " + taskStore.size() + " tasks!");
            } else {
                System.out.println("     Please specify one of the 3 commands before your task to add a task:\n"
                                  + "       todo\n"
                                  + "       event\n"
                                  + "       deadline\n");
            }
            response = input.nextLine();
        }
        System.out.println("     Sad to see you go! Visit me again soon!");
    }

    public static void main(String[] args) {
        String greetings = "Good day to you! I'm Jake\n"
                            + "How can I help you?";
        System.out.println(greetings);
        responseRepeater();
    }
}
