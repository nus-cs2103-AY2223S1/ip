import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    // FORMATTING
    private static final String logo =
            "$$\\                                     $$\\\n" +
            "$$ |                                    $$ |\n" +
            "$$ |      $$\\   $$\\  $$$$$$\\   $$$$$$$\\ $$$$$$$\\\n" +
            "$$ |      $$ |  $$ |$$  __$$\\ $$  _____|$$  __$$\\\n" +
            "$$ |      $$ |  $$ |$$ |  \\__|$$ /      $$ |  $$ |\n" +
            "$$ |      $$ |  $$ |$$ |      $$ |      $$ |  $$ |\n" +
            "$$$$$$$$\\ \\$$$$$$  |$$ |      \\$$$$$$$\\ $$ |  $$ |\n" +
            "\\________| \\______/ \\__|       \\_______|\\__|  \\__|\n\n";
    private static final char lineBreak = '\n';
    private static final String line = "---------------------------------------------------";
    private static final String indent = "      ";

    // MESSAGES
    private static final String greetingMessage = "Hi, I'm Lurch." + lineBreak + "You rang?";
    private static final String byeMessage = "Have a lovely day with dark and cloudy skies.";
    private static final String markMessage = "Nice! I've marked this task as done:";
    private static final String unmarkMessage = "OK, I've marked this task as not done yet:";

    // COMMANDS
    private static final String listCommand = "list";
    private static final String exitCommand = "bye";
    private static final String markPattern = "^mark \\d+$";
    private static final String unmarkPattern = "^unmark \\d+$";
    private static final String taskPattern = "(^todo|^event|^deadline)(\\s+)?(.+)?$";

    // INSTANCE VARIABLES
    private final ArrayList<Task> taskList = new ArrayList<>();
    private boolean isTerminated = false;

    public static void lurchMessage(String message) {
        final String indentedLine = indent + line;
        final String indentedMessage = indent +
                message.replace(Character.toString(lineBreak), lineBreak + indent);
        System.out.println(indentedLine + lineBreak + indentedMessage + lineBreak + indentedLine);
    }

    public void greet() {
        System.out.println(logo);
        lurchMessage(greetingMessage);
    }

    public void addTask(String task) {
        Pattern p = Pattern.compile(taskPattern);
        Matcher m = p.matcher(task);
        if (!m.find()) return; // THROW ERROR
        String mode = m.group(1);
        String whitespace = m.group(2); // USE LATER FOR ERROR HANDLING
        String meta = m.group(3);
        Task newTask;
        switch (mode) {
            case "todo": newTask = new Todo(meta); break;
            case "event": newTask = new Event(meta); break;
            case "deadline": newTask = new Deadline(meta); break;
            default: newTask = new Task(meta); break; // HANDLE ERROR HERE
        }
        this.taskList.add(newTask);
        lurchMessage("Got it! I've added this task:"
                + lineBreak
                + indent
                + newTask
                + lineBreak
                + "Now, you have "
                + this.taskList.size()
                + " tasks in the list!"
                );
    }

    public void listTasks() {
        String message = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            message += i + 1 + ". " + this.taskList.get(i);
            if (i < this.taskList.size() - 1) message += lineBreak;
        }
        lurchMessage(message);
    }

    public void markTask(String cmd) {
        final int idx = Integer.parseInt(cmd.replaceAll("[^0-9]", ""));
        Task selectedTask = this.taskList.get(idx - 1);
        selectedTask.mark();
        lurchMessage(markMessage + lineBreak + indent + selectedTask);
    }

    public void unmarkTask(String cmd) {
        final int idx = Integer.parseInt(cmd.replaceAll("[^0-9]", ""));
        Task selectedTask = this.taskList.get(idx - 1);
        selectedTask.unmark();
        lurchMessage(unmarkMessage + lineBreak + indent + selectedTask);
    }

    public void command(String cmd) {
        if (cmd.equals(exitCommand)) this.terminate();
        else if (cmd.equals(listCommand)) this.listTasks();
        else if (Pattern.matches(unmarkPattern, cmd)) unmarkTask(cmd);
        else if (Pattern.matches(markPattern, cmd)) markTask(cmd);
        else if (Pattern.matches(taskPattern, cmd)) addTask(cmd);
        else lurchMessage("Not available.");
    }

    public void terminate() {
        this.isTerminated = true;
        lurchMessage(byeMessage);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke lurch = new Duke();
        lurch.greet();
        while (!lurch.isTerminated) {
            lurch.command(scanner.nextLine());
        }
    }
}