import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskArray = new ArrayList<>();
        int taskCount = 0;

        while (true) {
            String str = sc.nextLine();
            Scanner sc2 = new Scanner(str);
            String first = sc2.next();

            switch (first) {
                case ("bye"):
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                    break;

                case ("list"):
                    System.out.printf("Here are the tasks in your list:\n");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.printf("%d.%s\n", i + 1, taskArray.get(i).toString());
                    }
                    break;

                case ("todo") :
                    try {
                        Task todo = new ToDo(sc2.nextLine());
                        taskArray.add(todo);
                        taskCount++;
                        System.out.printf("Got it. I've added this task:\n" +
                                "%s\n" +
                                "Now you have %d tasks in the list.\n", todo, taskCount);

                    } catch (NoSuchElementException e) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;

                case ("deadline") :
                    String description = "";
                    while (!sc2.hasNext("/by")) {
                        description += sc2.next();
                    }
                    String discard = sc2.next();
                    String date = sc2.nextLine();
                    Deadline deadline = new Deadline(description, date);
                    taskArray.add(deadline);
                    taskCount++;
                    System.out.printf("Got it. I've added this task:\n" +
                            "  %s\n" +
                            "Now you have %d tasks in the list.\n", deadline.toString(), taskCount);
                    break;

                case ("event") :
                    String description1 = "";
                    while (!sc2.hasNext("/at")) {
                        description1 += sc2.next() + " ";
                    }
                    String discard1 = sc2.next();
                    String date1 = sc2.nextLine();
                    Event event = new Event(description1, date1);
                    taskArray.add(event);
                    taskCount++;
                    System.out.printf("Got it. I've added this task:\n" +
                            "  %s\n" +
                            "Now you have %d tasks in the list.\n", event.toString(), taskCount);
                    break;

                case ("mark") :
                    int taskNo = sc2.nextInt();
                    taskArray.get(taskNo).markAsDone();
                    System.out.printf("Nice! I've marked this task as done: \n" +
                            "  [X] %s\n", taskArray.get(taskNo).description);
                    break;

                case ("unmark") :
                    int taskNo1 = sc2.nextInt();
                    taskArray.get(taskNo1).markAsUndone();
                    System.out.printf("OK, I've marked this task as not done yet: \n" +
                            "  [ ] %s\n", taskArray.get(taskNo1).description);
                    break;

                case ("delete") :
                    int taskNo2 = sc2.nextInt();
                    taskArray.remove(taskNo2);
                    System.out.printf("Noted. I've removed this task:\n" +
                            "  %s\n" +
                            "Now you have %d tasks in the list.\n", taskArray.get(taskNo2), taskCount);
                    break;

                default :
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
        }
    }
}

