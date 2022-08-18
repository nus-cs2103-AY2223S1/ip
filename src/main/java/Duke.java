import java.util.Scanner;

public class Duke {
    private final Scanner s;
    private static final String intro =
            "| Welcome to APOLLO! |\n" +
            "How can I help you today?";
    public static final String divider = "\n--------------------------";
    private final DukeList itemList;

    public Duke() {
        s = new Scanner(System.in);
        itemList = new DukeList();
    }

    void start() {
        System.out.println(intro + divider);
        run();
    }

    void run() {
        try {
            checkInput(s.nextLine());
        } catch (DukeException e) {
            System.out.println(e.getMessage() + divider);
            run();
        }
    }

    void checkInput(String inputString) throws DukeException {
        String[] input = inputString.split(" ");
        String output = "";
        switch (input[0]) {
            case "bye": {
                System.out.println("Goodbye, see you soon!" + divider);
                return;
            } case "list": {
                output = itemList.toString();
                break;
            } case "mark": {
                output = itemList.mark(Integer.parseInt(input[1]));
                break;
            } case "unmark": {
                output = itemList.unmark(Integer.parseInt(input[1]));
                break;
            } case "delete": {
                output = itemList.deleteItem(Integer.parseInt(input[1]));
                break;
            } default: {
                output = itemList.addItem(input);
            }
        }
        System.out.println(output + divider);
        run();
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.start();
    }
}
