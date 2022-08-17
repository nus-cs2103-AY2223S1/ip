import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String introMsg = "Baby Yoda I am\n\tFor you, what can I do?";

        prettyPrint(introMsg);

        Scanner in = new Scanner(System.in);
        String s = "";

        Boolean isRunning = true;

        while (isRunning) {
            s = in.nextLine();
            String[] inputTokens = s.split(" ");

            switch (inputTokens[0]) {
                case "bye":
                    isRunning = false;
                    break;
                case "list":
                    StringBuilder list = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        list.append(i + 1).append(" ").append(tasks.get(i)).append(i != tasks.size() - 1 ? "\n\t" : "");
                    }
                    prettyPrint(list.toString());
                    break;
                case "mark": {
                    int taskNumber = Integer.parseInt(inputTokens[1]);
                    tasks.get(taskNumber - 1).markDone();
                    prettyPrint("mark this as done, I have: \n\t" + tasks.get(taskNumber - 1));
                    break;
                }
                case "unmark": {
                    int taskNumber = Integer.parseInt(inputTokens[1]);
                    tasks.get(taskNumber - 1).markNotDone();
                    prettyPrint("mark this as not done, I have: \n\t" + tasks.get(taskNumber - 1));
                    break;
                }
                case "todo": {
                    Task newTask = new ToDo(s);
                    tasks.add(newTask);
                    prettyPrint("this task, I've added: \n\t\t" + newTask + "\n\t" + "you have " + tasks.size() + " tasks left");
                    break;
                }
                case "deadline": {
                    int separatorIndex = s.indexOf("/by");
                    String description = s.substring(9, separatorIndex);
                    String by = s.substring(separatorIndex + 4, s.length());

                    Task newTask = new Deadline(description, by);
                    tasks.add(newTask);
                    prettyPrint("this task, I've added: \n\t\t" + newTask + "\n\t" + "you have " + tasks.size() + " tasks left");
                    break;
                }
                case "event": {
                    int separator = s.indexOf("/at");
                    String description = s.substring(6, separator);
                    String at = s.substring(separator + 4, s.length());

                    Task newTask = new Event(description, at);
                    tasks.add(newTask);
                    prettyPrint("this task, I've added: \n\t\t" + newTask + "\n\t" + "you have " + tasks.size() + " tasks left");
                    break;
                }
                default:
                    prettyPrint("Know that, I don't");
            }
        }

        String byeMsg = "See you soon, I will";
        prettyPrint(byeMsg);
    }

    private static void prettyPrint(String s) {
        String divider = "____________________________________________________________\n";
        System.out.println(divider + "\t" + s + "\n" + divider);
    }
}
