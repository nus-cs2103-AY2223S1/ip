import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    Scanner scanner = new Scanner(System.in);
    int index = 0;
    String[] inputArray = new String[100];
    Duke() {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        try {
            while (true) {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    Bye();
                    break;
                } else if (input.equals("")) {
                    continue;
                } else if(input.equals("list")) {
                    List();
                } else {
                    addList(input);
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Exiting");
        }
    }

    private void Bye(){
        System.out.println("Bye. Please chat with me again!");
    }

    private void List(){
        for (int i = 0; i < index; i++) {
            System.out.println(i + 1 + ": " + inputArray[i]);
        }
    }

    private void addList(String input) {
        inputArray[index] = input;
        index++;
        System.out.println("added: " + input);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
    }
}
