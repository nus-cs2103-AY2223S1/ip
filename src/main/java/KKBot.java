import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * KKBot is a chatbot.
 * It currently functions like a to-do list.
 * It stores tasks and displays these stored tasks to users when asked.
 * It also allows users to set and check the completion of input tasks.
 */
public class KKBot {
    // create a class level array to store all user-input tasks
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // Welcome message and header
        String divider = "____________________________________________________________\n";
        String logo = " __   __   __   __  _____\n"
                    + "|  | /  / |  | /  /|  __  \\\n"
                    + "|  |/  /  |  |/  / | |__|  |\n"
                    + "|     /   |     /  |      /\n"
                    + "|     \\   |     \\  |  __  \\\n"
                    + "|  |\\  \\  |  |\\  \\ | |__|  |\n"
                    + "|__| \\__\\ |__| \\__\\|______/\n";
        String welcomeMessage = "Hello! I'm KKBot! \n"
                + "What can I do for you?\n";
        System.out.println(divider + logo + welcomeMessage + divider);

        // Add scanner in for user input (create scanner object)
        Scanner userText = new Scanner(System.in);
        // Chatbot function:
        while (true) {
            String input = userText.nextLine();
            System.out.println(divider);
            // if user input is bye, chatbot closes
            if (input.equals("bye") || input.equals("Bye") || input.equals("BYE")) {
                System.out.println("KKBot signing off. Goodbye!\n" + divider);
                break;
            // if user input is list, chatbot returns a list of stored tasks
            } else if (input.equals("list") || input.equals("List") || input.equals("LIST")) {
                // internal check if there are any tasks to do
                // if no stored tasks, inform accordingly
                if (tasks.isEmpty()) {
                    System.out.println("Good job! There's no tasks at hand!\n" + divider);
                    // if there are stored tasks, list them out
                    // include the index number and status icon of the task
                } else {
                    System.out.println("Here's your list of tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        String completionIcon = task.getStatusIcon();
                        String description = task.getDescription();
                        String index = String.valueOf(i + 1);
                        //if last task in list, add a divider after
                        if (i == tasks.size() - 1) {
                            System.out.println(index + ". " + completionIcon + " " + description + "\n" + divider);
                        } else {
                            System.out.println(index + ". " + completionIcon + " " + description + "\n");
                        }
                    }
                }
            } else if (input.startsWith("mark") || input.startsWith("Mark") || input.startsWith("MARK")) {
                String inputNumber = input.substring(5);
                try {
                    int taskNumber = Integer.parseInt(inputNumber);
                    Task task = tasks.get(taskNumber - 1);
                    if (task != null) {
                        if (!task.getCompletion()) {
                            task.setComplete();
                        } else {
                            System.out.println("Silly you, that task is already complete!");
                        }
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Woopsies you left out the task number. Please specify one!");
                } catch (IndexOutOfBoundsException ioobe) {
                    System.out.println("Hmm... that task doesn't exist. Please input another task number!");
                }
            } else if (input.startsWith("unmark") || input.startsWith("Unmark") || input.startsWith("UNMARK")) {
                String inputNumber = input.substring(7);
                try {
                    int taskNumber = Integer.parseInt(inputNumber);
                    Task task = tasks.get(taskNumber - 1);
                    if (task != null) {
                        if (task.getCompletion()) {
                            task.setIncomplete();
                        } else {
                            System.out.println("Erm... that task was never complete...");
                        }
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Woopsies you left out the task number. Please specify one!");
                } catch (IndexOutOfBoundsException ioobe) {
                    System.out.println("Hmm... that task doesn't exist. Please input another task number!");
                }
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println("added: " + input + "\n" + divider);
            }
        }
    }
}
