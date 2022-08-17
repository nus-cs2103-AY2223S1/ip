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

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What tasks do you have to do?");
        Scanner sc = new Scanner(System.in);
        String str2 = sc.nextLine();

        while (!str2.equals("bye")) {
            if (str2.equals("list")) {
                showList(taskNum, tasks);
                str2 = sc.nextLine();
                continue;
            }
            if (str2.length() > 4 && (str2.substring(0, 4)).equals("mark")) {
                markDone(str2, tasks);
                str2 = sc.nextLine();
                continue;
            }

            if (str2.length() > 4 && (str2.substring(0, 4)).equals("todo")) {
                addToDo(str2, tasks, taskNum);
                taskNum++;
                System.out.println("Now you have " + taskNum + " tasks in the list.");
                str2 = sc.nextLine();
                continue;
            }

            if (str2.length() == 4 && (str2.substring(0, 4)).equals("todo")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }

            if (str2.length() > 8 && (str2.substring(0, 8)).equals("deadline")) {
                addDeadline(str2, tasks, taskNum);
                taskNum++;
                System.out.println("Now you have " + taskNum + " tasks in the list.");
                str2 = sc.nextLine();
                continue;
            }

            if (str2.length() > 5 && (str2.substring(0, 5)).equals("event")) {
                addEvent(str2, tasks, taskNum);
                taskNum++;
                System.out.println("Now you have " + taskNum + " tasks in the list.");
                str2 = sc.nextLine();
                continue;
            }

            if (str2.length() > 7 && (str2.substring(0, 6)).equals("delete")) {
                deleteTask(str2, tasks);
                taskNum--;
                str2 = sc.nextLine();
                continue;
            }

            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");

            /**Task newTask = new Task(str2);
            tasks[taskNum] = newTask;
            taskNum++;
            System.out.println("added: " + str2);
            str2 = sc.nextLine();*/
        }
        System.out.println("Bye! See you soon!");
    }

    public static void showList(int taskNo, ArrayList<Task> arr1){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNo; i++) {
            int num = i + 1;
            System.out.println(num + ". " + arr1.get(i).toString());
        }
    }

    public static void markDone(String task, ArrayList<Task> arr1) {
        int taskToMark = 0;
        System.out.println("Nice! I've marked this task as done:");

        String strTaskToMark = "";
        for (int j = 5; j < task.length(); j++) {
            strTaskToMark = strTaskToMark + task.charAt(j);
        }

        taskToMark = Integer.parseInt(strTaskToMark);
        arr1.get(taskToMark - 1).isDone = true;
        System.out.println("[" + arr1.get(taskToMark - 1).getStatusIcon() + "] " + arr1.get(taskToMark - 1).description);
    }

    public static void addToDo(String str, ArrayList<Task> arr, int num) {
        System.out.println("Got it. I've added this task:");
        ToDos newToDo = new ToDos(str.substring(5, str.length()));
        arr.add(num, newToDo);
        System.out.println(arr.get(num).toString());
    }

    public static void addDeadline(String str, ArrayList<Task> arr, int num) {
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

    public static void addEvent(String str, ArrayList<Task> arr, int num) {
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

    public static void deleteTask(String str, ArrayList<Task> arr) {
        int taskToDel = 0;
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
}
