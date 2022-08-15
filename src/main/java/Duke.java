import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm Duke!\n" + logo + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        TasksList tasksList = new TasksList();

        while (sc.hasNext()) {
            String command = sc.nextLine();
            String[] words = command.split(" ", 2);
            if (command.equals("bye")) {
                message("Bye! Hope to see you again soon!");
                sc.close();
                break;
            }
            if (command.equals("list")) {
                tasksList.listTasks();
                continue;
            }
            if (words[0].equals("mark") || words[0].equals("unmark")) {
                if (words.length == 1) {
                    System.out.println("Please specify the task by its id.");
                    continue;
                }
                if (words[0].equals("mark")) {
                    tasksList.markTask(Integer.parseInt(words[1]));
                } else {
                    tasksList.unmarkTask(Integer.parseInt(words[1]));
                }
                continue;
            }
            if (words[0].equals("todo")) {
                if (words.length == 1) {
                    System.out.println("Please specify what task you wish to do.");
                    continue;
                }
                tasksList.addTodo(words[1]);
                continue;
            }
            if (words[0].equals("deadline")) {
                if (words.length == 1) {
                    System.out.println("Please specify what deadline task you wish to do.");
                    continue;
                }
                String[] deadline = words[1].split(" /by ", 2);
                if (deadline.length == 1) {
                    System.out.println("Please specify the date/time of this deadline.");
                    continue;
                }
                tasksList.addDeadline(deadline[0], deadline[1]);
                continue;
            }
            if (words[0].equals("event")) {
                if (words.length == 1) {
                    System.out.println("Please specify what event you wish to do.");
                    continue;
                }
                String[] event = words[1].split(" /at ", 2);
                if (event.length == 1) {
                    System.out.println("Please specify the date/time of this event.");
                    continue;
                }
                tasksList.addEvent(event[0], event[1]);
                continue;
            }
            System.out.println("Unknown command. Please try again.");
        }
    }

    /**
     * Prints the message from the bot.
     *
     * @param text the message for the bot to reply with.
     */
    private static void message(String text) {
        System.out.println("Duke: " + text);
    }
}
