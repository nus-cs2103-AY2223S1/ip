import java.util.Scanner;
import java.util.ArrayList;


public class Bob {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>(100);
        Scanner scanner = new Scanner(System.in);
        System.out.println("hey, i'm bob!\ndo you need help?");
        String reply = scanner.nextLine();

        int taskCount = 0;

        while (!reply.equalsIgnoreCase("bye")) {
            if (reply.equalsIgnoreCase("list")) {
                int index = 1;
                String list = "";
                for (Task task : tasks) {
                    list = list + "\n" + (index++) + ". " + task.toString();
                }
                System.out.println("here are all your tasks!" + list);
            } else if (reply.toLowerCase().matches("mark(.*)")) {
                try {
                    int index = Integer.valueOf(reply.replaceAll("[^0-9]", ""));
                    Task task = tasks.get(index - 1);
                    task.toMark(true);
                    System.out.println("yay! you've completed a task!\n" + task.toString());
                } catch(NumberFormatException e) {
                    System.out.println("which task to mark?");
                }

            } else if (reply.toLowerCase().matches("unmark(.*)")) {
                try {
                    int index = Integer.valueOf(reply.replaceAll("[^0-9]", ""));
                    Task task = tasks.get(index - 1);
                    task.toMark(false);
                    System.out.println("aw...i guess there's another task.\n" + task.toString());
                } catch(NumberFormatException e) {
                    System.out.println("which task to unmark?");
                }
            } else if (reply.toLowerCase().matches("remove(.*)") ) {
                try {
                    int index = Integer.valueOf(reply.replaceAll("[^0-9]",""));
                    ArrayList<Task> temp = new ArrayList<>(taskCount);
                    Task removedTask = tasks.get(index - 1);
                    for (int i = 0; i < taskCount; i++) {
                        if (i == (index - 1)) {
                            continue;
                        } else {
                            temp.add(tasks.get(i));
                        }
                    }
                    tasks = temp;
                    taskCount = taskCount - 1;
                    System.out.println("that's one less task for you! removed:" + "\n  " + removedTask.toString() + "\njust " + taskCount + " tasks left!");
                } catch(NumberFormatException e) {
                    System.out.println("which task do you want to delete?");
                }
            } else {
                if (reply.toLowerCase().matches("todo(.*)")) {
                    try {
                        String taskName = reply.substring(5);
                        ToDo newTask = new ToDo(taskName);
                        tasks.add(newTask);
                        taskCount = taskCount + 1;
                        System.out.println("okay! new task:" + "\n  " + newTask.toString() + "\njust " + taskCount + " tasks left!");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops! What do you want to do?");
                    }
                } else if (reply.toLowerCase().matches("deadline(.*)")) {
                    try {
                        String[] temp = reply.split("/");
                        String taskName = temp[0].substring(9);
                        String time = temp[1].substring(3);
                        Deadline newTask = new Deadline(taskName, time);
                        tasks.add(newTask);
                        taskCount = taskCount + 1;
                        System.out.println("okay! new task:" + "\n  " + newTask.toString() + "\njust " + taskCount + " tasks left!");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops! What's the deadline again?");
                    }
                } else if (reply.toLowerCase().matches("event(.*)")) {
                    try {
                        String[] temp = reply.split("/");
                        String taskName = temp[0].substring(6);
                        String time = temp[1].substring(3);
                        Event newTask = new Event(taskName, time);
                        tasks.add(newTask);
                        taskCount = taskCount + 1;
                        System.out.println("okay! new task:" + "\n  " + newTask.toString() + "\njust " + taskCount + " tasks left!");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops! When's your event again?");
                    }
                } else {
                    System.out.println("Oops! Maybe try something else? :(");
                }
            }
            reply = scanner.nextLine();
        }

        System.out.println("bye\nsee you again!");
    }
}
