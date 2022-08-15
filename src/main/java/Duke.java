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
            try {
                if (command.equals("bye")) {
                    System.out.println("Duke: Bye! Hope to see you again soon!");
                    sc.close();
                    break;
                }
                if (command.equals("list")) {
                    tasksList.listTasks();
                    continue;
                }
                if (words[0].equals("mark")) {
                    tasksList.markTask(words);
                    continue;
                }
                if (words[0].equals("unmark")) {
                    tasksList.unmarkTask(words);
                    continue;
                }
                if (words[0].equals("todo")) {
                    tasksList.addTodo(words);
                    continue;
                }
                if (words[0].equals("deadline")) {
                    tasksList.addDeadline(words);
                    continue;
                }
                if (words[0].equals("event")) {
                    tasksList.addEvent(words);
                    continue;
                }
                if (words[0].equals("delete")) {
                    tasksList.deleteTask(words);
                    continue;
                }
                throw new DukeException("Unknown command. Please try again.");
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                System.out.println();
            }
        }
    }
}
