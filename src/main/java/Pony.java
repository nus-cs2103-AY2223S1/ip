import java.util.Scanner;
import java.util.ArrayList;
public class Pony {

    private String greet = "Hello! I'm Pony" + "\n" + "What can I do for you?";
    private String line  = "___________________________________________________";
    private String exit = "Bye. Hope to see you again soon!";
    Scanner sc = new Scanner(System.in);
    ArrayList<Task> tasks;

    public Pony() {
        this.tasks = new ArrayList<>();
    };

    public void initialise() {
        System.out.println(this.greet);
    }

    private String[] takeCommand() {
        String command = this.sc.nextLine();
        String[] strArr = command.split(" ",2);
        return strArr;
    }

    private void processCommand(String[] command) {
        int commandSize = command.length;
        String action = command[0];
        if (action.equals("list")) {
            if (this.tasks.size() == 0) {
                System.out.println("Nothing on the list!");
                run();
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < this.tasks.size(); i++) {
                    int sn = i + 1;
                    System.out.println(sn + ". " + this.tasks.get(i).toString());
                }
                run();
            }
        } else if (action.equals("mark")) {
            // mark expects input in command[1] -> which task to mark
            // check if command is valid
            try {
                if (commandSize != 2) {
                    throw new PonyException.taskMissingError();
                } else {
                    int taskIndex = Integer.parseInt(command[1]);
                    Task target = this.tasks.get(taskIndex - 1);
                    target.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(target.toString());
                }
            } catch (PonyException.taskMissingError e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(new PonyException.taskInputError().getMessage());
            } finally {
                run();
            }
        } else if (action.equals("unmark")) {
            // unmark expects input in command[1] -> which task to unmark
            // check if command is valid
            try {
                if (commandSize != 2) {
                    throw new PonyException.taskMissingError();
                } else {
                    int taskIndex = Integer.parseInt(command[1]);
                    Task target = this.tasks.get(taskIndex - 1);
                    target.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(target.toString());
                }
            } catch (PonyException.taskMissingError e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(new PonyException.taskInputError().getMessage());
            } finally {
                run();
            }
        } else if (action.equals("todo")) {
            // tod0 expects task description in command[1]
            // check if command is valid
            try {
                if (commandSize != 2) {
                    throw new PonyException.taskMissingError();
                } else {
                    String description = command[1];
                    Task newTask = new ToDo(description);
                    this.tasks.add(newTask);
                    System.out.println("Got it. I've added this task: " + newTask.toString());
                    System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
                    run();
                }
            } catch (PonyException.taskMissingError e) {
                System.out.println(e.getMessage());
            } finally {
                run();
            }
        } else if (action.equals("deadline")) {
            // deadline expects description in command[1]
            // check if command is valid
            try {
                if (commandSize != 2) {
                    throw new PonyException.taskMissingError();
                } else {
                    // format of command: deadline <task> /by <time>
                    String[] taskInfoArr = command[1].split(" /by ", 2);
                    if (taskInfoArr.length != 2) {
                        String format = "<task> /by <time>";
                        throw new PonyException.taskFormatError(format);
                    } else {
                        String description = taskInfoArr[0];
                        String time = taskInfoArr[1];
                        Task newTask = new Deadline(description, time);
                        this.tasks.add(newTask);
                        System.out.println("Got it. I've added this task: " + newTask.toString());
                        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
                    }
                }
            } catch (PonyException.taskMissingError | PonyException.taskFormatError e) {
                System.out.println(e.getMessage());
            } finally {
                run();
            }
        } else if (action.equals("event")) {
            // event expects description in command[1]
            // check if command is valid
            try {
                if (commandSize != 2) {
                    throw new PonyException.taskMissingError();
                } else {
                    // format of command: event <task> /at <time>
                    String[] taskInfoArr = command[1].split(" /at ", 2);
                    if (taskInfoArr.length != 2) {
                        String format = "<task> /at <time>";
                        throw new PonyException.taskFormatError(format);
                    } else {
                        String description = taskInfoArr[0];
                        String time = taskInfoArr[1];
                        Task newTask = new Event(description, time);
                        this.tasks.add(newTask);
                        System.out.println("Got it. I've added this task: " + newTask.toString());
                        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
                    }
                }
            } catch (PonyException.taskMissingError | PonyException.taskFormatError e) {
                System.out.println(e.getMessage());
            } finally {
                run();
            }
        } else if (action.equals("delete")) {
            // delete expects input in command[1] -> which task to delete
            // check if command is valid
            try {
                if (commandSize != 2) {
                    throw new PonyException.taskMissingError();
                } else {
                    int taskIndex = Integer.parseInt(command[1]);
                    Task target = this.tasks.get(taskIndex - 1);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(target.toString());
                    tasks.remove(taskIndex - 1);
                    System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
                }
            } catch (PonyException.taskMissingError e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(new PonyException.taskInputError().getMessage());
            } finally {
                run();
            }
        } else if (action.equals("bye")) {
            System.out.println(this.exit);
        } else {
            try {
                throw new PonyException.inputError();
            } catch (PonyException.inputError e) {
                System.out.println(e.getMessage());
            } finally {
                run();
            }
        }
    }

    public void run() {
        String[] command = takeCommand();
        processCommand(command);
    }

    public static void main(String[] args) {
        Pony myPony = new Pony();
        myPony.initialise();
        myPony.run();
    }
}
