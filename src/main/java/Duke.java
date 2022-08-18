import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> storeList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();

        logicRun();

        farewell();

    }

    public static void greeting() {
        String greet = "Hello! I'm Duke \n"
                + "What can I do for you? \n";
        System.out.println(greet);
    }

    public static void logicRun() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while(!userInput.equals("bye")) {
            userInput = scanner.nextLine();

            if(userInput.equals("list")) {
                list();
                continue;
            }
            if(userInput.equals("bye")) {
                break;
            }
            if(userInput.split(" ", 2)[0].equals("mark")) {
                int inputTaskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                mark(inputTaskIndex);
                continue;
            }
            if(userInput.split(" ", 2)[0].equals("unmark")) {
                int inputTaskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                unmark(inputTaskIndex);
                continue;
            }
            if(userInput.split(" ", 2)[0].equals("delete")) {
                int inputTaskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                deleteTask(inputTaskIndex);
                continue;
            }

            try{
                userInputCheck(userInput);

                if(userInput.split(" ", 2)[0].equals("todo")) {
                    String taskInput = userInput.split(" ", 2)[1];
                    todo(taskInput);
                    continue;
                }
                if(userInput.split(" ", 2)[0].equals("deadline")) {
                    String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
                    String by = userInput.split("/", 2)[1].split(" ", 2)[1];
                    deadline(taskInput, by);
                    continue;
                }
                if(userInput.split(" ", 2)[0].equals("event")) {
                    String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
                    String duration = userInput.split("/", 2)[1].split(" ", 2)[1];
                    event(taskInput, duration);
                    continue;
                }

            }
            catch (DukeException err) {
                System.out.println(err.getMessage());
                continue;
            }
//            if(userInput.split(" ", 2)[0].equals("todo")) {
//                try {
//                    userInputCheck(userInput);
//                    String taskInput = userInput.split(" ", 2)[1];
//                    todo(taskInput);
//                }
//                catch (DukeException err) {
//                    System.out.println(err.getMessage());
//                }
//                continue;
//            }
//            if(userInput.split(" ", 2)[0].equals("deadline")) {
//                String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
//                String by = userInput.split("/", 2)[1].split(" ", 2)[1];
//                deadline(taskInput, by);
//                continue;
//            }
//            if(userInput.split(" ", 2)[0].equals("event")) {
//                String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
//                String duration = userInput.split("/", 2)[1].split(" ", 2)[1];
//                event(taskInput, duration);
//                continue;
//            }
            addTask(userInput);
        }
    }


    public static void echo(String userInput) {
        System.out.println(userInput);
    }

    public static void addTask(String userInput) {
        Task t = new Task(userInput);
        storeList.add(t);
        System.out.println("added: " + t.description);
    }

    public static void deleteTask(int taskIndex) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + storeList.get(taskIndex).toString());
        storeList.remove(taskIndex);
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");

    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i < storeList.size(); i++){
//            System.out.printf("%i. [%c] %s", i + 1, storeList.get(i).getStatusIcon(), storeList.get(i).description);
            System.out.println(i + 1 + ". " + storeList.get(i).toString());
        }
    }

    public static void mark(int taskIndex) {
        storeList.get(taskIndex).markAsDone();
    }

    public static void unmark(int taskIndex) {
        storeList.get(taskIndex).markAsNotDone();
    }

    public static void todo(String userInput) throws DukeException {
        Todo t = new Todo(userInput);
        if (userInput.isEmpty()) {
            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
        storeList.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + t.toString());
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");

    }

    public static void deadline(String userInput, String by) {
        Deadline d = new Deadline(userInput, by);
        storeList.add(d);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + d.toString());
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
    }

    public static void event(String userInput, String duration) {
        Event e = new Event(userInput, duration);
        storeList.add(e);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + e.toString());
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
    }

    public static void userInputCheck(String userInput) throws DukeException {
        String firstCommand = userInput.split(" ", 2)[0];
        if(!firstCommand.equals("todo") && !firstCommand.equals("deadline") && !firstCommand.equals("event")) {
            throw new DukeException("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if(userInput.split(" ", 2).length == 1) {
            throw new DukeException("\t ☹ OOPS!!! The description of a " + firstCommand + " cannot be empty.");
        }
        if((firstCommand.equals("deadline") || firstCommand.equals("event")) && userInput.split("/", 2).length == 1) {
            throw new DukeException("\t ☹ OOPS!!! The time due or needed cannot be empty.");
        }
    }

    public static void farewell() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}
