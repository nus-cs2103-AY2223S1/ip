import java.util.Scanner;

public class ChatBox {
    private String[] chat_memory = new String[100];
    private int array_index = 0;

    public ChatBox() {
    }

    public void Reply() {
        String EndLoop = "bye";
        String ListTasks = "list";

        while (true) {
            Scanner reader = new Scanner(System.in);
            String UserInput = reader.nextLine();

            if (UserInput.equals(EndLoop)) {
                System.out.println("__________________________________________________");
                System.out.println("Adios Amigo! See you soon!");
                System.out.println("__________________________________________________");
                break;
            }

            if (UserInput.equals(ListTasks)) {
                System.out.println("__________________________________________________");
                for (int i = 0; i < array_index; i++) {
                    System.out.printf("%d. " + chat_memory[i] + "\n", i + 1);
                }
                System.out.println("__________________________________________________");
                continue;
            }

            System.out.println("__________________________________________________");
            System.out.println("added: " + UserInput);
            System.out.println("__________________________________________________");
            chat_memory[array_index] = UserInput;
            array_index++;
        }

    }
}
