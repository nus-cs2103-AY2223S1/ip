import java.util.Objects;
import java.util.Scanner;


/**
 * Duke is a basic chat-bot that echoes whatever the user inputs.
 *
 * @author Chi Song Yi Amadeus
 * @version Level-3
 * @since 17-08-2022
 */
public class Duke {

    /**
     * Array used to store user inputs as a Task Object.
     */
    static Task[] taskArray = new Task[100];

    /**
     * Counter to keep track of the number of items in the list
     */
    static int count = 0;

    /**
     * Main method initializes welcome message, and then calls taskList method.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String initMessage =
                "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     I can store a to-do list for you!\n" +
                "     Enter 'list' to display current contents and 'bye' to end the program.\n" +
                "     Start entering your tasks!\n" +
                "    ____________________________________________________________";

        System.out.println(initMessage);
        taskList();
    }

    /**
     * Function prints all items in the list chronologically entered and numbered from 1 upwards.
     *
     */
    public static void readList() {
        System.out.println("    ____________________________________________________________\n");

        for (int i = 1; i <= count; i++){
            System.out.println(taskArray[i - 1].toString());
        }

        System.out.println("    ____________________________________________________________\n");
     }

    /**
     * taskList method creates an input loop, creating Task objects and adding it to the array.
     *
     * @throws NumberFormatException if User inputs a non integer after mark/unmark
     */
    public static void taskList() throws NumberFormatException{
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();
        while (!Objects.equals(input.toLowerCase(), "bye")) {
            if (Objects.equals(input.toLowerCase(), "list")) {
                 // readList function called to display list contents
                 readList();
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                 String[] command =  input.split(" ",2);
                 String action = command[0];
                 String number  = command[1];
                 try {
                     int num = Integer.parseInt(number);
                     markTasks(action, num);
                 } catch (NumberFormatException e) {
                     System.out.println("Invalid task! Please input a number!");
                 }


            } else {
                 Task newTask = new Task(input, count + 1);
                 taskArray[count] = newTask;
                 count++;
            }
            input = userInput.nextLine();
        }
        System.out.println(
                 "    ____________________________________________________________\n" +
                 "     Bye. Hope to see you again soon!\n" +
                 "    ____________________________________________________________\n");
     }

    /**
     * markTasks applies the required action on the correct task ID.
     *
     * @param action to indicate mark/unmark
     * @param index to indicate which task to apply action to
     */
    public static void markTasks(String action, int index) {
        if (index > count || index < 1) {
            System.out.println("Invalid task ID!");
        } else if (Objects.equals(action, "mark")){
            taskArray[index - 1].mark();
        } else {
            taskArray[index - 1].unMark();
        }
     }
}
