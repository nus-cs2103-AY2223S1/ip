import java.util.Scanner;

public class Duke {
    private final TaskList tasks;
    private Ui ui;
    //    private Storage storage;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList(Storage.readData());
    }

    public void run() {
        ui.printWelcome();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                // Write tasks data to storage before terminating program
                Storage.writeData(tasks.toString());
                ui.printGoodbye();
                return;
            } else if (command.equals("list")) {
                tasks.printList();
                continue;
            } else if (command.contains("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks.unmark(index);
                ui.printUnmark(tasks.getTask(index));
            } else if (command.contains("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks.mark(index);
                ui.printMark(tasks.getTask(index));
            } else if (command.contains("delete")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                Task temp = tasks.getTask(index);
                tasks.remove(index);
                ui.printDelete(temp);
            } else {
                try {
                    if (command.contains("todo")) {
                        String taskName = command.length() > 5 ? command.split("todo ")[1] : "";
                        if (taskName.isBlank()) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task newTodoTask = new Todo(taskName);
                        tasks.add(newTodoTask);
                        ui.printAdd(newTodoTask);
                    } else if (command.contains("deadline")) {
                        String[] res = command.split("deadline ")[1].split("\\\\by ");
                        Task newDeadlineTask = new Deadline(res[0], res[1]);
                        tasks.add(newDeadlineTask);
                        ui.printAdd(newDeadlineTask);
                    } else if (command.contains("event")) {
                        String[] res = command.split("event ")[1].split("\\\\at ");
                        Task newEventTask = new Event(res[0], res[1]);
                        tasks.add(newEventTask);
                        ui.printAdd(newEventTask);
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
