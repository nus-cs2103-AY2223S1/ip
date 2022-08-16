import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> TaskList = new ArrayList<>();

    public static void addTask(Task task) {
        TaskList.add(task);
        int total = TaskList.size();
        String printLine = "____________________________________________________________\n" +
                "Got it. I've added this task:\n" + "  " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n" + "____________________________________________________________\n";
        System.out.println(printLine);
    }

    public static void mark(int number) {
        Task task =  TaskList.get(number - 1);
        task.setCompleted();
        String taskCompletion = "____________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" + "  " + task.toString() + "\n" +
                "____________________________________________________________\n";
        System.out.println(taskCompletion);
    }

    public static void unmark(int number) {
        Task task =  TaskList.get(number - 1);
        task.setUncompleted();
        String taskUnCompletion = "____________________________________________________________\n" +
                "Ok, I've marked this task as not done yet:\n" + "  " + task.toString() + "\n" +
                "____________________________________________________________\n";
        System.out.println(taskUnCompletion);
    }

    public static void delete(int number) {
        Task task = TaskList.get(number - 1);
        TaskList.remove(number - 1);
        int total = TaskList.size();
        String message = "____________________________________________________________\n" +
                "Noted. I've removed this task:\n" + " " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n" + "____________________________________________________________\n";
        System.out.println(message);
    }

    public static void echo(String command) throws DukeException {
        if (command.toUpperCase().equals("BYE")) {
            String bye = "____________________________________________________________\n" +
                    "Bye. Hope to see you again soon!\n" +
                    "____________________________________________________________\n";
            System.out.println(bye);
        } else if (command.toUpperCase().equals("LIST")) {
            String newList = "Here are the tasks in your list:\n";
            int count = 1;
            for (Task item: TaskList) {
                newList += (count + "." + item.toString() + "\n");
                count++;
            }
            String updatedList = "____________________________________________________________\n" + newList +
                    "____________________________________________________________\n";
            System.out.println(updatedList);

        } else {
            String [] commandArr = command.split(" ");

            if (commandArr[0].equals("todo")) {
                    if (commandArr.length == 1) {
                        throw new DukeException("____________________________________________________________\n" +
                                "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                "____________________________________________________________");
                    } else {
                        ToDo toDo = new ToDo(command.substring(5));
                        addTask(toDo);
                    }
            } else if (commandArr[0].equals("deadline")) {
                int end = command.indexOf('/');
                String name = command.substring(9, end );
                String date = command.substring(end + 4);
                Deadline deadline = new Deadline(name,date);
                addTask(deadline);
            } else if (commandArr[0].equals("event")) {
                int end = command.indexOf('/');
                String name = command.substring(6, end );
                String time = command.substring(end + 4);
                Event event = new Event(name,time);
                addTask(event);
            } else {
                throw new DukeException("____________________________________________________________\n" +
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________");
            }
        }
    }
    public static void main(String[] args)  {
        String logo = "____________________________________________________________\n" + "Hello from\n" +
                " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n" +
                "How may I assist you?\n" +
                "____________________________________________________________\n";
        System.out.println(logo);

        Scanner myScanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Please enter your command:");
                String command = myScanner.nextLine();
                String[] commandArr = command.split(" ");
                if (commandArr[0].equals("mark")) {
                    int number = Integer.parseInt(commandArr[1]);
                    mark(number);

                } else if (commandArr[0].equals("unmark")) {
                    int number = Integer.parseInt(commandArr[1]);
                    unmark(number);
                } else if (commandArr[0].equals("delete")) {
                    int number = Integer.parseInt(commandArr[1]);
                    delete(number);
                } else {
                    echo(command);
                    if (command.toUpperCase().equals("BYE")) {
                        break;
                    }

                }
            }
            catch (DukeException e) {
                System.out.println(e.toString());
            }

        }
    }
}
