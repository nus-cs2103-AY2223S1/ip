import java.util.Scanner;
import java.util.ArrayList;

public class Skyler {
    public static void printTask(Task task, int num) {
        System.out.println("I've added the following task:");
        String str = String.format("  %s", task);
        System.out.println(str);
        String summary = String.format("Total number of tasks: %d", num);
        System.out.println(summary);
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Good day, mate! I'm Skyler\nHow can I help you?\n");
            while (sc.hasNext()) {
                String description = sc.nextLine();

                if (description.equals("bye")) {
                    System.out.println("Bye! See you again soon!");
                    break;
                } else if (description.equals("list")) {
                    System.out.println("Tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        String str = String.format("%d.%s", i + 1, tasks.get(i));
                        System.out.println(str);
                    }
                } else if (description.startsWith("mark")) {
                    int item = Integer.parseInt(description.substring(description.length() - 1));
                    Task currTask = tasks.get(item - 1);
                    currTask.markAsDone();
                    System.out.println("You have completed this task:");
                    String show = String.format("  %s", currTask);
                    System.out.println(show);
                } else if (description.startsWith("unmark")) {
                    int item = Integer.parseInt(description.substring(description.length() - 1));
                    Task currTask = tasks.get(item - 1);
                    currTask.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    String show = String.format("  %s", currTask);
                    System.out.println(show);
                } else if (description.startsWith("todo")) {
                    String[] arr = description.split(" ", 2);
                    if (description.trim().equals("todo")) {
                        throw new EmptyDescriptionException();
                    }
                    Todo newTodo = new Todo(arr[1]);
                    tasks.add(newTodo);

                    printTask(newTodo, tasks.size());
                } else if (description.startsWith("deadline")) {
                    String[] arr = description.split(" ", 2);
                    if (description.trim().equals("deadline")) {
                        throw new EmptyDescriptionException();
                    }
                    String item = arr[1];
                    String[] arr1 = item.split(" /by ", 2);

                    Deadline newDeadline = new Deadline(arr1[0], arr1[1]);
                    tasks.add(newDeadline);

                    printTask(newDeadline, tasks.size());
                } else if (description.startsWith("event")) {
                    String[] arr = description.split(" ", 2);
                    if (description.trim().equals("event")) {
                        throw new EmptyDescriptionException();
                    }
                    String item = arr[1];
                    String[] arr1 = item.split(" /at ", 2);

                    Event newEvent = new Event(arr1[0], arr1[1]);
                    tasks.add(newEvent);

                    printTask(newEvent, tasks.size());
                } else if (description.startsWith("delete")) {
                    int item = Integer.parseInt(description.substring(description.length() - 1));
                    Task currTask = tasks.get(item - 1);
                    System.out.println("The following task will be removed:");
                    String show = String.format("  %s", currTask);
                    System.out.println(show);
                    tasks.remove(item - 1);
                    String str = String.format("Total number of tasks: %d", tasks.size());
                    System.out.println(str);
                } else {
                    throw new TaskNotRecognisedException();
                }
            }
        } catch (EmptyDescriptionException e) {
            System.out.println("Oh no! Cannot insert task without description.");
        } catch (TaskNotRecognisedException e) {
            System.out.println("Oops! I'm sorry, but I don't know what that means.");
        }
    }
}
