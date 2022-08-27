import java.util.Scanner;

public class UwuBot {
    private static UwuChat chat = new UwuChat(new TaskList());

    public static void run() throws UwuException {
        chat.greetUsers();

        UwuHandler handler = new UwuHandler("data/taskList.txt");
        Scanner userInput = new Scanner(System.in);

        while (userInput.hasNextLine()) {
            String input = userInput.nextLine().toLowerCase();
            handler.handleResponse(input);
        }
    }

    public static void main(String[] args) {
        try {
            UwuBot.run();
        } catch (UwuException e){
            System.out.println(e.getMessage());
        }
    }
}
