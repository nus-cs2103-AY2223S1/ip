import java.util.Scanner;

public class Duke {

    /**
     *  This method prints out a horizontal line 40 dashes long.
     */
    public static void printLine() {
        for(int i = 0; i < 40; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * This method prints out the string s, wrapped within 2 horizontal lines.
     * @param s The given string to be printed.
     */
    public static void say(String s) {
        printLine();
        System.out.println(s);
        printLine();
    }

    /**
     * This method adds a task to the given task-list.
     * @param input The task to be added.
     * @param tasks The task-list.
     * @param numTasks The number of tasks currently.
     */
    public static void addTask(String input, String[] tasks, int numTasks) {
        tasks[numTasks] = input;
        say("added: " + input);
    }

    /**
     * This method lists out the current tasks.
     * @param tasks The task-list.
     * @param numTasks The number of tasks currently.
     */
    public static void listTasks(String[] tasks, int numTasks) {
        String list = "";
        for(int i = 0; i < numTasks; i++) {
            list +=  (i+1) + ". " + tasks[i] + "\n";
        }
        say(list);
    }


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String[] tasks = new String[100];
        int numTasks = 0;

        say("Hello! I'm Pawl\nWhat can I do for you?");

        String input = scn.nextLine();
        while (!input.equals("bye")) {

            //Handle listing of tasks
            if(input.equals("list")) {
                listTasks(tasks, numTasks);

            //Else, add task to list
            } else {
                addTask(input, tasks, numTasks);
                numTasks++;
            }
            input = scn.nextLine();
        }

        say("Bye. Hope to see you again soon!");

    }
}
