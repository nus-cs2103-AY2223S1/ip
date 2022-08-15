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
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String text = input.next();
        while (!text.equals("bye")) {
            switch (text) {
                case "list" :
                    for (int i = 0; i < lst.size(); i++) {
                        Task task = lst.get(i);
                        System.out.printf("\t%d. %s\n", i+1, task.toString());
                    }
                    break;
                case "mark":
                    int index = input.nextInt();
                    Task taskToBeMarked = lst.get(index-1);
                    taskToBeMarked.markAsDone();
                    System.out.printf("\tNice! I've marked this task as done:\n\t%s\n",
                            taskToBeMarked.toString());
                    break;

                case "unmark":
                    int index2 = input.nextInt();
                    Task taskToBeUnmarked = lst.get(index2-1);
                    taskToBeUnmarked.markAsUndone();
                    System.out.printf("\tOkay, I've marked this task as not done yet:\n\t%s\n",
                            taskToBeUnmarked.toString());
                    break;

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
