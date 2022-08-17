import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws DukeException {
        final String ADDED_TASK = "Got it. I've added this task:";
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Luke\nWhat can I do for you?");
        String command = "";
        
        while (!command.equals("bye")) {
            try {
                command = scanner.nextLine().strip();

                if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(String.valueOf(i + 1) + "." + task);
                    }
    
                } else if (command.startsWith("mark")) {
                    if (command.strip().length() == 4) {
                        throw new DukeException("Choose which task to mark as done!");
                    }
                    int taskNumber = Integer.parseInt(command.substring(5));
                    tasks.get(taskNumber - 1).setDone();
    
    
                } else if (command.startsWith("unmark")) {
                    if (command.strip().length() == 6) {
                        throw new DukeException("Choose which task to mark as undone!");
                    }
                    int taskNumber = Integer.parseInt(command.substring(7));
                    tasks.get(taskNumber - 1).setUndone();
                    
    
                } else if (command.startsWith("todo")) {
                    if (command.strip().length() == 4) {
                        throw new DukeException("The description of a todo cannot be empty");
                    }
                    
                    Task task = new Todo(command.substring(5));
                    tasks.add(task);
                    System.out.println(String.format("%s\n%s", ADDED_TASK, task));
                    System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ?" task" : " tasks") + " in the list.");
                    
                } else if (command.startsWith("event")) {
                    if (command.strip().length() == 5) {
                        throw new DukeException("The description of an event cannot be empty");
                    }

                    if (command.strip().indexOf('/') == -1) {
                        throw new DukeException("The date of the event cannot be empty");
                    } 

                    String[] split = command.substring(6).split("/");
                    Task task = new Event(split[0], split[1].substring(3));
                    tasks.add(task);
                    System.out.println(String.format("%s\n%s", ADDED_TASK, task));
                    System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ?" task" : " tasks") + " in the list.");
    
                } else if (command.startsWith("deadline")) {
                    if (command.strip().length() == 8) {
                        throw new DukeException("The description of an deadline cannot be empty");
                    }

                    if (command.strip().indexOf('/') == -1) {
                        throw new DukeException("The date of the deadline cannot be empty");
                    }


                    String[] split = command.substring(9).split("/");
                    Task task = new Deadline(split[0], split[1].substring(3));
                    tasks.add(task);
                    System.out.println(String.format("%s\n%s", ADDED_TASK, task));
                    System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ?" task" : " tasks") + " in the list.");

                } else if (command.equals("bye")) {
                    System.out.println("Bye! Thanks for using Luke!");
                    scanner.close();
    
                } else {
                    throw new DukeException("I'm sorry, but I dont know what you mean :(");
                }                
                
            } catch (DukeException e) {
                System.out.println(e);
            }

        }
    
    }
}
