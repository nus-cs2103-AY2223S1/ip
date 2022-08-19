public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private TaskList taskList;

    public Duke() {
        try {
            taskList = new TaskList();
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("Hello from\n" + Duke.LOGO);
        System.out.println("What can I do for you?\n");
    }

    public boolean runInstruction(Instruction instruction) {
        try {
            InstructionType instructionType = instruction.getInstructionType();
            String[] instructionArgs = instruction.getInstructionArgs();
            switch (instructionType) {
                case NONE:
                    // empty line
                    // do nothing
                    break;

                case UNKNOWN:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :(");

                case EXIT:
                    System.out.println("Bye. Hope to see you again soon!\n");
                    return true;

                case ADD_TODO:
                    taskList.addItem(new ToDo(false, instructionArgs));
                    break;

                case ADD_DEADLINE:
                    taskList.addItem(new Deadline(false, instructionArgs));
                    break;

                case ADD_EVENT:
                    taskList.addItem(new Event(false, instructionArgs));
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
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }
}
