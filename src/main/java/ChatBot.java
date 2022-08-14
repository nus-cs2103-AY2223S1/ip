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

    private void handleAddTask(String taskType, Scanner parser) {
        Task task = null;
        if (taskType.equals("todo")) {
            task = new ToDo(" " + parser.next());
        } else if (taskType.equals("deadline")) {
            parser.useDelimiter("/by");
            String description = parser.next();
            if (parser.hasNext()) {
                task = new Deadline(description, parser.next());
            } else {
                this.handleUnexpected();
            }
        } else if (taskType.equals("event")) {
            parser.useDelimiter("/at");
            String description = parser.next();
            if (parser.hasNext()) {
                task = new Event(description, parser.next());
            } else {
                this.handleUnexpected();
            }
        } else {
            this.handleUnexpected();
        }

        if (task != null) {
            this.taskList.add(task);
            this.displayMessage("\t" + "Got it. I've added this task:" + "\n\t\t" + task + "\n"
                    + "\t" + "Now you have " + this.taskList.size() + " tasks in the list." + "\n");
        }
    }

    private void handleList() {
        this.displayMessage("\t" + "Here are the tasks in your list:" + "\n" + this.taskList);
    }

    private void handleMark(Scanner parser) {
        int entry = parser.nextInt();
        if (!parser.hasNext() && this.taskList.inRange(entry)) {
            Task task = this.taskList.get(entry);
            this.taskList.markTask(entry);
            this.displayMessage("\t" + "Great! I've marked this task." + "\n\t\t" + task + "\n");
        }
    }

    private void handleUnmark(Scanner parser) {
        int entry = parser.nextInt();
        if (!parser.hasNext() && this.taskList.inRange(entry)) {
            Task task = this.taskList.get(entry);
            this.taskList.unmarkTask(entry);
            this.displayMessage("\t" + "Ok, I've unmarked this task." + "\n\t\t" + task + "\n");
        }
    }

    private void handleUnexpected() {
        this.displayMessage("\t" + "Seems like you've entered something incorrectly, try again!" + "\n");
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

            if (check.equals("mark") && parser.hasNextInt()) {
                this.handleMark(parser);
            } else if (check.equals("unmark") && parser.hasNextInt()) {
                this.handleUnmark(parser);
            } else if ((check.equals("todo") || check.equals("deadline")
                    || check.equals("event")) && parser.hasNext()) {
                this.handleAddTask(check, parser);
            } else {
                this.handleUnexpected();
            }
        }
        input.close();
    }
}
