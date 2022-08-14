import java.util.Scanner;

public class ChatBot {
    private final TaskList taskList = new TaskList();

    private void displayMessage(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.print(message);
        System.out.println("\t____________________________________________________________");
    }

    private void handleGreet() {
        String logo =
                  "\t" + " ,-----.,--.               ,--.    ,--.                  " + "\n"
                + "\t" + "'  .--./|  ,---.  ,--,--.,-'  '-.,-'  '-.,--.,--. ,---.  " + "\n"
                + "\t" + "|  |    |  .-.  |' ,-.  |'-.  .-''-.  .-'|  ||  |(  .-'  " + "\n"
                + "\t" + "'  '--'\\|  | |  |\\ '-'  |  |  |    |  |  '  ''  '.-'  `) " + "\n"
                + "\t" + " `-----'`--' `--' `--`--'  `--'    `--'   `----' `----'  " + "\n";
        String message = "\n\t" + "Hello! My name is Chattus" + "."
                       + "\n\t" + "What can I do for you? :)" + "\n";
        this.displayMessage(logo + message);
    }

    private void handleBye() {
        String message = "\t" + "Bye! Till we next meet!" + "\n";
        this.displayMessage(message);
    }

    private void handleAddTask(Task task) {
        this.taskList.add(task);
        this.displayMessage("\t" + "Got it. I've added this task:" + "\n\t\t" + task + "\n"
                        + "\t" + "Now you have " + this.taskList.size() + " tasks in the list." + "\n");
    }

    private void handleList() {
        this.displayMessage("\t" + "Here are the tasks in your list:" + "\n" + this.taskList);
    }

    private void handleMark(int entry) {
        Task task = this.taskList.get(entry);
        this.taskList.markTask(entry);
        this.displayMessage("\t" + "Great! I've marked this task." + "\n\t\t" + task + "\n");
    }

    private void handleUnmark(int entry) {
        Task task = this.taskList.get(entry);
        this.taskList.unmarkTask(entry);
        this.displayMessage("\t" + "Ok, I've unmarked this task." + "\n\t\t" + task + "\n");
    }

    public void start() {
        this.handleGreet();

        String line;
        Scanner input = new Scanner(System.in);

        while(true) {
            line = input.nextLine();
            if (line.equals("bye")) {
                this.handleBye();
                break;
            } else if (line.equals("list")) {
                this.handleList();
                continue;
            }

            Scanner parser = new Scanner(line);
            String check = parser.next();

            if ((check.equals("mark") || check.equals("unmark")) && parser.hasNextInt()) {
                int entry = parser.nextInt();
                if (!parser.hasNext() && this.taskList.inRange(entry)) {
                    if (check.equals("mark")) {
                        this.handleMark(entry);
                    } else {
                        this.handleUnmark(entry);
                    }
                    continue;
                }
            } else if (check.equals("todo") && parser.hasNext()) {
                Task task = new ToDo(parser.nextLine());
                this.handleAddTask(task);
            } else if (check.equals("deadline") && parser.hasNext()) {
                parser.useDelimiter("/by");
                if (parser.hasNext()) {
                    String description = parser.next();
                    if (parser.hasNext()) {
                        Task task = new Deadline(description, parser.next());
                        this.handleAddTask(task);
                    }
                }
            } else if (check.equals("event") && parser.hasNext()) {
                parser.useDelimiter("/at");
                if (parser.hasNext()) {
                    String description = parser.next();
                    if (parser.hasNext()) {
                        Task task = new Event(description, parser.next());
                        this.handleAddTask(task);
                    }
                }
            }
        }
        input.close();
    }
}
