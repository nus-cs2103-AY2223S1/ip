import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //ArrayList to store tasks
        List<Task> lst = new ArrayList<>();
        //Scanner object to take in input from user
        Scanner input = new Scanner(System.in);
        //Welcome message
        System.out.println("Hello! I'm Donovan\nWhat can I do for you?");
        String text = input.next();
        while (!text.equals("bye")) {
            switch (text) {
                //Handle case when task aTodo
                case "todo" :
                    String tDescription = input.nextLine();
                    Task todo = new Todo(tDescription);
                    lst.add(todo);
                    int size = lst.size();
                    System.out.printf("\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n",
                            todo,
                            size);
                    break;

                //Handle case when task is a deadline
                case "deadline":
                    String str = input.nextLine();
                    String dDescription = str.substring(0, str.indexOf('/') - 1);
                    String dBy = str.substring(str.indexOf('/') + 4);
                    Task deadline = new Deadline(dDescription, dBy);
                    lst.add(deadline);
                    int size2 = lst.size();
                    System.out.printf("\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n",
                            deadline,
                            size2);
                    break;

                //Handle case when task is an event
                case "event":
                    String str2 = input.nextLine();
                    String eDescription = str2.substring(0, str2.indexOf('/') - 1);
                    String eAt = str2.substring(str2.indexOf('/') + 4);
                    Task event = new Event(eDescription, eAt);
                    lst.add(event);
                    int size3 = lst.size();
                    System.out.printf("\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n",
                            event,
                            size3);
                    break;

                //Handle case when user wants to list tasks
                case "list" :
                    System.out.println("\tHere are the tasks in your list.");
                    for (int i = 0; i < lst.size(); i++) {
                        Task task = lst.get(i);
                        System.out.printf("\t%d. %s\n", i+1, task.toString());
                    }
                    break;

                //Handle case when user wants to mark task
                case "mark":
                    int index = input.nextInt();
                    Task taskToBeMarked = lst.get(index-1);
                    taskToBeMarked.markAsDone();
                    System.out.printf("\tNice! I've marked this task as done:\n\t%s\n",
                            taskToBeMarked);
                    break;

                //Handle case when user wants to unmark task
                case "unmark":
                    int index2 = input.nextInt();
                    Task taskToBeUnmarked = lst.get(index2-1);
                    taskToBeUnmarked.markAsUndone();
                    System.out.printf("\tOkay, I've marked this task as not done yet:\n\t%s\n",
                            taskToBeUnmarked);
                    break;

                //Default case of adding normal general task to list
                default:
                    text += input.nextLine();
                    System.out.printf("\tAdded: %s\n", text);
                    Task newTask = new Task(text);
                    lst.add(newTask);
                    break;
            }
            text = input.next();
        }
        //Goodbye message
        System.out.println("\tBye! Hope to see you again soon!");
    }
}

