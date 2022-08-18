import java.util.*;


public class Duke {
    private static final String indentation = "    ";
    private static final String horizontalLine = "____________________________________________________________";
    private static List<Task> list = new ArrayList<>();
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
        Task newTask = new Task(item, listSize);
        list.add(newTask);
        echo("added: " + newTask.toString());
    }

    private static void markAsDone(int number){
        Task item = list.get(number - 1);
        item.mark();
        String itemMessage = "Nice! I've marked this task as done: \n"
        + item.toString();
        encapsulateMessage(itemMessage);
        list.set(number - 1, item);
    }

    private static void unmarkTask(int number) {
        Task item = list.get(number - 1);
        item.unmark();
        String itemMessage = "OK, I've marked this task as not done yet: \n"
        + item.toString();
        encapsulateMessage(itemMessage);
        list.set(number - 1, item);

    }

    private static void printList(){
        String itemString = "";
        for (Task item : list) {
            itemString += String.valueOf(item.index) + ". " + item.toString() + "\n";
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

            } else if (command.split(" ")[0].toLowerCase().equals("mark")) {
                int number = Integer.parseInt(command.split(" ")[1]);
                markAsDone(number);
            } else if (command.split(" ")[0].toLowerCase().equals("unmark")){
                int number = Integer.parseInt(command.split(" ")[1]);
                unmarkTask(number);
            } else {
                addToList(command);
            }

            command = sc.nextLine();

        }

        // Loop has been exited, meaning bye has been inputted
        exit();
        sc.close();
    }
}
