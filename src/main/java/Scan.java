import exceptions.UnknownCommandException;

import java.util.Scanner;

public class Scan {
    private final Scanner sc;
    private final TaskList taskList;
    private final Storage storage;

    private static final String GREETING = "Hello! I'm Duke. What can I do for you?";
    private static final String BYE = "Thank you for using the bot. Have a nice day!";



    public Scan(TaskList taskList, Storage storage) {
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
                char index = nextCommand.charAt(nextCommand.length() - 1);
                taskList.setTaskAsDone(Character.getNumericValue(index));
            } else if(nextCommand.startsWith(CommandsEnum.unmark.toString())){
                char index = nextCommand.charAt(nextCommand.length() - 1);
                taskList.setTaskAsUndone(Character.getNumericValue(index));
            }
            else if(nextCommand.contains(CommandsEnum.todo.toString()) ||
                    nextCommand.contains(CommandsEnum.deadline.toString()) ||
                    nextCommand.contains(CommandsEnum.event.toString())){
                taskList.addTask(nextCommand);
                storage.addLineToFile(nextCommand);

            } else if(nextCommand.startsWith(CommandsEnum.delete.toString())){
                char index = nextCommand.charAt(nextCommand.length() - 1);
                taskList.deleteTask(Character.getNumericValue(index));
            }
            else {
                throw new UnknownCommandException();
            }


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
