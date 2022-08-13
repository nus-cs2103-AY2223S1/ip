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
            switch(splitInput[0]) {
                case "bye":
                    break;

                case "mark": {
                    int index = Integer.parseInt(splitInput[1]);
                    taskArr[index - 1].markAsDone();
                    System.out.println("I've marked this task as done. Great job nya!");
                    break;
                }

                case "unmark": {
                    int index = Integer.parseInt(splitInput[1]);
                    taskArr[index - 1].markAsNotDone();
                    System.out.println("Roger nya! I've marked this task as not done.");
                    break;
                }

                case "list":
                    for (int i = 0; i < taskArr.length; i++) {
                        Task t = taskArr[i];
                        if (taskArr[i] != null) {
                            System.out.println((i + 1) + ".[" + t.getStatusIcon() + "] " + t.getDescription());
                        } else {
                            break;
                        }
                    }
                    break;

                default:
                    System.out.println("Roger nya! Added " + input + ".");
                    taskArr[taskCounter++] =  new Task(input);
                    break;
            }
        }

        System.out.print("Bye nya! Hope to see you again nya.");

    }
}
