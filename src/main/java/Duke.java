import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm SoCCat \nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        String currInput = input.nextLine();
        String[] userInputs = new String[100];
        int index = 0;

        while (!currInput.equals("bye")) {
            if (currInput.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(i + ". " + userInputs[i]);
                }
            } else {
                System.out.println("added: " + currInput);
                userInputs[index] = currInput;
                index++;
            }
                currInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
