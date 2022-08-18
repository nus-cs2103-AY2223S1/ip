import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        TaskList tasks = new TaskList();

        while (userInput.hasNextLine()) {
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                break;
            }
            String[] inputParts = input.split(" ", 2);
            if (input.equals("list")) {
                tasks.listTasks();
            }
            else if (input.matches("mark +\\d+") || input.matches("unmark +\\d+")) {
                if (inputParts[0].equals("mark")) {
                    tasks.mark(Integer.parseInt(inputParts[1]));
                }
                if (inputParts[0].equals("unmark")) {
                    tasks.unmark(Integer.parseInt(inputParts[1]));
                }
            }
            else if (inputParts[0].equals("deadline")) {
                String[] deadlineParts = inputParts[1].split("/by", 2);
                Deadline deadlineTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                tasks.addTask(deadlineTask);
            }
            else if (inputParts[0].equals("event")){
                String[] eventParts = inputParts[1].split("/at", 2);
                Event newEvent = new Event(eventParts[0], eventParts[1]);
                tasks.addTask(newEvent);
            }
            else {
                tasks.addTask(new Todo(input));
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
