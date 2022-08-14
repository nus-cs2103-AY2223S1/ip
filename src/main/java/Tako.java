import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A chatbot named Tako that
 * supports various tasks.
 *
 * @author Alvin Tan Fu Long
 */
public class Tako {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Tako.\nWhat do you want?");
        final String COMMAND_BYE = "bye";
        final String COMMAND_LIST = "list";
        final String COMMAND_MARK = "mark";
        final String COMMAND_TODO = "todo";
        final String COMMAND_DEADLINE = "deadline";
        final String COMMAND_EVENT = "event";
        List<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];

            if (command.equals(COMMAND_BYE)) {
                System.out.println("Bye, until next time...");
                break;
            }

            try {
                switch (command) {
                case COMMAND_LIST:
                    if (input.trim().equals(COMMAND_LIST)) {
                        for (int i = 0; i < tasks.size(); i++) {
                            Task task = tasks.get(i);
                            System.out.printf("%d.%s\n", i + 1, task);
                        }
                    } else {
                        throw new InvalidInputException();
                    }
                    break;
                case COMMAND_MARK:
                    try {
                        if (input.trim().equals(COMMAND_MARK)) {
                            throw new EmptyDescriptionException(COMMAND_MARK);
                        }
                        if (splitInput.length == 2) {
                            int taskNumber = Integer.parseInt(splitInput[1]) - 1;
                            if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
                                throw new InvalidRangeException();
                            }
                            Task task = tasks.get(taskNumber);
                            task.markAsDone();
                            System.out.println("marked: " + task);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("The task number to mark is invalid.");
                    } catch (InvalidRangeException e) {
                        System.out.println("The task number to mark does not exist.");
                    } catch (EmptyDescriptionException e) {
                        System.out.println("The task number to mark cannot be empty.");
                    }
                    break;
                case COMMAND_TODO:
                    if (input.trim().equals(COMMAND_TODO)) {
                        throw new EmptyDescriptionException(COMMAND_TODO);
                    }
                    Todo todo = new Todo(splitInput[1]);
                    tasks.add(todo);
                    System.out.println("added: " + todo);
                    System.out.println("Total tasks: " + tasks.size());
                    break;
                case COMMAND_DEADLINE:
                    if (input.trim().equals(COMMAND_DEADLINE)) {
                        throw new EmptyDescriptionException(COMMAND_DEADLINE);
                    }
                    String[] splitDeadline = splitInput[1].trim().split(" /by ", 2);
                    if (splitDeadline.length == 2) {
                        Deadline deadline = new Deadline(splitDeadline[0], splitDeadline[1]);
                        tasks.add(deadline);
                        System.out.println("added: " + deadline);
                        System.out.println("Total tasks: " + tasks.size());
                    } else {
                        throw new EmptyDescriptionException("deadline's date/time");
                    }
                    break;
                case COMMAND_EVENT:
                    if (input.trim().equals(COMMAND_EVENT)) {
                        throw new EmptyDescriptionException(COMMAND_EVENT);
                    }
                    String[] splitEvent = splitInput[1].trim().split(" /at ", 2);
                    if (splitEvent.length == 2) {
                        Event event = new Event(splitEvent[0], splitEvent[1]);
                        tasks.add(event);
                        System.out.println("added: " + event);
                        System.out.println("Total tasks: " + tasks.size());
                    } else {
                        throw new EmptyDescriptionException("event's date/time");
                    }
                    break;
                default:
                    throw new InvalidInputException();
                }
            } catch (EmptyDescriptionException | InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
}