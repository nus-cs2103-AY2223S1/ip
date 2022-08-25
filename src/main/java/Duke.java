import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> storage = new ArrayList<>();
    private static SaveFile save = new SaveFile();

    /**
     * Lists all the tasks currently being stored.
     */
    private static void list() {

        System.out.println("____________________________________________________________ \n"
                + "Here are the tasks in your list:");

        int index = 1;
        Iterator<Task> iter = storage.iterator();
        while (iter.hasNext()) {
            System.out.printf("%d. ", index);
            System.out.println(iter.next().toString());
            index++;
        }

        System.out.println("____________________________________________________________");
    }

    /**
     *  Removes the specified Task from storage if it exists.
     *
     *  If the specified Task does not exist, a statement telling the user that
     *  the specified does not exist is printed.
     *
     * @param index The index of the Task to be removed from storage.
     */
    private static void delete(int index) {
        try {
            Task toRemove = storage.get(index);
            storage.remove(index);

            save.reload(storage);

            System.out.println(
                    "____________________________________________________________ \n"
                            + "Noted. I've removed this task: \n"
                            + toRemove.toString() + "\n"
                            + "Now you have " + storage.size() + " tasks in the list. \n"
                            + "____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "____________________________________________________________ \n"
                            + "☹ OOPS!!! That task doesn't exist. \n"
                            + "____________________________________________________________");
        }

    }

    public static void main(String[] args) {
        save.initialise(storage);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Duke \n" + "What can I do for you?");
        String command = scan.nextLine();

        while (!command.equals("bye")) {
            // Create and store Tasks
            if (!command.equals("list") && !(command.startsWith("mark") || command.startsWith("unmark"))
                    && !command.startsWith("delete") && !command.equals("reset")) {
                if (command.startsWith("todo")) {
                    try {
                        if (command.length() <= 5) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }

                        Task newTask = new Todo(command.substring(5));
                        storage.add(newTask);
                        save.addTask(newTask.toStore());
                    } catch (DukeException e) {
                        System.out.println(
                                "____________________________________________________________ \n"
                                        + "☹ OOPS!!! The description of a todo cannot be empty. \n"
                                        + "____________________________________________________________");

                        command = scan.nextLine();
                        continue;
                    }
                } else if (command.startsWith("deadline")) {
                    try {
                        if (command.length() <= 9) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }

                        String[] temp = command.substring(9).split("/by ");

                        Task newTask = new Deadline(temp[0], temp[1]);
                        storage.add(newTask);
                        save.addTask(newTask.toStore());
                    } catch (IndexOutOfBoundsException | DukeException e) {
                        System.out.println(
                                "____________________________________________________________ \n"
                                        + "☹ OOPS!!! The description of a deadline cannot be empty. \n"
                                        + "____________________________________________________________");

                        command = scan.nextLine();
                        continue;
                    } catch (DateTimeParseException e) {
                        System.out.println(
                                "____________________________________________________________ \n"
                                        + "☹ OOPS!!! The deadline must be in yyyy-MM-dd hh:mm AM/PM format. \n"
                                        + "____________________________________________________________");
                        command = scan.nextLine();
                        continue;
                    }
                } else if (command.startsWith("event")) {
                    try {
                        if (command.length() <= 6) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }

                        String[] temp = command.substring(6).split("/at ");

                        Task newTask = new Event(temp[0], temp[1]);
                        storage.add(newTask);
                        save.addTask(newTask.toStore());
                    } catch (IndexOutOfBoundsException | DukeException e) {
                        System.out.println(
                                "____________________________________________________________ \n"
                                        + "☹ OOPS!!! The description of an event cannot be empty. \n"
                                        + "____________________________________________________________");

                        command = scan.nextLine();
                        continue;
                    }
                } else {
                    System.out.println(
                            "____________________________________________________________ \n"
                                    + "☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n"
                                    + "____________________________________________________________");

                    command = scan.nextLine();
                    continue;
                }

            }

            // Commands
            if (command.equals("list")) {
                list();
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                int index = Character.getNumericValue(command.charAt(command.length() - 1));

                try {
                    storage.get(index - 1).toggleDone(command);
                    save.reload(storage);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(
                            "____________________________________________________________ \n"
                                    + "☹ OOPS!!! That task doesn't exist. \n"
                                    + "____________________________________________________________");
                }
            } else if (command.startsWith("delete")) {
                int index = Character.getNumericValue(command.charAt(command.length() - 1));
                delete(index - 1);
            } else if (command.equals("reset")) {
                save.reset(storage);
            } else {
                System.out.println(
                        "____________________________________________________________ \n"
                                + "Got it. I've added this task: \n"
                                + storage.get(storage.size() - 1).toString() + "\n"
                                + "Now you have " + storage.size() + " tasks in the list. \n"
                                + "____________________________________________________________");
            }

            command = scan.nextLine();
        }

        System.out.println(
                "____________________________________________________________ \n"
                + "Bye. Hope to see you again soon! \n"
                + "____________________________________________________________");
    }
}
