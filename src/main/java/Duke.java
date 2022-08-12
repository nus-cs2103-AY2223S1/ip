import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public abstract class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String mark() {
            this.isDone = true;
            return this.toString();
        }

        public String unmark() {
            this.isDone = false;
            return this.toString();
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", (this.isDone ? "X" : " "), this.description);
        }
    }

    public class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return String.format("[T]%s", super.toString());
        }
    }

    public class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return String.format("[D]%s (by: %s)", super.toString(), this.by);
        }
    }

    public class Event extends Task {
        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return String.format("[E]%s (at: %s)", super.toString(), this.at);
        }
    }

    public class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    private static final String LINE = "--------------------------------------------------";

    private void run() {
        LinkedList<Task> list = new LinkedList<>();
        int size = 0;
        boolean bye = false;

        Scanner sc = new Scanner(System.in);
        System.out.printf("%s%n%s%n%s%n%s%n", LINE, "Hello! I'm Cortana", "What can I do for you?", LINE);

        while (!bye) {
            try {
                String[] input = sc.nextLine().trim().split(" ", 2);
                System.out.println(LINE);

                Command c = Command.valueOf(input[0].toUpperCase());

                if (input.length == 1) {
                    switch (c) {
                        case BYE:
                            System.out.println("Bye. Hope to see you again soon!");
                            bye = true;
                            break;

                        case LIST:
                            if (size == 0) {
                                System.out.println("There are no tasks in your list.");
                                break;
                            }
                            System.out.printf("Here %s the task%s in your list:%n",
                                    size > 1 ? "are" : "is", size > 1 ? "s" : "");
                            for (int i = 1; i <= size; i++) {
                                System.out.printf("%d.%s%n", i, list.get(i - 1));
                            }
                            break;

                        case MARK:
                            throw new DukeException("The index to mark cannot be empty.");

                        case UNMARK:
                            throw new DukeException("The index to unmark cannot be empty.");

                        case TODO:
                            throw new DukeException("The description of a todo cannot be empty.");

                        case DEADLINE:
                            throw new DukeException("The description of a deadline cannot be empty.");

                        case EVENT:
                            throw new DukeException("The description of an event cannot be empty.");

                        case DELETE:
                            throw new DukeException("The index to delete cannot be empty.");

                        default:
                            break;
                    }
                } else {
                    switch (c) {
                        case MARK:
                            if (!input[1].matches("[0-9]+")) {
                                throw new DukeException("The index provided is not a positive integer.");
                            }

                            int markIdx = Integer.parseInt(input[1]);
                            if (markIdx == 0 || markIdx > size) {
                                throw new DukeException("The index provided is not within the list.");
                            }

                            System.out.printf("Nice! I've marked this task as done:%n%s%n",
                                    list.get(markIdx - 1).mark());
                            break;

                        case UNMARK:
                            if (!input[1].matches("[0-9]+")) {
                                throw new DukeException("The index provided is not a positive integer.");
                            }

                            int unmarkIdx = Integer.parseInt(input[1]);
                            if (unmarkIdx == 0 || unmarkIdx > size) {
                                throw new DukeException("The index provided is not within the list.");
                            }

                            System.out.printf("OK, I've marked this task as not done yet:%n%s%n",
                                    list.get(unmarkIdx - 1).unmark());
                            break;

                        case TODO:
                            Todo todoTemp = new Todo(input[1]);
                            list.add(todoTemp);
                            size++;
                            System.out.printf("Got it. I've added this task:%n%s%nNow you have %d task%s in the list.%n",
                                    todoTemp, size, size > 1 ? "s" : "");
                            break;

                        case DEADLINE:
                            String[] deadlineSplit = input[1].split(" /by ");
                            if (deadlineSplit.length == 1) {
                                throw new DukeException("The time of a deadline cannot be empty.");
                            }

                            Deadline deadlineTemp = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                            list.add(deadlineTemp);
                            size++;
                            System.out.printf("Got it. I've added this task:%n%s%nNow you have %d task%s in the list.%n",
                                    deadlineTemp, size, size > 1 ? "s" : "");
                            break;

                        case EVENT:
                            String[] eventSplit = input[1].split(" /at ");
                            if (eventSplit.length == 1) {
                                throw new DukeException("The time of an event cannot be empty.");
                            }

                            Event eventTemp = new Event(eventSplit[0], eventSplit[1]);
                            list.add(eventTemp);
                            size++;
                            System.out.printf("Got it. I've added this task:%n%s%nNow you have %d task%s in the list.%n",
                                    eventTemp, size, size > 1 ? "s" : "");
                            break;

                        case DELETE:
                            if (!input[1].matches("[0-9]+")) {
                                throw new DukeException("The index provided is not a positive integer.");
                            }

                            int deleteIdx = Integer.parseInt(input[1]);
                            if (deleteIdx == 0 || deleteIdx > size) {
                                throw new DukeException("The index provided is not within the list.");
                            }

                            Task deleteTemp = list.remove(Integer.parseInt(input[1]) - 1);
                            size--;
                            System.out.printf("Noted. I've removed this task:%n%s%nNow you have %d task%s in the list.%n",
                                    deleteTemp, size, size != 1 ? "s" : "");
                            break;

                        default:
                            throw new DukeException("Please re-enter the command only.");
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("I'm sorry, but I don't know what that means.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(LINE);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
