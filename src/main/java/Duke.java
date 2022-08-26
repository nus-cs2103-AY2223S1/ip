import java.util.Scanner;


public class Duke {

    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    public static void main(String[] args) {

        ui = new Ui();

        ui.printGreeting();
        storage = new Storage("./src/main/KiwiList.txt");

        taskList = storage.load();

        taskList.printList();

        run(taskList, ui, storage);

    }

    public static void run(TaskList taskList, Ui ui, Storage storage) {

        Parser p = new Parser(taskList, ui);
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("Bye")) {

            p.parse(input);

            if (sc.hasNextLine()) {
                input = sc.nextLine();
            }
        }

        storage.save(taskList);

        ui.printGoodbyeMessage();

    }
}
