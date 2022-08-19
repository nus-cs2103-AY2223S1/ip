import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //lvlOne();
        try {
            toDo();
        } catch (DukeException de) {
            System.out.println(de);
        }
    }

    public static void toDo() throws DukeException {
        //String[] tasks = new String[100];
        //Task[] tasks = new Task[100];
        ArrayList<Task> tasks = new ArrayList<>();

        int taskNum = 0;


        drawLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What tasks do you have to do?");
        drawLine();
        Scanner sc = new Scanner(System.in);
        String str2 = sc.nextLine();

        //while (!str2.equals("bye")) {
        while (true) {
            if (str2.equals("list")) {
                showList(taskNum, tasks);
                if (sc.hasNextLine()) { str2 = sc.nextLine(); }
                continue;
            }
            if (str2.length() > 4 && (str2.substring(0, 4)).equals("mark")) {
                markDone(str2, tasks);
                drawLine();
                //str2 = sc.nextLine();
                if (sc.hasNextLine()) { str2 = sc.nextLine(); }
                continue;
            }

            if (str2.length() > 6 && (str2.substring(0, 6)).equals("unmark")) {
                markUndone(str2, tasks);
                drawLine();
                if (sc.hasNextLine()) { str2 = sc.nextLine(); }
                continue;
            }

            if (str2.length() > 4 && (str2.substring(0, 4)).equals("todo")) {
                addToDo(str2, tasks, taskNum);
                taskNum++;
                System.out.println("Now you have " + taskNum + " tasks in the list.");
                drawLine();
                if (sc.hasNextLine()) { str2 = sc.nextLine(); }
                continue;
            }

            if (str2.equals("todo")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }

            if (str2.equals("deadline")) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }

            if (str2.equals("event")) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }

            if (str2.length() > 8 && (str2.substring(0, 8)).equals("deadline")) {
                addDeadline(str2, tasks, taskNum);
                taskNum++;
                System.out.println("Now you have " + taskNum + " tasks in the list.");
                drawLine();
                if (sc.hasNextLine()) { str2 = sc.nextLine(); }
                continue;
            }

            if (str2.length() > 5 && (str2.substring(0, 5)).equals("event")) {
                addEvent(str2, tasks, taskNum);
                taskNum++;
                System.out.println("Now you have " + taskNum + " tasks in the list.");
                drawLine();
                if (sc.hasNextLine()) { str2 = sc.nextLine(); }
                continue;
            }

            if (str2.length() > 7 && (str2.substring(0, 6)).equals("delete")) {
                deleteTask(str2, tasks);
                taskNum--;
                drawLine();
                if (sc.hasNextLine()) { str2 = sc.nextLine(); }
                continue;
            }

            if (str2.equals("bye")) {
                System.out.println("Bye! See you soon!");
                break;
            }

            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");

        }
        //System.out.println("Bye! See you soon!");
    }

    /**
     * Display the list of tasks that the user has
     *
     * @param taskNo The task number
     * @param arr1 The ArrayList containing all the tasks objects
     */
    public static void showList(int taskNo, ArrayList<Task> arr1){
        drawLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNo; i++) {
            int num = i + 1;
            System.out.println(num + ". " + arr1.get(i).toString());
        }
        drawLine();
    }

    /**
     * Mark tasks as done
     *
     * @param task The string containing which task to be marked
     * @param arr1 The ArrayList containing all the tasks
     */
    public static void markDone(String task, ArrayList<Task> arr1) {
        int taskToMark = 0;
        drawLine();
        System.out.println("Nice! I've marked this task as done:");

        String strTaskToMark = "";
        for (int j = 5; j < task.length(); j++) {
            strTaskToMark = strTaskToMark + task.charAt(j);
        }

        taskToMark = Integer.parseInt(strTaskToMark);
        arr1.get(taskToMark - 1).isDone = true;
        System.out.println("[" + arr1.get(taskToMark - 1).getStatusIcon() + "] " + arr1.get(taskToMark - 1).description);
    }

    /**
     * Change status of task back to not done
     *
     * @param task The string containing which task to be unmarked
     * @param arr The ArrayList containing all the tasks
     */
    public static void markUndone(String task, ArrayList<Task> arr) {
        int taskToUnmark = 0;
        drawLine();
        System.out.println("OK, I've marked this task as not done yet:");

        String strTaskToUnmark = "";
        for (int j = 7; j < task.length(); j++) {
            strTaskToUnmark = strTaskToUnmark + task.charAt(j);
        }

        taskToUnmark = Integer.parseInt(strTaskToUnmark);
        arr.get(taskToUnmark - 1).isDone = false;
        System.out.println("[" + arr.get(taskToUnmark - 1).getStatusIcon() + "] " + arr.get(taskToUnmark - 1).description);
    }

    /**
     * Add Todo tasks
     *
     * @param str The string containing task to be added
     * @param arr The ArrayList containing all the tasks
     * @param num The task number
     */
    public static void addToDo(String str, ArrayList<Task> arr, int num) {
        drawLine();
        System.out.println("Got it. I've added this task:");
        ToDos newToDo = new ToDos(str.substring(5, str.length()));
        arr.add(num, newToDo);
        System.out.println(arr.get(num).toString());
    }

    /**
     * Add Deadline tasks
     *
     * @param str The string containing task to be added
     * @param arr The ArrayList containing all the tasks
     * @param num The task number
     */
    public static void addDeadline(String str, ArrayList<Task> arr, int num) {
        drawLine();
        System.out.println("Got it. I've added this task:");
        int k = 9;
        String desc = "";
        while (str.charAt(k) != '/') {
            desc += str.charAt(k);
            k++;
        }

        String dl = str.substring(k + 4, str.length());
        Deadlines newDeadline = new Deadlines(desc, dl);
        arr.add(num, newDeadline);
        System.out.println(arr.get(num).toString());
    }

    /**
     * Add Event tasks
     *
     * @param str The string containing task to be added
     * @param arr The ArrayList containing all the tasks
     * @param num The task number
     */
    public static void addEvent(String str, ArrayList<Task> arr, int num) {
        drawLine();
        System.out.println("Got it. I've added this task:");
        int k = 6;
        String desc = "";
        while (str.charAt(k) != '/') {
            desc += str.charAt(k);
            k++;
        }

        String eventTime = str.substring(k + 4, str.length());
        Events newEvent = new Events(desc, eventTime);
        arr.add(num, newEvent);
        System.out.println(arr.get(num).toString());
    }

    /**
     * Delete task
     *
     * @param str The string sepcifying which task to be deleted
     * @param arr The ArrayList containing all the tasks
     */
    public static void deleteTask(String str, ArrayList<Task> arr) {
        int taskToDel = 0;
        drawLine();
        System.out.println("Noted. I've removed this task:");

        String strTaskToDel = "";
        for (int j = 7; j < str.length(); j++) {
            strTaskToDel = strTaskToDel + str.charAt(j);
        }

        taskToDel = Integer.parseInt(strTaskToDel) - 1;
        System.out.println(arr.get(taskToDel).toString());
        arr.remove(taskToDel);
        System.out.println("Now you have " + arr.size() + " tasks in the list.");
    }

    /**
     * Draw horizontal line
     */
    public static void drawLine() {
        String str = "";
        for (int i = 0; i < 80; i++) {
            str += "-";
        }

        System.out.println(str);
    }
}
