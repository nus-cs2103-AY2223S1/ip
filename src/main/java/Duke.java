import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    private TaskList list;
    private final Save save;

    public Duke(String filePath) {
        this.save = new Save(filePath);
        try {
            this.list = new TaskList(save.load());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            this.list = new TaskList();
        }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I am a ToDos, Events, Deadlines and Talk Bot, otherwise known as TEDTalk\n" +
                "What can I do for you today?");

        boolean terminated = false;

        while (!terminated) {
            try {
                String next = sc.nextLine();
                Input input = new Input(next);
                Command cmd = input.getCommand();
                cmd.execCommand(this.list, this.save);
                terminated = cmd.isTerminated();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.home") + "/data/duke.txt").run();
    }
}
