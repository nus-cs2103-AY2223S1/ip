package duke;

import exceptions.UnknownCommandException;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Ui {
    private static final String GREETING = "Hello! I'm Duke. What can I do for you?";
    private static final String BYE = "Thank you for using the bot. Have a nice day!";
    private final Scanner sc;
    private final TaskList taskList;
    private final Storage storage;


    public Ui(TaskList taskList, Storage storage) {
        this.sc = new Scanner(System.in);
        this.taskList = taskList;
        this.storage = storage;
    }

    public static void dukePrintLn(String str) {
        System.out.println("-----");
        System.out.println(str);
        System.out.println("-----");
    }

    public void Greet() {
        dukePrintLn(GREETING);
    }

    public void readInput() {

        try {
            String nextCommand = sc.nextLine();
            while (!nextCommand.equals(CommandsEnum.bye.toString())) {
                executeCommand(nextCommand);
                nextCommand = sc.nextLine();
            }
            dukePrintLn(BYE);
        } catch (UnknownCommandException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            readInput();
        }
    }

    public void executeCommand(String nextCommand) throws UnknownCommandException {
        if (nextCommand.equals(CommandsEnum.list.toString())) {
            taskList.displayAllTask();
        } else if (nextCommand.startsWith(CommandsEnum.mark.toString())) {
            int index = convertToInt(nextCommand);
            storage.toggleDone(index, true);
            taskList.setTaskAsDone(index);
        } else if (nextCommand.startsWith(CommandsEnum.unmark.toString())) {
            int index = convertToInt(nextCommand);
            storage.toggleDone(index, false);
            taskList.setTaskAsUndone(index);
        } else if (nextCommand.contains(CommandsEnum.todo.toString()) || nextCommand.contains(CommandsEnum.deadline.toString()) || nextCommand.contains(CommandsEnum.event.toString())) {
            taskList.addTask(nextCommand);
            storage.addLineToFile(nextCommand);

        } else if (nextCommand.startsWith(CommandsEnum.delete.toString())) {
            int index = convertToInt(nextCommand);
            storage.deleteLine(index);
            taskList.deleteTask(index);
        } else if(nextCommand.startsWith(CommandsEnum.find.toString())){
            String name = Parser.getFindObject(nextCommand);
            taskList.findTask(name);
        }
        else {
            throw new UnknownCommandException();
        }


    }


    public String getResponse(String nextCommand) throws UnknownCommandException {
        if(nextCommand.equals(CommandsEnum.bye.toString())){
            return BYE;
        }
        else if (nextCommand.equals(CommandsEnum.list.toString())) {
            return taskList.displayAllTask();
        } else if (nextCommand.startsWith(CommandsEnum.mark.toString())) {
            int index = convertToInt(nextCommand);
            storage.toggleDone(index, true);
            return taskList.setTaskAsDone(index);
        } else if (nextCommand.startsWith(CommandsEnum.unmark.toString())) {
            int index = convertToInt(nextCommand);
            storage.toggleDone(index, false);
            return taskList.setTaskAsUndone(index);
        } else if (nextCommand.contains(CommandsEnum.todo.toString()) || nextCommand.contains(CommandsEnum.deadline.toString()) || nextCommand.contains(CommandsEnum.event.toString())) {
            storage.addLineToFile(nextCommand);
            return taskList.addTask(nextCommand);

        } else if (nextCommand.startsWith(CommandsEnum.delete.toString())) {
            int index = convertToInt(nextCommand);
            storage.deleteLine(index);
            return taskList.deleteTask(index);
        } else if (nextCommand.startsWith(CommandsEnum.find.toString())) {
            String name = Parser.getFindObject(nextCommand);
            return taskList.findTask(name);
        } else {
            return  new UnknownCommandException().getMessage();
        }

    }

    private int convertToInt(String str) {
        return Integer.parseInt(str.substring(str.length()-1));
    }

    public void run() {
        Greet();
        readInput();
    }
}
