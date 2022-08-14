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
    private enum Command {
        BYE, LIST, MARK, TODO, DEADLINE, EVENT, DELETE;

        public static boolean contains(String s) {
            for (Command c : Command.values()) {
                if (c.name().equals(s)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Tako.\nWhat do you want?");
        List<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine().trim();
            String[] splitInput = input.split(" ", 2);
            String stringCommand = splitInput[0].toUpperCase();
            Command command;
            try {
                if (Command.contains(stringCommand)) {
                    command = Command.valueOf(stringCommand);
                } else {
                    throw new InvalidInputException();
                }

                switch (command) {
                case BYE:
                    System.out.println("Bye, until next time...");
                    sc.close();
                    return;
                case LIST:
                    if (splitInput.length == 1) {
                        for (int i = 0; i < tasks.size(); i++) {
                            Task task = tasks.get(i);
                            System.out.printf("%d.%s\n", i + 1, task);
                        }
                    } else {
                        throw new InvalidInputException();
                    }
                    break;
                case MARK:
                    try {
                        if (splitInput.length == 1) {
                            throw new EmptyDescriptionException();
                        } else if (splitInput.length == 2) {
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
                case TODO:
                    if (splitInput.length == 1) {
                        throw new EmptyDescriptionException("todo");
                    }
                    Todo todo = new Todo(splitInput[1]);
                    tasks.add(todo);
                    System.out.println("added: " + todo);
                    System.out.println("Total tasks: " + tasks.size());
                    break;
                case DEADLINE:
                    if (splitInput.length == 1) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    String[] splitDeadline = splitInput[1].split(" /by ", 2);
                    if (splitDeadline.length == 2) {
                        Deadline deadline = new Deadline(splitDeadline[0], splitDeadline[1]);
                        tasks.add(deadline);
                        System.out.println("added: " + deadline);
                        System.out.println("Total tasks: " + tasks.size());
                    } else {
                        throw new EmptyDescriptionException("deadline's date/time");
                    }
                    break;
                case EVENT:
                    if (splitInput.length == 1) {
                        throw new EmptyDescriptionException("event");
                    }
                    String[] splitEvent = splitInput[1].split(" /at ", 2);
                    if (splitEvent.length == 2) {
                        Event event = new Event(splitEvent[0], splitEvent[1]);
                        tasks.add(event);
                        System.out.println("added: " + event);
                        System.out.println("Total tasks: " + tasks.size());
                    } else {
                        throw new EmptyDescriptionException("event's date/time");
                    }
                    break;
                case DELETE:
                    try {
                        if (splitInput.length == 1) {
                            throw new EmptyDescriptionException();
                        } else if (splitInput.length == 2) {
                            int taskNumber = Integer.parseInt(splitInput[1]) - 1;
                            if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
                                throw new InvalidRangeException();
                            }
                            Task task = tasks.remove(taskNumber);
                            System.out.println("deleted: " + task);
                            System.out.println("Total tasks: " + tasks.size());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("The task number to delete is invalid.");
                    } catch (InvalidRangeException e) {
                        System.out.println("The task number to delete does not exist.");
                    } catch (EmptyDescriptionException e) {
                        System.out.println("The task number to delete cannot be empty.");
                    }
                    break;
                default:
                    throw new InvalidInputException();
                }
            } catch (EmptyDescriptionException | InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}