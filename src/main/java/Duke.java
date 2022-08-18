import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> TaskList = new ArrayList<>();

    public static void addTask(Task task) {
        TaskList.add(task);
        int total = TaskList.size();
        String printLine = "Got it. I've added this task:\n" + "  " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n";
        System.out.println(printLine);
    }

    public static void mark(int number) {
        Task task =  TaskList.get(number - 1);
        task.setCompleted();
        String taskCompletion = "Nice! I've marked this task as done:\n" + "  " + task.toString() + "\n";
        System.out.println(taskCompletion);
    }

    public static void unmark(int number) {
        Task task =  TaskList.get(number - 1);
        task.setUncompleted();
        String taskUnCompletion = "Ok, I've marked this task as not done yet:\n" + "  " + task.toString() + "\n";
        System.out.println(taskUnCompletion);
    }

    public static void delete(int number) {
        Task task = TaskList.get(number - 1);
        TaskList.remove(number - 1);
        int total = TaskList.size();
        String message = "Noted. I've removed this task:\n" + " " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n";
        System.out.println(message);
    }

    public static void echo(String command) throws DukeException {
        if (command.trim().equals("bye")) {
            String bye = "Bye. Hope to see you again soon!\n";
            System.out.println(bye);
        } else if (command.trim().equals("list")) {
            String newList = "Here are the tasks in your list:\n";
            int count = 1;
            for (Task item: TaskList) {
                newList += (count + "." + item.toString() + "\n");
                count++;
            }
            System.out.println(newList);

        } else {
            if (command.startsWith("todo")) {
                    try {
                        ToDo toDo = new ToDo(command.substring(5));
                        addTask(toDo);
                    } catch (StringIndexOutOfBoundsException e){
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
                    }
            } else if (command.trim().startsWith("deadline")) {
                int end = command.indexOf('/');
                String name = command.substring(9, end );
                String date = command.substring(end + 4);
                Deadline deadline = new Deadline(name,date);
                addTask(deadline);
            } else if (command.trim().startsWith("event")) {
                int end = command.indexOf('/');
                String name = command.substring(6, end );
                String time = command.substring(end + 4);
                Event event = new Event(name,time);
                addTask(event);
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
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
                if (command.trim().startsWith("mark")) {
                    int number = Integer.parseInt(command.substring(5));
                    mark(number);
                } else if (command.trim().startsWith("unmark")) {
                    int number = Integer.parseInt(command.substring(7));
                    unmark(number);
                } else if (command.trim().startsWith("delete")) {
                    int number = Integer.parseInt(command.substring(7));
                    delete(number);
                } else {
                    echo(command);
                    if (command.startsWith("bye")) {
                        break;
                    }
                }
            }
            catch (DukeException e) {
                System.out.println(e.toString());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input :( ");
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid command");
            }

        }
    }
}
