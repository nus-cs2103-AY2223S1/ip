package duke;

public class Duke {
    private Scan scan;

    public Duke() {
        this.scan = new Scan();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();

    }
    public void start() {
        scan.Greet();
        scan.readInput();
    }
}
