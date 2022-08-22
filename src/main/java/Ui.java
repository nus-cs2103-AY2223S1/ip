public class Ui {
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

    private static final String greetingMessage = "Hi, I'm Lurch." + lineBreak + "You rang?";
    private static final String noTasksMessage = "You have no tasks in the list right now";
    private static final String byeMessage = "Have a lovely day with dark and cloudy skies.";
    private static final String oopsMessage = "Oh bother!";

    private static void message(String msg) {
        final String indentedLine = indent + line;
        final String indentedMessage = indent +
                msg.replace(Character.toString(lineBreak), lineBreak + indent);
        System.out.println(indentedLine + lineBreak + indentedMessage + lineBreak + indentedLine);
    }

    public static void showGreeting() {
        System.out.println(logo);
        message(greetingMessage);
    }

    public static void showTaskStatus(String prefix, Task task) {
        message(prefix + lineBreak + indent + task);
    }

    public static void showTaskStatus(String prefix, Task task, String suffix) {
        message(prefix + lineBreak + indent + task + lineBreak + suffix);
    }

    public static void showTasksList(TaskList taskList) throws DukeException {
        String msg = "";
        if (taskList.getSize() == 0) throw new DukeException(noTasksMessage);
        for (int i = 0; i < taskList.getSize(); i++) {
            msg += i + 1 + ". " + taskList.get(i);
            if (i < taskList.getSize() - 1) msg += lineBreak;
        }
        message(msg);
    }

    public static void showTermination() {
        message(byeMessage);
    }

    public static void showErrorMessage(DukeException exc) {
        message(oopsMessage + lineBreak + exc);
    }
}
