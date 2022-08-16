import java.util.ArrayList;
import java.util.Arrays;
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
     * @param noType The input String with type removed.
     * @param type The type of the task
     * @param tasks The task-list.
     * @param numTasks The number of tasks currently.
     */
    public static void addTask(String noType, String type, Task[] tasks, int numTasks) {
        String confirmation = "Got it. I've added this task:\n  ";
        String number = "\nNow you have " + (numTasks + 1) + " tasks in the list.";

        if(type.equals("deadline")) {
            String[] split = noType.split(" /by ");
            tasks[numTasks] = new Deadline(split[0], split[1]);
            say(confirmation + tasks[numTasks] + number);
        } else if(type.equals("event")){
            String[] split = noType.split(" /at ");
            tasks[numTasks] = new Event(split[0], split[1]);
            say(confirmation  + tasks[numTasks] + number);
        } else {
            tasks[numTasks] = new ToDo(noType);
            say(confirmation + tasks[numTasks] + number);
        }

    }

    /**
     * Mark a task as done
     * @param index The index of the task.
     * @param tasks The task-list.
     */
    public static void markTask(String index, Task[] tasks) {
        //Note potential exception: Task index exceeded
        Task task = tasks[Integer.valueOf(index) - 1];
        task.mark();
        say("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * Mark a task as not done
     * @param index The index of the task.
     * @param tasks The task-list.
     */
    public static void unmarkTask(String index, Task[] tasks) {
        //Note potential exception: Task index exceeded
        Task task = tasks[Integer.valueOf(index) - 1];
        task.unmark();
        say("OK, I've marked this task as not done yet:\n  " + task);
    }

    /**
     * This method lists out the current tasks.
     * @param tasks The task-list.
     * @param numTasks The number of tasks currently.
     */
    public static void listTasks(Task[] tasks, int numTasks) {
        String list = "Here are the tasks in your list:\n";
        for(int i = 0; i < numTasks; i++) {
            //Add index
            list +=  (i+1) + ".";
            //Add task
            list += tasks[i];
            //Add nextline
            if(i!=numTasks -1) {
                list += "\n";
            }
        }
        say(list);
    }


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTasks = 0;

        say("Hello! I'm Pawl\nWhat can I do for you?");

        String input = scn.nextLine();
        //Potential exception: No input
        while (!input.equals("bye")) {

            ArrayList<String> split = new ArrayList<String>(Arrays.asList(input.split(" ")));
            String first = split.remove(0);
            String rest = String.join(" ", split);


            if(first.equals("list")) {
                //Handle listing of tasks
                listTasks(tasks, numTasks);

            }else if(first.equals("mark")) {
                //Mark a task as done
                markTask(rest, tasks);

            }else if(first.equals("unmark")) {
                //Mark a task as not done
                unmarkTask(rest, tasks);

            } else if(first.equals("todo") || first.equals("deadline") || first.equals("event")){
                //Else, add task to list
                addTask(rest, first, tasks, numTasks);
                numTasks++;
            }
            input = scn.nextLine();
        }

        say("Bye. Hope to see you again soon!");

    }
}
