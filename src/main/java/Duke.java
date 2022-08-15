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
            Instruction instruction = new Instruction(userInput);
            instruction.run();
            if (instruction.instructionType == Instruction.InstructionType.EXIT) {
                break;
            }
        }
    }

    private static TaskList taskList;

    private static class Instruction {
        private enum InstructionType {
            NONE,
            EXIT,
            ADD_TO_LIST,
            PRINT_LIST,
            MARK_ITEM,
            UNMARK_ITEM
        }

        private InstructionType instructionType;
        private String[] instructionArgs;

        public Instruction(String instruction) {
            instructionArgs = instruction.trim().split("\\s+");

            if (instructionArgs.length == 0) {
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

                default:
                    instructionType = InstructionType.ADD_TO_LIST;
                    break;
            }
        }

        public void run() {
            switch (instructionType) {
                case NONE:
                    // do nothing
                    break;

                case EXIT:
                    System.out.println("Bye. Hope to see you again soon!\n");
                    return;

                case ADD_TO_LIST:
                    taskList.addItem(new Task(this.instructionArgs));
                    break;

                case PRINT_LIST:
                    taskList.printList();
                    break;

                case MARK_ITEM:
                    taskList.markItem(Integer.parseInt(instructionArgs[1]) - 1);
                    break;

                case UNMARK_ITEM:
                    taskList.unmarkItem(Integer.parseInt(instructionArgs[1]) - 1);
                    break;

                default:
                    // do nothing

            }
        }
    }

    private static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String[] description) {
            this.description = Arrays.stream(description).reduce("", (x, y) -> x + " " + y);
            this.isDone = false;
        }

        private String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void setDone(boolean isDone) {
            this.isDone = isDone;
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "]" + " " + description;
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
        }

        public void markItem(int index) {
            taskList.get(index).setDone(true);
            System.out.println("Nice! I've marked this task as done:\n  " + taskList.get(index));
        }

        public void unmarkItem(int index) {
            taskList.get(index).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:\n  " + taskList.get(index));
        }
        
        public void printList() {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i += 1) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }
}
