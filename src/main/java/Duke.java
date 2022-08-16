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
    private static final String exitCommand = "bye";
    private static final String byeMessage = "Have a lovely day with dark and cloudy skies.";

    public static void lurchMessage(String message) {
        final String indentedLine = indent + line;
        final String indentedMessage = indent +
                message.replace(Character.toString(lineBreak), lineBreak + indent);
        System.out.println(indentedLine + lineBreak + indentedMessage + lineBreak + indentedLine);
    }

    public static void main(String[] args) {
        String command = "";
        System.out.println(logo);
        lurchMessage(greetingMessage);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            command = scanner.nextLine();
            if (command.equals(exitCommand)) break;
            lurchMessage(command);
        }
        lurchMessage(byeMessage);
    }
}