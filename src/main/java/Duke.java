import java.util.Scanner;

public class Duke {

    private static TaskList taskArr = new TaskList();
//    private Storage storage;
    private Ui ui;
    String input;


    public Duke(String filePath) {
        ui = new Ui();
        ui.printGreetings();

        try {
            Storage.loadFileData(taskArr);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
    }

    public void run() {
        while (!input.equals("bye")) {
            try {
                Parser.parseInput(input,taskArr);

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            input = ui.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
