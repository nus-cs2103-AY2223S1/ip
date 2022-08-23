import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String OUTPUT_FILE_FOLDER = "data";
    private static final String OUTPUT_FILE_NAME = "duke.txt";
    private static final String OUTPUT_FILE_PATH = OUTPUT_FILE_FOLDER + File.separator + OUTPUT_FILE_NAME;

    private enum COMMANDS {
        mark, unmark, todo, deadline, event, delete, bye, list
    }

    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("------------------------------");
        File file = new File(OUTPUT_FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();      // lists of tasks
        ArrayList<String> commands = new ArrayList<>(); // list of commands that would generate tasks; same length as tasks

        try {
            Scanner scanner = new Scanner(file);
            System.out.println("Reading previous tasks...");
            readLine(scanner, tasks, commands, false);
            readLine(new Scanner("list"), tasks, commands, true);
        } catch (FileNotFoundException e) {
            System.out.println("No previous tasks found, starting with empty task list!");
            System.out.println("------------------------------");
        } catch (IOException e) {
            System.out.print(e);
            return;
        }

        try {
            Scanner scanner = new Scanner(System.in);
            if (!Files.exists(Paths.get(OUTPUT_FILE_FOLDER))) {
                Files.createDirectories(Paths.get(OUTPUT_FILE_FOLDER));
            }
            readLine(scanner, tasks, commands, true);
        } catch (IOException e) {
            System.out.print(e);
            return;
        }
    }

    // supposedly should stop when reaches end of file, but blocks if using system.in
    public static void readLine(Scanner scanner, ArrayList<Task> tasks, ArrayList<String> commands, boolean isPrinting)
            throws IOException {
        while(scanner.hasNext()) {
            FileWriter fw = new FileWriter(OUTPUT_FILE_PATH);
            String command = scanner.nextLine();
            String[] commandArr = command.split(" ");
            COMMANDS commandWord = null;
            String toBePrinted = "";
            toBePrinted = toBePrinted.concat("------------------------------\n");

            try {
                commandWord = COMMANDS.valueOf(commandArr[0]);
            } catch (IllegalArgumentException e) {
                toBePrinted = toBePrinted.concat("🙁 OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }

            switch(commandWord) {
            case mark:
                int markI;
                try {
                    markI = Integer.parseInt(commandArr[1]);
                } catch (NumberFormatException e) {     // if second word not integer
                    toBePrinted = toBePrinted.concat("🙁 OOPS!!! Provide a number to mark a task.\n");
                    break;
                }
                if (markI >= 1 && markI <= tasks.size()) {   // ensure i given is within range
                    tasks.get(markI - 1).mark();
                    commands.set(markI - 1, commands.get(markI - 1).strip().concat(" /done"));
                } else {
                    toBePrinted = toBePrinted.concat(
                        String.format(
                            "🙁 OOPS!!! Provide a valid number (from 1 to %d) to mark a task.\n", tasks.size()
                        )
                    );
                }
                break;
            case unmark:
                int unmarkI;
                try {
                    unmarkI = Integer.parseInt(commandArr[1]);
                } catch (NumberFormatException e) {     // if second word not integer
                    toBePrinted = toBePrinted.concat("🙁 OOPS!!! Provide a number to unmark a task.\n");
                    break;
                }
                if (unmarkI >= 1 && unmarkI <= tasks.size()) {   // ensure i given is within range
                    tasks.get(unmarkI - 1).unmark();
                    commands.set(unmarkI - 1, commands.get(unmarkI - 1).replace("/done", "").strip());
                } else {
                    toBePrinted = toBePrinted.concat(
                        String.format(
                            "🙁 OOPS!!! Provide a valid number (from 1 to %d) to unmark a task.\n", tasks.size()
                        )
                    );
                }
                break;
            case todo:
                boolean todoIsDone = command.contains("/done");
                if (todoIsDone) {
                    command = command.replace("/done", "");
                }
                String todoText = command.replaceFirst("todo", "").strip();
                if (todoText.isEmpty()) {
                    toBePrinted = toBePrinted.concat("🙁 OOPS!!! The description of a todo cannot be empty.\n");
                } else {
                    tasks.add(new Todo(todoIsDone, todoText, isPrinting));
                    commands.add(command);
                    toBePrinted = toBePrinted.concat(
                        String.format(
                            "Now you have %d tasks in the list.\n", tasks.size()
                        )
                    );
                }
                break;
            case deadline:
                boolean deadlineIsDone = command.contains("/done");
                if (deadlineIsDone) {
                    command = command.replace("/done", "");
                }
                String[] c1 = command.split("/by");
                String deadlineText = c1[0].replaceFirst("deadline", "").strip();
                String deadlineTime = c1.length > 1 ? c1[1].strip() : "";
                if (deadlineText.isEmpty()) {
                    toBePrinted = toBePrinted.concat("🙁 OOPS!!! The description of a deadline cannot be empty.\n");
                } else if (deadlineTime.isEmpty()) {
                    toBePrinted = toBePrinted.concat("🙁 OOPS!!! Provide a time for the deadline.\n");
                } else {
                    try {
                        tasks.add(new Deadline(deadlineIsDone, deadlineText, LocalDateTime.parse(deadlineTime, DateTimeFormatter.ofPattern("dd/MM/yy HHmm")), isPrinting));
                    } catch (DateTimeParseException e) {
                        System.out.println("🙁 OOPS!!! Provide a valid time (dd/MM/yy HHmm) for the deadline.");
                        break;
                    }
                    commands.add(command);
                    toBePrinted = toBePrinted.concat(
                            String.format(
                                    "Now you have %d tasks in the list.\n", tasks.size()
                            )
                    );
                }
                break;
            case event:
                boolean eventIsDone = command.contains("/done");
                if (eventIsDone) {
                    command = command.replace("/done", "");
                }
                String[] c2 = command.split("/at");
                String eventText = c2[0].replaceFirst("event", "").strip();
                String eventTime = c2.length > 1 ? c2[1].strip() : "";
                if (eventText.isEmpty()) {
                    toBePrinted = toBePrinted.concat("🙁 OOPS!!! The description of an event cannot be empty.\n");
                } else if (eventTime.isEmpty()) {
                    toBePrinted = toBePrinted.concat("🙁 OOPS!!! Provide a time for the event.\n");
                } else {
                    try {
                        tasks.add(new Event(eventIsDone, eventText, LocalDateTime.parse(eventTime, DateTimeFormatter.ofPattern("dd/MM/yy HHmm")), isPrinting));
                    } catch (DateTimeParseException e) {
                        System.out.println("🙁 OOPS!!! Provide a valid time (dd/MM/yy HHmm) for the event.");
                        break;
                    }
                    commands.add(command);
                    toBePrinted = toBePrinted.concat(
                            String.format(
                                    "Now you have %d tasks in the list.\n", tasks.size()
                            )
                    );
                }
                break;
            case delete:
                int deleteI;
                try {
                    deleteI = Integer.parseInt(commandArr[1]);
                } catch (NumberFormatException e) {     // if second word not integer
                    toBePrinted = toBePrinted.concat("🙁 OOPS!!! Provide a number to delete a task.\n");
                    break;
                }
                if (deleteI >= 1 && deleteI <= tasks.size()) {   // ensure i given is within range
                    String taskToString = tasks.get(deleteI - 1).toString();
                    tasks.remove(deleteI - 1);
                    commands.remove(deleteI - 1);
                    toBePrinted = toBePrinted.concat(
                            String.format(
                                "Noted. I've removed this task:\n  %s\n", taskToString
                            )
                    );
                } else {
                    toBePrinted = toBePrinted.concat(
                        String.format(
                            "🙁 OOPS!!! Provide a valid number (from 1 to %d) to delete a task.\n", tasks.size()
                        )
                    );
                }
                break;
            case bye:
                toBePrinted = toBePrinted.concat("Bye. Hope to see you again soon!\n");
                break;
            case list:
                if (tasks.isEmpty()) {
                    toBePrinted = toBePrinted.concat("🙁 OOPS!!! There are no tasks in your list yet.\n");
                } else {
                    toBePrinted = toBePrinted.concat("Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        toBePrinted = toBePrinted.concat(
                                String.format(
                                        "%d. %s\n", i + 1, tasks.get(i).toString()
                                )
                        );
                    }
                }
                break;
            default:
                break;
            }
            toBePrinted = toBePrinted.concat("------------------------------\n");
            if (isPrinting) {
                System.out.print(toBePrinted);
            }

            fw.write(commands.stream().reduce((s, prev) -> String.format("%s\n%s", s, prev)).orElse(""));
            fw.close();
        }
    }
}
