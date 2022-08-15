import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> taskList = new ArrayList<>();
    private int messageState = 0;

    public enum TaskStatus {MARK, UNMARK}

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
                this.handleTask(command, TaskStatus.MARK);
                break;
            case "unmark":
                this.handleTask(command, TaskStatus.UNMARK);
                break;
            default:
                this.outputMessage(Messages.ADD_LIST[this.messageState][0]
                        + message
                        + Messages.ADD_LIST[this.messageState][0]);
                this.taskList.add(new Task(message));
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

    private boolean checkCommand(String[] command) {
        if (command.length < 2) {
            this.outputMessage(Messages.WRONG_COMMAND_FORMAT[this.messageState]);
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

    private boolean handleTask(String[] command, TaskStatus status) {
        if (!checkCommand(command)) {
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
