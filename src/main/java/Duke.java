import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Duke {

    //create a task list to record all kinds of tasks to do
    public static ArrayList<Task> taskList = new ArrayList<Task>();

    //create a function to draw the cut-off line
    public static void cutOff() {
        System.out.println("-".repeat(100));
    }

    //the start dialog
    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        cutOff();
        System.out.println("Hola! I'm Ashy (//●⁰౪⁰●)//\n" +
                "What can I do for you?");
        cutOff();
    }

    //the bye dialog
    public static void bye() {
        cutOff();
        System.out.println("Byeeee~ Hope to see you again soon (•͈⌔•͈⑅)");
        cutOff();
    }

    //this function is to list all the tasks having been created
    //this function is evoked after the user enters "list"
    public static void listAll() {
        cutOff();
        for (int i = 0; i < taskList.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " +  taskList.get(i).toString());
        }
        cutOff();
    }

    //add a task when user enter a todotask , a deadline task or an event task
    //if invalid instruction is given, a exception is thrown
    public static void addTask(String command) throws DukeException {
        String[] arr = command.split(" ", 2);
        String type = arr[0];

        switch (type) {
            case "todo": //a todotask is entered
                if (arr.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.(-`д´-)");
                }
                String todoContent = arr[1].trim();
                Task todo = new Todo(todoContent);
                taskList.add(todo);
                break;

            case "deadline": //a deadline with given ddl time is entered
                if (arr.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.(-`д´-)");
                }
                String[] deadline = arr[1].split("/");
                String deadlineContent = deadline[0];
                String deadlineBy = deadline[1];
                Task ddl = new Deadline(deadlineContent, deadlineBy);
                taskList.add(ddl);
                break;

            case "event": //an event with a given event time is entered
                if (arr.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.(-`д´-)");
                }
                String[] event = arr[1].split("/");
                String eventContent = event[0];
                String eventAt = event[1];
                Task evnt = new Event(eventContent, eventAt);
                taskList.add(evnt);
                break;

            default: // if nonsense is entered
                DukeException e = new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means (╬ Ò ‸ Ó)");
                throw e;
        }

        cutOff();
        int num = taskList.size();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(num - 1).toString());
        System.out.println("Now you have " + num + " tasks in the list.");
        cutOff();
    }

    //mark the given task as done
    public static void mark(int num) {
        cutOff();
        taskList.get(num - 1).markAsDone();
        System.out.println("OK, I've marked this task as done:");
        System.out.println(taskList.get(num - 1).toString());
        cutOff();
    }

    //mark the given task as undone
    public static void unmark(int num) {
        cutOff();
        taskList.get(num - 1).markAsUnDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(num - 1).toString());
        cutOff();
    }

    //delete the given task from the tasklist
    public static void delete(int num) {
        cutOff();
        Task remove = taskList.get(num - 1);
        taskList.remove(num - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(remove.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        cutOff();
    }

    //main method
    public static void main(String[] args) {

        start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];
            switch (command) {
                case "bye":
                    bye();
                    return;
                case "list":
                    listAll();
                    break;
                case "mark":
                    int num1 = Integer.parseInt(input.split(" ")[1]);
                    mark(num1);
                    break;
                case "unmark":
                    int num2 = Integer.parseInt(input.split(" ")[1]);
                    unmark(num2);
                    break;
                case "delete":
                    int num3 = Integer.parseInt(input.split(" ")[1]);
                    delete(num3);
                    break;
                default:
                    try {
                        addTask(input);
                    } catch (DukeException e) {
                        cutOff();
                        System.out.println(e.getMessage());
                        cutOff();
                    }
                    break;
            }
        }

    }

}