import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static boolean isRunning = true;

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

        while (isRunning) {
            try {
                handleInput(in);
            } catch (DukeException de) {
                prettyPrint(de.getMessage());
            }
        }

        String byeMsg = "See you soon, I will";
        prettyPrint(byeMsg);
    }
    
    private static void handleInput(Scanner in) throws DukeException {
        String userInput = in.nextLine();
        
        String cmd = userInput.split(" ")[0];
        
        switch (cmd) {
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
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    tasks.get(taskNumber - 1).markDone();
                    prettyPrint("mark this as done, I have: \n\t" + tasks.get(taskNumber - 1));
                } catch (NumberFormatException | IndexOutOfBoundsException nfe) {
                    throw new DukeException("Please enter a valid task number!");
                }
                break;
            }
            case "unmark": {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    tasks.get(taskNumber - 1).markNotDone();
                    prettyPrint("mark this as not done, I have: \n\t" + tasks.get(taskNumber - 1));
                } catch (NumberFormatException | IndexOutOfBoundsException nfe) {
                    throw new DukeException("Please enter a valid task number!");
                }
                break;
            }
            case "todo": {
                if (userInput.split(" ").length == 1) {
                    throw new DukeException("Todo description cannot be empty!");
                }
                Task newTask = new ToDo(userInput.substring(userInput.indexOf(" ") + 1, userInput.length()));
                tasks.add(newTask);
                prettyPrint("this task, I've added: \n\t\t" + newTask + "\n\t" + "you have " + tasks.size() + " tasks left");
                break;
            }
            case "deadline": {
                if (userInput.split(" ").length == 1) {
                    throw new DukeException("Deadline description cannot be empty!");
                }
                int separatorIndex = userInput.indexOf("/by");
                if (separatorIndex == -1) {
                    throw new DukeException("deadline requires a /by date!");
                }
                String description = userInput.substring(9, separatorIndex);
                String by = userInput.substring(separatorIndex + 4, userInput.length());

                Task newTask = new Deadline(description, by);
                tasks.add(newTask);
                prettyPrint("this task, I've added: \n\t\t" + newTask + "\n\t" + "you have " + tasks.size() + " tasks left");
                break;
            }
            case "event": {
                if (userInput.split(" ").length == 1) {
                    throw new DukeException("Event description cannot be empty!");
                }
                int separator = userInput.indexOf("/at");
                if (separator == -1) {
                    throw new DukeException("event requires a /at time");
                }
                String description = userInput.substring(6, separator);
                String at = userInput.substring(separator + 4, userInput.length());

                Task newTask = new Event(description, at);
                tasks.add(newTask);
                prettyPrint("this task, I've added: \n\t\t" + newTask + "\n\t" + "you have " + tasks.size() + " tasks left");
                break;
            }
            default:
                throw new DukeException("That cmd I don't know");
                
        }
    }

    private static void prettyPrint(String s) {
        String divider = "____________________________________________________________\n";
        System.out.println(divider + "\t" + s + "\n" + divider);
    }
}
