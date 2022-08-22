import java.util.Scanner;

public class Duke {

    private TaskList list;

    public Duke() {
        this.list = new TaskList();
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
                cmd.execCommand(this.list);
                terminated = cmd.isTerminated();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
