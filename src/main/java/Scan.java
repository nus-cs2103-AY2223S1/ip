import exceptions.EmptyNameException;
import exceptions.UnknownCommandException;

import java.util.Scanner;

public class Scan {
    private final Scanner sc;
    private final TaskList taskList;

    private static final String GREETING = "Hello! I'm Duke. What can I do for you?";
    private static final String BYE = "Thank you for using the bot. Have a nice day!";



    public Scan() {
        this.sc = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    public  void Greet() {
        dukePrintLn(GREETING);
    }

    public  void readInput() {
        try {
            String nextCommand = sc.nextLine();
            while(!nextCommand.equals(CommandsEnum.bye.toString())){
                if(nextCommand.equals(CommandsEnum.list.toString())){
                    taskList.displayAllTask();
                } else if(nextCommand.startsWith(CommandsEnum.mark.toString())){
                    char index = nextCommand.charAt(nextCommand.length() - 1);
                    taskList.setTaskAsDone(Character.getNumericValue(index));
                } else if(nextCommand.startsWith(CommandsEnum.unmark.toString())){
                    char index = nextCommand.charAt(nextCommand.length() - 1);
                    taskList.setTaskAsUndone(Character.getNumericValue(index));
                }
                else if(nextCommand.contains("todo") ||
                        nextCommand.contains("deadline") ||
                        nextCommand.contains("event")){
                    this.taskList.addTask(nextCommand);
                } else if(nextCommand.startsWith(CommandsEnum.delete.toString())){
                    char index = nextCommand.charAt(nextCommand.length() - 1);
                    this.taskList.deleteTask(Character.getNumericValue(index));
                }
                else {
                    throw new UnknownCommandException();
                }

                nextCommand = sc.nextLine();
            }
            dukePrintLn(BYE);
        } catch (UnknownCommandException e){
            System.out.println(e.getMessage());
            readInput();
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
