import java.util.ArrayList;
import java.util.List;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello from\n"  + LOGO + "\nHow can I help you ?\n" ;


    public Ui(){

    }

    /*
     * Prints the startup message
     */
    public void showWelcome() {
        System.out.println(GREETING);
    }

    /*
     * Prints the ending message
     */
    public void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println( "No tasks.");
        }
        for(int i = 0; i<tasks.size(); i++){
            Integer index = i+1;
            System.out.println(index + ". " + tasks.get(i));
        }
    }

    /**
     * A helper function that adds the task to the Task List and prints out the appropriate message.
     * @param task The task to be added
     */
    public void printAddTaskMsg (Task task,int size){
        System.out.println("Got it. I've added this task:\n " + task.toString() + "\nNow you have " + size +" tasks in the list.");
    }


    public void printDateItems(List<String> onThisDate, List<String> byThisDate){
        System.out.println("Things on this day :" );
        for(String s : onThisDate) {
            System.out.println(s + "\n");
        }
        System.out.println("Things to do by this day :" );
        for(String s : byThisDate) {
            System.out.println(s + "\n");
        }
    }

}
