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
    public static void addTask(String noType, String type, ArrayList<Task> tasks, int numTasks)
            throws DukeException {
        String confirmation = "Got it. I've added this task:\n  ";
        String number = "\nNow you have " + (numTasks + 1) + " tasks in the list.";

        if(noType == ""){
            throw new DukeException("☹ OOPS!!! The description of a " + type +" cannot be empty.");
        }

        if(type.equals("deadline")) {
            String[] split = noType.split(" /by ");
            if(split.length != 2){
                throw new DukeException("☹ OOPS!!! A single deadline must be specified." +
                        "e.g. deadline finish work /by June 9th");
            }
            tasks.add(numTasks, new Deadline(split[0], split[1]));
            say(confirmation + tasks.get(numTasks) + number);
        } else if(type.equals("event")){
            String[] split = noType.split(" /at ");
            if(split.length != 2){
                throw new DukeException("☹ OOPS!!! A single timeframe must be specified." +
                        "e.g. event meeting /at Thursday 2-4pm");
            }
            tasks.add(numTasks, new Event(split[0], split[1]));
            say(confirmation  + tasks.get(numTasks) + number);
        } else {
            tasks.add(numTasks, new ToDo(noType));
            say(confirmation + tasks.get(numTasks) + number);
        }

    }

    public static void deleteTask(String index, ArrayList<Task> tasks, int numTasks) throws  DukeException{
        if(!index.matches("\\d+")) {
            throw new DukeException("☹ OOPS!!! Please specify an integer index.");
        } else if(Integer.valueOf(index) > numTasks) {
            throw new DukeException("☹ OOPS!!! The index specified is out of range.");
        }
        Task task = tasks.get(Integer.valueOf(index) - 1);
        tasks.remove(Integer.valueOf(index) - 1);
        say("Noted. I've removed this task:\n  " + task + "\nNow you have " + (numTasks - 1) +
                " tasks in the list.");
    }

    /**
     * Mark a task as done
     * @param index The index of the task.
     * @param tasks The task-list.
     */
    public static void markTask(String index, ArrayList<Task> tasks, int numTasks)
            throws DukeException {
        if(!index.matches("\\d+")) {
            throw new DukeException("☹ OOPS!!! Please specify an integer index.");
        } else if(Integer.valueOf(index) > numTasks) {
            throw new DukeException("☹ OOPS!!! The index specified is out of range.");
        }
        Task task = tasks.get(Integer.valueOf(index) - 1);
        task.mark();
        say("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * Mark a task as not done
     * @param index The index of the task.
     * @param tasks The task-list.
     */
    public static void unmarkTask(String index, ArrayList<Task> tasks, int numTasks)
            throws DukeException {
        //Note potential exception: Task index exceeded
        if(!index.matches("\\d+")) {
            throw new DukeException("☹ OOPS!!! Please specify an integer index.");
        } else if(Integer.valueOf(index) > numTasks) {
            throw new DukeException("☹ OOPS!!! The index specified is out of range.");
        }
        Task task = tasks.get(Integer.valueOf(index) - 1);
        task.unmark();
        say("OK, I've marked this task as not done yet:\n  " + task);
    }

    /**
     * This method lists out the current tasks.
     * @param tasks The task-list.
     * @param numTasks The number of tasks currently.
     */
    public static void listTasks(ArrayList<Task> tasks, int numTasks) {
        String list = "Here are the tasks in your list:\n";
        for(int i = 0; i < numTasks; i++) {
            //Add index
            list +=  (i+1) + ".";
            //Add task
            list += tasks.get(i);
            //Add nextline
            if(i!=numTasks -1) {
                list += "\n";
            }
        }
        say(list);
    }


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(100);
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
                try {
                    markTask(rest, tasks, numTasks);
                } catch (DukeException ex) {
                    say(ex.toString());
                }
            }else if(first.equals("unmark")) {
                //Mark a task as not done
                try {
                    unmarkTask(rest, tasks, numTasks);
                } catch (DukeException ex) {
                    say(ex.toString());
                }
            } else if(first.equals("delete")) {
                //Delete a task at a given index
                try {
                    deleteTask(rest, tasks, numTasks);
                    numTasks --;
                } catch (DukeException ex) {
                    say(ex.toString());
                }
            } else if(first.equals("todo") || first.equals("deadline") || first.equals("event")){
                //Else, add task to list
                try {
                    addTask(rest, first, tasks, numTasks);
                    numTasks++;
                } catch (DukeException ex) {
                    say(ex.toString());
                }
            } else {
                say("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            input = scn.nextLine();
        }

        say("Bye. Hope to see you again soon!");
    }
}
