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
                this.handleTaskStatus(command, TaskStatus.MARK);
                break;
            case "unmark":
                this.handleTaskStatus(command, TaskStatus.UNMARK);
                break;
            case "todo":
                this.handleTaskType(command, TaskType.TODO);
                break;
            case "deadline":
                this.handleTaskType(command, TaskType.DEADLINE);
                break;
            case "event":
                this.handleTaskType(command, TaskType.EVENT);
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

    private boolean checkCommandArgs(String[] command, int length) {
        if (command.length < length) {
            this.outputMessage(Messages.WRONG_COMMAND_FORMAT[this.messageState]);
            return false;
        }
        return true;
    }

    private boolean checkCommandInt(String[] command) {
        if (!checkCommandArgs(command, 2)) {
            return false;
        }
        try {
            int index = Integer.parseInt(command[1]);
            if (index > taskList.size() || index < 1) {
                this.outputMessage(Messages.INVALID_INDEX[this.messageState]);
                return false;
            }
        } catch (NumberFormatException e) {
            this.outputMessage(Messages.NOT_A_NUMBER[this.messageState]);
            return false;
        }
        return true;
    }

    private String[] splitTag(String message, String tag) {
        return message.split(tag, 2);
    }

    private boolean checkCommandTag(String[] command, String tag) {
        if (!checkCommandArgs(command, 2)) {
            return false;
        }
        String[] args = splitTag(command[1], tag);
        return checkCommandArgs(args, 2);
    }

    private boolean handleTaskStatus(String[] command, TaskStatus status) {
        if (!checkCommandInt(command)) {
            return false;
        }
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
        return true;
    }

    private boolean handleTaskType(String[] command, TaskType type) {
        Task task;
        if (type == TaskType.TODO) {
            if (!checkCommandArgs(command, 2)) {
                return false;
            }
            task = new ToDo(command[1]);
        } else if (type == TaskType.DEADLINE) {
            if (!checkCommandTag(command, DEADLINE_TAG)) {
                return false;
            } else {
                String[] args = splitTag(command[1], DEADLINE_TAG);
                task = new Deadline(args[0], args[1]);
            }
        } else {
            if (!checkCommandTag(command, EVENT_TAG)) {
                return false;
            } else {
                String[] args = splitTag(command[1], EVENT_TAG);
                task = new Event(args[0], args[1]);
            }
        }
        this.taskList.add(task);
        this.outputMessage(Messages.ADD_LIST[this.messageState]);
        this.outputMessage(task.toString());
        this.outputMessage(Messages.getListSizeMsg(taskList.size(), messageState));
        return true;
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
