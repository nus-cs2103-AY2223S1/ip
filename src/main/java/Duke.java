import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> taskList = new ArrayList<>();
    private int messageState = 0;

    public enum TaskStatus {MARK, UNMARK}
    public enum TaskType {TODO, DEADLINE, EVENT}

    private static String DEADLINE_TAG = " /by ";
    private static String EVENT_TAG = " /at ";

    private void outputMessage(String message) {
        String[] messageLines = message.split("\n");
        for (String line : messageLines) {
            System.out.println("Duke: " + line);
        }
    }

    private boolean parseMessage(String message) {
        String[] command = message.split(" ", 2);
        switch (command[0]) {
            case "bye":
                this.outputMessage(Messages.BYE[this.messageState]);
                return false;
            case "list":
                this.outputMessage(Messages.BEFORE_LIST[this.messageState]);
                this.printList();
                this.outputMessage(Messages.AFTER_LIST[this.messageState]);
                break;
            case "mark":
                try {
                    this.handleTaskStatus(command, TaskStatus.MARK);
                } catch (InvalidIndexException e) {
                    this.outputMessage(Messages.INVALID_INDEX[messageState]);
                } catch (MissingDescriptionException e) {
                    this.outputMessage(Messages.WRONG_COMMAND_FORMAT[messageState]);
                } catch (NumberFormatException e) {
                    this.outputMessage(Messages.NOT_A_NUMBER[messageState]);
                }
                break;
            case "unmark":
                try {
                    this.handleTaskStatus(command, TaskStatus.UNMARK);
                } catch (InvalidIndexException e) {
                    this.outputMessage(Messages.INVALID_INDEX[messageState]);
                } catch (MissingDescriptionException e) {
                    this.outputMessage(Messages.WRONG_COMMAND_FORMAT[messageState]);
                } catch (NumberFormatException e) {
                    this.outputMessage(Messages.NOT_A_NUMBER[messageState]);
                }
                break;
            case "todo":
                try {
                    this.handleTaskType(command, TaskType.TODO);
                } catch (MissingDescriptionException e) {
                    this.outputMessage(Messages.WRONG_COMMAND_FORMAT[messageState]);
                } catch (MissingArgumentException e) {
                    e.printStackTrace();
                }
                break;
            case "deadline":
                try {
                    this.handleTaskType(command, TaskType.DEADLINE);
                } catch (MissingDescriptionException e) {
                    this.outputMessage(Messages.WRONG_COMMAND_FORMAT[messageState]);
                } catch (MissingArgumentException e) {
                    this.outputMessage(Messages.MISSING_TIME[messageState]);
                }
                break;
            case "event":
                try {
                    this.handleTaskType(command, TaskType.EVENT);
                } catch (MissingDescriptionException e) {
                    this.outputMessage(Messages.WRONG_COMMAND_FORMAT[messageState]);
                } catch (MissingArgumentException e) {
                    this.outputMessage(Messages.MISSING_TIME[messageState]);
                }
                break;
            default:
                this.outputMessage(Messages.INVALID_COMMAND[this.messageState]);
        }
        return true;
    }

    private void greet() {
        this.outputMessage(Messages.GREETING[this.messageState]);
    }

    private void printList() {
        int index = 1;
        for (Task task : this.taskList) {
            this.outputMessage(index + ". " + task.toString());
            index++;
        }
    }

    private void checkCommandArgs(String[] command, int length) throws MissingDescriptionException{
        if (command.length < length) {
            throw new MissingDescriptionException("Missing Arguments");
        }
    }

    private void checkCommandInt(String[] command) throws NumberFormatException, InvalidIndexException, MissingDescriptionException {
        checkCommandArgs(command, 2);
        int index = Integer.parseInt(command[1]);
        if (index > taskList.size() || index < 1) {
            throw new InvalidIndexException("Index out of bounds.");
        }
    }

    private String[] splitTag(String message, String tag) {
        return message.split(tag, 2);
    }

    private void checkCommandTag(String[] command, String tag) throws MissingArgumentException, MissingDescriptionException {
        checkCommandArgs(command, 2);
        String[] args = splitTag(command[1], tag);
        try {
            checkCommandArgs(args, 2);
        } catch (MissingDescriptionException e) {
            throw new MissingArgumentException("Missing " + tag);
        }
    }

    private void handleTaskStatus(String[] command, TaskStatus status) throws InvalidIndexException, MissingDescriptionException {
        checkCommandInt(command);
        String message;
        String message2;
        if (status == TaskStatus.MARK) {
            message = Messages.MARK_DONE[this.messageState];
            message2 = Messages.PREV_DONE[this.messageState];
        } else {
            message = Messages.MARK_UNDONE[this.messageState];
            message2 = Messages.PREV_UNDONE[this.messageState];
        }
        this.outputMessage(message);
        Task task = taskList.get(Integer.parseInt(command[1]) - 1);
        boolean change = task.changeStatus(status);
        this.outputMessage(task.toString());
        if (!change) {
            this.outputMessage(message2);
        }
    }

    private void handleTaskType(String[] command, TaskType type) throws MissingDescriptionException, MissingArgumentException {
        Task task;
        if (type == TaskType.TODO) {
            checkCommandArgs(command, 2);
            task = new ToDo(command[1]);
        } else if (type == TaskType.DEADLINE) {
            checkCommandTag(command, DEADLINE_TAG);
            String[] args = splitTag(command[1], DEADLINE_TAG);
            task = new Deadline(args[0], args[1]);
        } else {
            checkCommandTag(command, EVENT_TAG);
            String[] args = splitTag(command[1], EVENT_TAG);
            task = new Event(args[0], args[1]);
        }
        this.taskList.add(task);
        this.outputMessage(Messages.ADD_LIST[this.messageState]);
        this.outputMessage(task.toString());
        this.outputMessage(Messages.getListSizeMsg(taskList.size(), messageState));
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Duke duke = new Duke();
        duke.greet();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("You: ");
        } while(duke.parseMessage(scanner.nextLine()));
    }
}
