import java.util.*;
public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Task[] taskArr = new Task[100];
        int taskCounter = 0;
        String input = "";
        System.out.print("Hi I'm catBot!\nHow can I help you nya?\n");

        while (!input.equals("bye")) {

            input = sc.nextLine();
            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];

            switch(command) {
                case "todo": {
                    String[] splitDes = splitInput[1].split("/", 2);
                    String des = splitDes[0];
                    Task task = new ToDo(des);

                    System.out.println("Roger nya! Added this task:\n  " + task.toString());
                    taskArr[taskCounter++] = task;
                    System.out.println("Now you have " + taskCounter + " task(s) in the list nya.");
                    break;
                }

                case "deadline": {
                    String[] splitDes = splitInput[1].split("/", 2);
                    String[] splitWhen = splitDes[1].split(" ", 2);
                    String des = splitDes[0];
                    String when = splitWhen[1];
                    Task task = new Deadline(des, when);

                    System.out.println("Roger nya! Added this task:\n  " + task.toString());
                    taskArr[taskCounter++] = task;
                    System.out.println("Now you have " + taskCounter + " task(s) in the list nya.");
                    break;
                }

                case "event": {
                    String[] splitDes = splitInput[1].split("/", 2);
                    String[] splitWhen = splitDes[1].split(" ", 2);
                    String des = splitDes[0];
                    String when = splitWhen[1];
                    Task task = new Event(des, when);

                    System.out.println("Roger nya! Added this task:\n  " + task.toString());
                    taskArr[taskCounter++] = task;
                    System.out.println("Now you have " + taskCounter + " task(s) in the list nya.");
                    break;
                }

                case "mark": {
                    String[] splitTask = splitInput[1].split("/", 2);
                    String task = splitTask[0];

                    int index = Integer.parseInt(task);
                    taskArr[index - 1].markAsDone();
                    System.out.println("I've marked this task as done. Great job nya!");
                    break;
                }

                case "unmark": {
                    String[] splitTask = splitInput[1].split("/", 2);
                    String task = splitTask[0];
                    int index = Integer.parseInt(task);
                    taskArr[index - 1].markAsNotDone();
                    System.out.println("Roger nya! I've marked this task as not done.");
                    break;
                }

                case "list":
                    System.out.println("Here are the tasks in your list nya:");
                    for (int i = 0; i < taskArr.length; i++) {
                        Task t = taskArr[i];
                        if (taskArr[i] != null) {
                            System.out.println((i + 1) + t.toString());
                        } else {
                            break;
                        }
                    }
                    break;

                default:
                    break;
            }
        }

        System.out.print("Bye nya! Hope to see you again nya.");

    }
}
