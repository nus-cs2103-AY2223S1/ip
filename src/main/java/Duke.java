import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String logo =
            "$$\\                                     $$\\       \n" +
            "$$ |                                    $$ |      \n" +
            "$$ |      $$\\   $$\\  $$$$$$\\   $$$$$$$\\ $$$$$$$\\  \n" +
            "$$ |      $$ |  $$ |$$  __$$\\ $$  _____|$$  __$$\\ \n" +
            "$$ |      $$ |  $$ |$$ |  \\__|$$ /      $$ |  $$ |\n" +
            "$$ |      $$ |  $$ |$$ |      $$ |      $$ |  $$ |\n" +
            "$$$$$$$$\\ \\$$$$$$  |$$ |      \\$$$$$$$\\ $$ |  $$ |\n" +
            "\\________| \\______/ \\__|       \\_______|\\__|  \\__|\n\n";
    private static final char lineBreak = '\n';
    private static final String line = "---------------------------------------------------";
    private static final String indent = "      ";
    private static final String greetingMessage = "Hi, I'm Lurch." + lineBreak + "You rang?";
    private static final String listCommand = "list";
    private static final String exitCommand = "bye";
    private static final String byeMessage = "Have a lovely day with dark and cloudy skies.";
    private final ArrayList<String> taskList = new ArrayList<>();
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
        this.taskList.add(task);
        lurchMessage("added: " + task);
    }

    public void listTasks() {
        String message = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            message += i + 1 + ". " + this.taskList.get(i);
            if (i < this.taskList.size() - 1) message += lineBreak;
        }
        lurchMessage(message);
    }

    public void command(String cmd) {
        if (cmd.equals(exitCommand)) this.terminate();
        else if (cmd.equals(listCommand)) this.listTasks();
        else this.addTask(cmd);
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