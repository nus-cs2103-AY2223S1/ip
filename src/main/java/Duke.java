import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    Scanner scanner = new Scanner(System.in);
    int index = 0;
    Task[] inputArray = new Task[100];

    Duke() {
        Greet();
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    Bye();
                    break;
                } else if (input.equals("")) {
                    continue;
                } else if (input.equals("list")) {
                    List();
                } else if (input.startsWith("mark")) {
                    markTask(input);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input);
                } else if (input.startsWith("todo")) {
                    addTodo(input);
                } else if (input.startsWith("deadline")) {
                    addDeadlines(input);
                } else if (input.startsWith("event")) {
                    addEvents(input);
                } else {
                    continue;
                }
                reportNum();
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                System.out.println("Please input correctly");
            }
        }
    }

    private void reportNum() {
        System.out.println("Now you have " + index + " in the list.");
    }

    private void Greet() {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    private void Bye(){
        System.out.println("Bye. Please chat with me again!");
    }

    private void List(){
        for (int i = 0; i < index; i++) {
            System.out.println(i + 1 + ": " + inputArray[i]);
        }
    }

    private void addTodo(String input) {
        inputArray[index] = new Todo(input.substring(5));
        System.out.println("Got it. I've added this task:\n" + inputArray[index]);
        index++;
    }

    private void addEvents(String input) {
        int slashIndex = input.indexOf("/");
        inputArray[index] = new Events(input.substring(6, slashIndex), input.substring(slashIndex + 4));
        System.out.println("Got it. I've added this task:\n" + inputArray[index]);
        index++;
    }

    private void addDeadlines(String input) {
        int slashIndex = input.indexOf("/");
        inputArray[index] = new Deadlines(input.substring(9, slashIndex), input.substring(slashIndex + 4));
        System.out.println("Got it. I've added this task:\n" + inputArray[index]);
        index++;
    }

    private void markTask(String input) throws NullPointerException, ArrayIndexOutOfBoundsException, NumberFormatException {
        int markIndex = Integer.parseInt(input.substring(5));
        inputArray[markIndex - 1].done();
        System.out.println("Nice! I've marked this task as done liao!:\n"
                + inputArray[markIndex - 1]);
    }

    private void unmarkTask(String input) throws NullPointerException, ArrayIndexOutOfBoundsException , NumberFormatException{
        int markIndex = Integer.parseInt(input.substring(7));
        inputArray[markIndex - 1].unDone();
        System.out.println("OK, I unmark this task as not done le:\n"
                + inputArray[markIndex - 1]);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
    }
}
