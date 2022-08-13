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
    public static void addTask(String input, Task[] tasks, int numTasks) {
        tasks[numTasks] = new Task(input);
        say("added: " + input);
    }

    /**
     * Mark a task as done
     * @param index The index of the task.
     * @param tasks The task-list.
     */
    public static void markTask(String index, Task[] tasks) {
        Task task = tasks[Integer.valueOf(index) - 1];
        task.mark();
        say("Nice! I've marked this task as done:\n  [X] " + task);
    }

    /**
     * Mark a task as not done
     * @param index The index of the task.
     * @param tasks The task-list.
     */
    public static void unmarkTask(String index, Task[] tasks) {
        Task task = tasks[Integer.valueOf(index) - 1];
        task.unmark();
        say("OK, I've marked this task as not done yet:\n  [ ] " + task);
    }

    /**
     * This method lists out the current tasks.
     * @param tasks The task-list.
     * @param numTasks The number of tasks currently.
     */
    public static void listTasks(Task[] tasks, int numTasks) {
        String list = "Here are the tasks in your list:\n";
        for(int i = 0; i < numTasks; i++) {
            list +=  (i+1) + "." + "[" + (tasks[i].isDone()?"X":" ") + "] " + tasks[i] + "\n";
        }
        say(list);
    }


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTasks = 0;

        say("Hello! I'm Pawl\nWhat can I do for you?");

        String input = scn.nextLine();
        while (!input.equals("bye")) {

            String[] split = input.split(" ");

            if(split[0].equals("list")) {
                //Handle listing of tasks
                listTasks(tasks, numTasks);

            }else if(split[0].equals("mark")) {
                //Mark a task as done
                markTask(split[1], tasks);


            }else if(split[0].equals("unmark")) {
                //Mark a task as not done
                unmarkTask(split[1], tasks);

            } else {
                //Else, add task to list
                addTask(input, tasks, numTasks);
                numTasks++;
            }
            input = scn.nextLine();
        }

        say("Bye. Hope to see you again soon!");

    }
}
