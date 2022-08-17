import java.util.Scanner;

public class UwuBot {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        UwuChat.greetingUsers();

        while(true) {
            String userCommand = scanner.nextLine();

            if (userCommand.equals("bye")) {
                UwuChat.leavingChat();
                break;
            } else {
                UwuChat.userCommands(userCommand);
            }
        }
    }
}
