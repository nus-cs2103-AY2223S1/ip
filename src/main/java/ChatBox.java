import java.util.Scanner;

public class ChatBox {
    public ChatBox() {
    }

    public void Reply() {
        String EndLoop = "bye";

        while (true) {
            Scanner reader = new Scanner(System.in);
            String UserInput = reader.next();

            if (UserInput.equals(EndLoop)) {
                System.out.println("__________________________________________________");
                System.out.println("Adios Amigo! See you soon!");
                System.out.println("__________________________________________________");
                break;
            }

            System.out.println("__________________________________________________");
            System.out.println(UserInput);
            System.out.println("__________________________________________________");
        }

    }
}
