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

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true; // mark tasks as done
    }

    public void markAsUndone() {
        this.isDone = false; // mark task as undone
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
