import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    Scanner scanner = new Scanner(System.in);
    int index = 0;
    ArrayList<Task> inputArray = new ArrayList<>();

    Duke() {
        Greet();
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    Bye();
                    break;
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
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else {
                    System.out.println("Sorry, I do not accept that as a task!");
                    continue;
                }
                reportNum();
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException |
                    NumberFormatException | NullPointerException e) {
                System.out.println("Please input correctly");
            }
        }
    }

    private void reportNum() {
        System.out.println("Now you have " + index + " in the list.");
    }

    private void Greet() {
        System.out.println("Hello! I'm BotChat123 \nWhat can I do for you?");
    }

    private void Bye(){
        System.out.println("Bye. Please chat with me again!");
    }

    private void List(){
        for (int i = 0; i < index; i++) {
            System.out.println(i + 1 + ": " + inputArray.get(i));
        }
    }

    private void addTodo(String input) {
        try {
            if (input.length() > 5) {
                inputArray.add(index, new Todo(input.substring(5)));
                System.out.println("Got it. I've added this task:\n" + inputArray.get(index));
                index++;
            } else {
                System.out.println("Please fill in what you want on the Todo!");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please fill in what you want on the Todo!");
        }
    }

    private void addEvents(String input) {
        try {
            if (input.length() > 6) {
                int slashIndex = input.indexOf("/");
                inputArray.add(index, new Events(input.substring(6, slashIndex), input.substring(slashIndex + 4)));
                System.out.println("Got it. I've added this task:\n" + inputArray.get(index));
                index++;
            } else {
                System.out.println("Please fill in what you want on the Events!");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please fill in what you want on the Events!");
        }
    }

    private void addDeadlines(String input) {
        try {
            if (input.length() > 9) {
                int slashIndex = input.indexOf("/");
                inputArray.add(index, new Deadlines(input.substring(9, slashIndex), input.substring(slashIndex + 4)));
                System.out.println("Got it. I've added this task:\n" + inputArray.get(index));
                index++;
            } else {
                System.out.println("Please fill in what you want on the Deadlines!");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please fill in what you want on the Deadlines!");
        }
    }

    private void markTask(String input) throws NullPointerException, ArrayIndexOutOfBoundsException,
            NumberFormatException {
        int markIndex = Integer.parseInt(input.substring(5));
        System.out.println("Nice! I've marked this task as done liao!:\n"
                + inputArray.get(markIndex - 1));
        inputArray.get(markIndex -1).done();
    }

    private void unmarkTask(String input) throws NullPointerException, ArrayIndexOutOfBoundsException,
            NumberFormatException {
        int markIndex = Integer.parseInt(input.substring(7));
        System.out.println("OK, I unmark this task as not done le:\n"
                + inputArray.get(markIndex - 1));
        inputArray.get(markIndex - 1).unDone();
    }

    private void deleteTask(String input) throws NullPointerException, ArrayIndexOutOfBoundsException , NumberFormatException {
        int markIndex = Integer.parseInt(input.substring(7));
        System.out.println("Swee! Task removed: " + inputArray.get(markIndex - 1));
        inputArray.remove(markIndex - 1);
        index--;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
    }
}
