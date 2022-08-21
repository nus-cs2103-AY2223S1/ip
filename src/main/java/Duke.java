import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private void run() throws DukeException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I am a ToDos, Events, Deadlines and Talk Bot, otherwise known as TEDTalk\n" +
                "What can I do for you today?");

        ArrayList<Task> store = new ArrayList<>();

        while (true) {

            String next = sc.nextLine();
            Input input = new Input(next);
            String cmd = input.getCmd();

            if (cmd.equals("mark")) {
                int TaskNo = input.getTaskNumber();
                store.get(TaskNo).markAsDone();
                System.out.println("Task successfully completed!\n" + store.get(TaskNo));
            } else if (cmd.equals("unmark")) {
                int TaskNo = input.getTaskNumber();
                store.get(TaskNo).markAsUndone();
                System.out.println("Task incomplete.\n" + store.get(TaskNo));
            } else if (cmd.equals("bye")) {
                System.out.println("Bye bye! Hope to see you soon!");
                break;
            } else if (cmd.equals("list")) {
                System.out.println("Here are your tasks:");
                for (int i = 0; i < store.size(); i++) {
                    System.out.println(i + 1 + ". " + store.get(i));
                }
            } else if (cmd.equals("todo")) {
                Task newTask = new ToDo(input.getToDoTask());
                store.add(newTask);
                System.out.println("Successfully added new task:\n" + newTask +
                        "\nYou have " + store.size() + " task(s) in the list.");
            } else if (cmd.equals("event")) {
                Task newTask = new Event(input.getEventTask(), input.getEventTime());
                store.add(newTask);
                System.out.println("Successfully added new task:\n" + newTask +
                        "\nYou have " + store.size() + " task(s) in the list.");
            } else if (cmd.equals("deadline")) {
                Task newTask = new Deadline(input.getDeadlineTask(), input.getDeadlineTime());
                store.add(newTask);
                System.out.println("Successfully added new task:\n" + newTask +
                        "\nYou have " + store.size() + " task(s) in the list.");
            } else if (cmd.equals("delete")) {
                int TaskNo = input.getTaskNumber();
                Task removed = store.remove(TaskNo);
                System.out.println("Understood. I have removed this task:\n" + removed +
                        "\nYou have " + store.size() + " task(s) in the list.");
            } else {
                throw new DukeException("Unknown command. Please enter a valid command");
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
