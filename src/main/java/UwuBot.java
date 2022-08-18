import java.util.Scanner;

public class UwuBot {
    private static Scanner scanner = new Scanner(System.in);
    private static UwuChat chat = new UwuChat();

    public static void main(String[] args) {

        chat.greetingUsers();

        while(true) {
            String userCommand = scanner.nextLine();

            if (userCommand.equals("bye")) {
                chat.leavingChat();
                break;
            } else {
                chat.userCommands(userCommand);
            }
        }
    }
}
