import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        taskList = new TaskList();
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            try {
                Instruction instruction = new Instruction(userInput);
                instruction.run();
                if (instruction.instructionType == Instruction.InstructionType.EXIT) {
                    break;
                }
            } catch (DukeException exception) {
                System.out.println(exception);
            }
        }
    }

    private static TaskList taskList;

    private static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }

        @Override
        public String toString() {
            return super.getMessage();
        }
    }

    private static class Instruction {
        private enum InstructionType {
            NONE,
            UNKNOWN,
            EXIT,
            ADD_TODO,
            ADD_EVENT,
            ADD_DEADLINE,
            PRINT_LIST,
            MARK_ITEM,
            UNMARK_ITEM,
            DELETE_ITEM
        }

        private InstructionType instructionType;
        private String[] instructionArgs;

        public Instruction(String instruction) {
            instructionArgs = instruction.trim().split("\\s+");
            if (instructionArgs.length == 0 || instructionArgs[0].isBlank()) {
                instructionType = InstructionType.NONE;
                return;
            }

            switch (instructionArgs[0]) {
                case "bye":
                    instructionType = InstructionType.EXIT;
                    break;

                case "list":
                    instructionType = InstructionType.PRINT_LIST;
                    break;

                case "mark":
                    instructionType = InstructionType.MARK_ITEM;
                    break;

                case "unmark":
                    instructionType = InstructionType.UNMARK_ITEM;
                    break;

                case "todo":
                    instructionType = InstructionType.ADD_TODO;
                    break;

                case "event":
                    instructionType = InstructionType.ADD_EVENT;
                    break;

                case "deadline":
                    instructionType = InstructionType.ADD_DEADLINE;
                    break;

                case "delete":
                    instructionType = InstructionType.DELETE_ITEM;
                    break;

                default:
                    instructionType = InstructionType.UNKNOWN;
                    break;
            }
        }

        public void run() throws DukeException {
            switch (instructionType) {
                case NONE:
                    // do nothing
                    break;

                case UNKNOWN:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :(");

                case EXIT:
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;

                case ADD_TODO:
                    taskList.addItem(new ToDo(instructionArgs));
                    break;

                case ADD_DEADLINE:
                    taskList.addItem(new Deadline(instructionArgs));
                    break;

                case ADD_EVENT:
                    taskList.addItem(new Event(instructionArgs));
                    break;

                case PRINT_LIST:
                    taskList.printList();
                    break;

                case MARK_ITEM:
                    try {
                        taskList.markItem(Integer.parseInt(instructionArgs[1]) - 1);
                    } catch (IndexOutOfBoundsException exception) {
                        throw new DukeException("☹ OOPS!!! No task index is specified :(");
                    } catch (NumberFormatException exception) {
                        throw new DukeException("☹ OOPS!!! You didn't give a valid index :(");
                    }
                    break;

                case UNMARK_ITEM:
                    try {
                        taskList.unmarkItem(Integer.parseInt(instructionArgs[1]) - 1);
                    } catch (IndexOutOfBoundsException exception) {
                        throw new DukeException("☹ OOPS!!! No task index is specified :(");
                    } catch (NumberFormatException exception) {
                        throw new DukeException("☹ OOPS!!! You didn't give a valid index :(");
                    }
                    break;

                case DELETE_ITEM:
                    try {
                        taskList.deleteItem(Integer.parseInt(instructionArgs[1]) - 1);
                    } catch (IndexOutOfBoundsException exception) {
                        throw new DukeException("☹ OOPS!!! No task index is specified :(");
                    } catch (NumberFormatException exception) {
                        throw new DukeException("☹ OOPS!!! You didn't give a valid index :(");
                    }
                    break;

                default:
                    // do nothing
            }
        }
    }

    private static abstract class Task {
        private String taskType;
        protected String description;
        protected boolean isDone;

        public Task(String taskType, String[] args) throws DukeException {
            this.taskType = taskType;
            this.description = Arrays.stream(args).skip(1).reduce("", (x, y) -> x + " " + y);
            this.isDone = false;
            if (this.description.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a " + this.taskType + " cannot be empty.");
            }
        }

        private char getStatusIcon() {
            return (isDone ? 'X' : ' '); // mark done task with X
        }

        private char getTaskTypeIcon() {
            return taskType.toUpperCase().charAt(0);
        }

        public void setDone(boolean isDone) {
            this.isDone = isDone;
        }

        @Override
        public String toString() {
            return "[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "]" + this.description;
        }
    }

    private static class ToDo extends Task {
        public ToDo(String[] args) throws DukeException {
            super("todo", args);
        }
    }

    private static class Deadline extends Task {
        private String by;

        public Deadline(String[] args) throws DukeException {
            super("deadline", Arrays.stream(args).takeWhile(x -> !x.contains("/")).toArray(String[]::new));
            String[] curArgs = Arrays.stream(args).dropWhile(x -> !x.contains("/")).toArray(String[]::new);
            if (curArgs.length == 0 || !curArgs[0].equals("/by")) {
                throw new DukeException("☹ OOPS!!! There is no /by argument for deadline :(");
            }
            this.by = Arrays.stream(curArgs).skip(1).reduce("", (x, y) -> x + " " + y);
            if (this.by.isEmpty()) {
                throw new DukeException("☹ OOPS!!! No time is specified for the deadline :(");
            }
        }

        @Override
        public String toString() {
            return super.toString() + " (by:" + this.by + ")";
        }
    }

    private static class Event extends Task {
        private String at;

        public Event(String[] args) throws DukeException {
            super("event", Arrays.stream(args).takeWhile(x -> !x.contains("/")).toArray(String[]::new));
            String[] curArgs = Arrays.stream(args).dropWhile(x -> !x.contains("/")).toArray(String[]::new);
            if (curArgs.length == 0 || !curArgs[0].equals("/at")) {
                throw new DukeException("☹ OOPS!!! There is no /at argument for deadline :(");
            }
            this.at = Arrays.stream(curArgs).skip(1).reduce("", (x, y) -> x + " " + y);
            if (this.at.isEmpty()) {
                throw new DukeException("☹ OOPS!!! No time is specified for the event :(");
            }
        }

        @Override
        public String toString() {
            return super.toString() + " (at:" + this.at + ")";
        }
    }

    private static class TaskList {
        private ArrayList<Task> taskList;

        public TaskList() {
            this.taskList = new ArrayList<Task>();
        }

        public void addItem(Task item) {
            taskList.add(item);
            System.out.println("OK, I've added the following task:\n  " + item);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }

        public void markItem(int index) throws DukeException {
            try {
                taskList.get(index).setDone(true);
                System.out.println("Nice! I've marked this task as done:\n  " + taskList.get(index));
            } catch (IndexOutOfBoundsException exception) {
                throw new DukeException("☹ OOPS!!! No such task exists :(");
            }
        }

        public void unmarkItem(int index) throws DukeException {
            try {
                taskList.get(index).setDone(false);
                System.out.println("OK, I've marked this task as not done yet:\n  " + taskList.get(index));
            } catch (IndexOutOfBoundsException exception) {
                throw new DukeException("☹ OOPS!!! No such task exists :(");
            }
        }

        public void deleteItem(int index) throws DukeException {
            try {
                System.out.println("Noted. I've removed this task:\n  " + taskList.get(index));
                taskList.remove(index);
            } catch (IndexOutOfBoundsException exception) {
                throw new DukeException("☹ OOPS!!! No such task exists :(");
            }
        }

        public void printList() {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i += 1) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }
}
