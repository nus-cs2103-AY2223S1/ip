import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final static String UNDERLINE = "_________________________________";
    private ArrayList<String> taskList = new ArrayList<>();
    boolean inProcess = true;
    
    private String greet(String res) {
        if (res.toLowerCase().equals("bye")) {
            inProcess = false;
            return "Bye. Hope to see you again soon!";
        } else if(res.toLowerCase().equals("list")){
            String log ="Tasks that you have:";
            for (int i = 0; i < taskList.size(); i++) {
                log += String.format("\n %d: %s", i + 1, this.taskList.get(i));
            }
            return log;
        } else {
            this.taskList.add(res);
            return String.format("added: %s",res);
        }
    }
    private void run() {
        Scanner sc= new Scanner(System.in);
        System.out.println(UNDERLINE);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println(UNDERLINE);
        while(inProcess) {
            String a= sc.nextLine();
            System.out.println(UNDERLINE + "\n" + greet(a) + "\n" + UNDERLINE);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke().run();
        }
    }

