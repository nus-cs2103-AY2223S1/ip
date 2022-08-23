package duke;

import exceptions.UnknownCommandException;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    private final TaskList taskList;
    private final Storage storage;

    private static final String GREETING = "Hello! I'm Duke. What can I do for you?";
    private static final String BYE = "Thank you for using the bot. Have a nice day!";



    public Ui(TaskList taskList, Storage storage) {
        this.sc = new Scanner(System.in);
        this.taskList = taskList;
        this.storage = storage;
    }

    public  void Greet() {
        dukePrintLn(GREETING);
    }

    public  void readInput() {

        try {
            String nextCommand = sc.nextLine();
            while(!nextCommand.equals(CommandsEnum.bye.toString())){
                executeCommand(nextCommand);
                nextCommand = sc.nextLine();
            }
            dukePrintLn(BYE);
        }
        catch (UnknownCommandException e){
            System.out.println(e.getMessage());
            readInput();
        }




    }

    public void executeCommand(String nextCommand) throws UnknownCommandException {
            if(nextCommand.equals(CommandsEnum.list.toString())){
                taskList.displayAllTask();
            } else if(nextCommand.startsWith(CommandsEnum.mark.toString())){
                int index = convertToInt(nextCommand);
                storage.toggleDone(index, true);
                taskList.setTaskAsDone(index);
            } else if(nextCommand.startsWith(CommandsEnum.unmark.toString())){
                int index = convertToInt(nextCommand);
                storage.toggleDone(index, false);
                taskList.setTaskAsUndone(index);
            }
            else if(nextCommand.contains(CommandsEnum.todo.toString()) ||
                    nextCommand.contains(CommandsEnum.deadline.toString()) ||
                    nextCommand.contains(CommandsEnum.event.toString())){
                taskList.addTask(nextCommand);
                storage.addLineToFile(nextCommand);

            } else if(nextCommand.startsWith(CommandsEnum.delete.toString())){
                int index = convertToInt(nextCommand);
                storage.deleteLine(index);
                taskList.deleteTask(index);
            }
            else {
                throw new UnknownCommandException();
            }


    }

    private int convertToInt(String str){
        return Character.getNumericValue((str.charAt(str.length() - 1)));
    }

    public static void dukePrintLn(String str){
        System.out.println("-----");
        System.out.println(str);
        System.out.println("-----");
    }

    public void run() {
        Greet();
        readInput();
    }
}
