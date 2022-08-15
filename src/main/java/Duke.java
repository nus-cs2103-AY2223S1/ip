import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> taskArr = new ArrayList<>();

    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke. How may I assist you?");
    }

    public void sayBye() {
        String message = "Bye! Hope to see you soon!";
        System.out.println(message);
    }
 
    public void listTasks() {
        System.out.println("Here are your tasks: ");
        int len = this.taskArr.size();
        for (int i = 0; i < len; i++) {
            Task task = this.taskArr.get(i);
            System.out.printf("%d. %s%n", i + 1, task);
        }
    }

    public void markTaskAsDone(int taskIndex) {
        Task task = this.taskArr.get(taskIndex);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("   " + task);
    }

    public void unmarkTaskAsDone(int taskIndex) {
        Task task = this.taskArr.get(taskIndex);
        task.unmarkAsDone();
        System.out.println("Sure! I've marked this task as not yet done: ");
        System.out.println("   " + task);
    }

    public void deleteTask(int taskIndex) {
        Task task = this.taskArr.get(taskIndex);
        this.taskArr.remove(taskIndex);
        System.out.println("Swee! I've removed this task: ");
        System.out.println("   " + task);
        System.out.println("Now, you have " + this.taskArr.size() + " tasks in the list");
    }
    
    public void addTask(String type, String details) throws DukeException {
        Task task;
        // Dateless task
        if (type.equals("todo")) {
            if (details.length() == 0) {
                throw new MissingDescriptionException("todo");
            }
            task = new Todo(details);
        } else if (type.equals("deadline") || type.equals("event")) { // dated tasks
            String[] strArr = type.equals("deadline") ? details.split(" /by ") : details.split(" /at ");
            String description = strArr[0].strip();

            // Description is missing
            if (description.length() == 0) {
                throw new MissingDescriptionException(type);
            }

            if (strArr.length > 1) {
                String date = strArr[1].strip();
                task = type.equals("deadline") ? new Deadline(description, date) : new Event(description, date);
            } else { // Description is present, but date is missing
                throw new MissingDateException(type);
            }
        } else { // Unknown task
            throw new UnknownCommandException();
        }
        this.taskArr.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println("Now, you have " + this.taskArr.size() + " tasks in the list");
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        this.greetUser();

        System.out.print(">>> ");
        while (scanner.hasNext()) {
            String command = scanner.next();
            if (command.equals("bye")) {
                this.sayBye();
                break;
            }

            if (command.equals("list")) {
                this.listTasks();
            } else if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
                if (scanner.hasNextInt()) {
                    int taskIndex = scanner.nextInt() - 1;
                    if (taskIndex < 0 || taskIndex >= this.taskArr.size() ) {
                        System.out.println("Invalid task index.");
                    } else {
                        if (command.equals("mark")) {
                            this.markTaskAsDone(taskIndex);
                        } else if (command.equals("unmark")) {
                            this.unmarkTaskAsDone(taskIndex);
                        } else {
                            this.deleteTask(taskIndex);
                        }
                    }
                }
            } else {
                String taskDetails = scanner.nextLine().strip();
                try {
                    this.addTask(command, taskDetails);
                } catch (DukeException de) {
                    System.out.println(de.getMessage());
                }  
            }
            System.out.print(">>> ");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}