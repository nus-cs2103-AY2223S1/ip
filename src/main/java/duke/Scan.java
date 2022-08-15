package duke;

import java.util.Scanner;

public class Scan {
    private Scanner sc;
    private TaskList taskList;

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
        String nextCommand = sc.nextLine();
        while(!nextCommand.equals("bye")){
            if(nextCommand.equals("list")){
                taskList.displayAllTask();
            } else if(nextCommand.startsWith("mark")){
                char index = nextCommand.charAt(nextCommand.length() - 1);
                taskList.setTaskAsDone(Character.getNumericValue(index));
            } else if(nextCommand.startsWith("unmark")){
                char index = nextCommand.charAt(nextCommand.length() - 1);
                taskList.setTaskAsUndone(Character.getNumericValue(index));
            }
            else {
                this.taskList.addTask(nextCommand);
            }

            nextCommand = sc.nextLine();
        }
            dukePrintLn(BYE);
    }

    public static void dukePrintLn(String str){
        System.out.println("-----");
        System.out.println(str);
        System.out.println("-----");
    }
}
