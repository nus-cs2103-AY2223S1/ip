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
                        System.out.printf("\t%d.[%s] %s\n", i+1, task.getStatusIcon(),task.description);
                    }
                    break;
                case "mark":
                    int index = input.nextInt();
                    Task taskToBeMarked = lst.get(index-1);
                    taskToBeMarked.markAsDone();
                    System.out.printf("\tNice! I've marked this task as done:\n\t[%s] %s\n",
                            taskToBeMarked.getStatusIcon(),
                            taskToBeMarked.description);
                    break;

                case "unmark":
                    int index2 = input.nextInt();
                    Task taskToBeUnmarked = lst.get(index2-1);
                    taskToBeUnmarked.markAsUndone();
                    System.out.printf("\tOkay, I've marked this task as not done yet:\n\t[%s] %s\n",
                            taskToBeUnmarked.getStatusIcon(),
                            taskToBeUnmarked.description);
                    break;

                default:
                    text += input.nextLine();
                    System.out.printf("\tAdded: %s\n", text);
                    Task newTask = new Task(text);
                    lst.add(newTask);
                    break;
            }
            text = input.next();
//            if (text.equals("list")) {
//                for (int i = 0; i < lst.size(); i++) {
//                    Task task = lst.get(i);
//                    System.out.printf("\t%d.[%s] %s\n", i+1, task.getStatusIcon(),task.description);
//                }
//            } else if (text.equals("mark")) {
//                int index = input.nextInt();
//                Task taskToBeMarked = lst.get(index-1);
//                taskToBeMarked.markAsDone();
//                System.out.printf("\tNice! I've marked this task as done:\n\t[%s] %s\n",
//                        taskToBeMarked.getStatusIcon(),
//                        taskToBeMarked.description);
//            } else if (text.equals("unmark")) {
//                int index = input.nextInt();
//                Task taskToBeUnmarked = lst.get(index-1);
//                taskToBeUnmarked.markAsUndone();
//                System.out.printf("\tOkay, I've marked this task as not done yet:\n\t[%s] %s\n",
//                        taskToBeUnmarked.getStatusIcon(),
//                        taskToBeUnmarked.description);
//            } else {
//                text += input.nextLine();
//                System.out.printf("\tAdded: %s\n", text);
//                Task newTask = new Task(text);
//                lst.add(newTask);
//            }
//            text = input.next();
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

}