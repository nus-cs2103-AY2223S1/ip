import java.util.*;

public class Duke {
    private static final String indentation = "    ";
    private static final String horizontalLine = "____________________________________________________________";
    private static List<String> list = new ArrayList<>();
    private static int listSize = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        commandHandler();
    }

    private static void encapsulateMessage(String message){
        String[] messages = message.split("\n");
        drawLine();
        for (String msg : messages){
            System.out.println(indentation + " " + msg);
        }
        drawLine();
    }

    private static void drawLine(){
        System.out.println(indentation + horizontalLine + "\n");
    }

    private static void greet(){
        String greeting = "Hello! I'm Duke \n" 
                        + "What can I do for you? \n" ;
        
        encapsulateMessage(greeting);
    }

    private static void echo(String command){
        encapsulateMessage(command);
    }

    private static void exit(){
        String bye = "Bye. Hope to see you again soon!";
        encapsulateMessage(bye);
    }

    private static void addToList(String item){
        listSize++;
        list.add(String.valueOf(listSize) + ". " + item);
    }

    private static void printList(){
        String itemString = "";
        for (String item : list) {
            itemString += item + "\n";
        }
        encapsulateMessage(itemString);
    }


    /*
     * The main Command Handling function for the bot.
     * It first greets the user, then takes in commands and keeps echoing them
     * Until the user inputs bye.
     */
    private static void commandHandler() {
        greet();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        
        while (!command.toLowerCase().equals("bye")) {
            if (command.equals("list")){
                printList();

            } else {
                addToList(command);
                echo("added: " + command);
            }
            // echo(command);

            command = sc.nextLine();

        }

        // Loop has been exited, meaning bye has been inputted
        exit();
        sc.close();
    }
}
