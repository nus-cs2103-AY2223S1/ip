import java.lang.reflect.Array;
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

    public static void lvlOne() {
        System.out.println("Hello! I'm Duke\n");
        Scanner sc = new Scanner(System.in);
        System.out.println("Tell me a word!");
        String str = sc.nextLine();
        System.out.println(str);

        while (!str.equals("bye") && !str.equals("goodbye")) {
            sc = new Scanner(System.in);
            str = sc.nextLine();
            System.out.println(str);
        }
        System.out.println("Bye! See you soon!");
    }

    public static void toDo() throws DukeException {
        //String[] tasks = new String[100];
        Task[] tasks = new Task[100];

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
                System.out.println("Got it. I've added this task:");
                ToDos newToDo = new ToDos(str2.substring(5, str2.length()));
                tasks[taskNum] = newToDo;
                System.out.println(tasks[taskNum].toString());
                taskNum++;
                System.out.println("Now you have " + taskNum + " tasks in the list.");

                str2 = sc.nextLine();
                continue;
            }

            if (str2.length() == 4 && (str2.substring(0, 4)).equals("todo")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }

            if (str2.length() > 8 && (str2.substring(0, 8)).equals("deadline")) {
                System.out.println("Got it. I've added this task:");
                int k = 9;
                String desc = "";
                while (str2.charAt(k) != '/') {
                    desc += str2.charAt(k);
                    k++;
                }

                String dl = str2.substring(k + 4, str2.length());
                Deadlines newDeadline = new Deadlines(desc, dl);
                tasks[taskNum] = newDeadline;
                System.out.println(tasks[taskNum].toString());
                taskNum++;
                System.out.println("Now you have " + taskNum + " tasks in the list.");
                str2 = sc.nextLine();
                continue;
            }

            if (str2.length() > 5 && (str2.substring(0, 5)).equals("event")) {
                System.out.println("Got it. I've added this task:");
                int k = 6;
                String desc = "";
                while (str2.charAt(k) != '/') {
                    desc += str2.charAt(k);
                    k++;
                }

                String eventTime = str2.substring(k + 4, str2.length());
                Events newEvent = new Events(desc, eventTime);
                tasks[taskNum] = newEvent;
                System.out.println(tasks[taskNum].toString());
                taskNum++;
                System.out.println("Now you have " + taskNum + " tasks in the list.");
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

    public static void showList(int taskNo, Task[] arr1){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNo; i++) {
            int num = i + 1;
            //System.out.println(num + ". " + "[" + arr1[i].getStatusIcon() + "] " + arr1[i].description);
            System.out.println(num + ". " + arr1[i].toString());
        }
    }

    public static void markDone(String task, Task[] arr1) {
        int taskToMark = 0;
        System.out.println("Nice! I've marked this task as done:");

        String strTaskToMark = "";
        for (int j = 5; j < task.length(); j++) {
            strTaskToMark = strTaskToMark + task.charAt(j);
        }

        taskToMark = Integer.parseInt(strTaskToMark);
        arr1[taskToMark - 1].isDone = true;
        System.out.println("[" + arr1[taskToMark - 1].getStatusIcon() + "] " + arr1[taskToMark - 1].description);
    }

}
