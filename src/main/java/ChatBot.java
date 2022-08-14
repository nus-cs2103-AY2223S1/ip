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

    private void handleAdd(String description) {
        this.taskList.add(description);
        this.displayMessage("\t" + "added: " + description + "\n");
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

            Scanner lineCheck = new Scanner(line);
            String check = lineCheck.next();

            if ((check.equals("mark") || check.equals("unmark")) && lineCheck.hasNextInt()) {
                int entry = lineCheck.nextInt();
                if (!lineCheck.hasNext() && this.taskList.inRange(entry)) {
                    if (check.equals("mark")) {
                        this.handleMark(entry);
                    } else {
                        this.handleUnmark(entry);
                    }
                    continue;
                }
            }
            this.handleAdd(line);
        }
        input.close();
    }
}
