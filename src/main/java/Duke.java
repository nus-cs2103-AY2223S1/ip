import java.util.Scanner;

public class Duke {
    private final TaskList tasks;
    //    private Storage storage;

    public Duke() {
        tasks = new TaskList(Storage.readData());
    }

    public void run() {
        String name = "Duke";
        System.out.println("Hello! I'm " + name + "\nHow can I help you?");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                // Write tasks data to storage before terminating program
                Storage.writeData(tasks.toString());
                System.out.println("Bye! See you again :)");
                return;
            } else if (command.equals("list")) {
                tasks.printList();
                continue;
            } else if (command.contains("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks.unmark(index);
                System.out.println("Okay, this task is now unchecked:\n" + tasks.getTask(index));
            } else if (command.contains("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks.mark(index);
                System.out.println("Great! This task is completed:\n" + tasks.getTask(index));
            } else if (command.contains("delete")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                Task temp = tasks.getTask(index);
                tasks.remove(index);
                System.out.println("Noted. I've removed this task:\n" + temp);
            } else {
                try {
                    if (command.contains("todo")) {
                        String taskName = command.length() > 5 ? command.split("todo ")[1] : "";
                        if (taskName.isBlank()) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task newTodoTask = new Todo(taskName);
                        tasks.add(newTodoTask);
                        System.out.println("Got it. I've added this task:\n" + newTodoTask);
                    } else if (command.contains("deadline")) {
                        String[] res = command.split("deadline ")[1].split("\\\\by ");
                        Task newDeadlineTask = new Deadline(res[0], res[1]);
                        tasks.add(newDeadlineTask);
                        System.out.println("Got it. I've added this task:\n" + newDeadlineTask);
                    } else if (command.contains("event")) {
                        String[] res = command.split("event ")[1].split("\\\\at ");
                        Task newEventTask = new Event(res[0], res[1]);
                        tasks.add(newEventTask);
                        System.out.println("Got it. I've added this task:\n" + newEventTask);
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    continue;
                }
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
