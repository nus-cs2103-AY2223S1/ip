public class Duke {
    private Belly belly; //storage
    private Brain brain; //task
    private TobTob tobTob;
    private Parser parser;
    private Executor executor;

    private Duke() {
        tobTob = new TobTob();
        belly = new Belly();
        try {
            brain = new Brain(belly.puke());
        } catch (DukeException e) {
            tobTob.tobTobNeverMetBefore();
            brain = new Brain();
        }
        executor = new Executor(brain, belly);
        parser = new Parser(executor);
    }

    public void run() {
        tobTob.tobTobGreets();

        while (true) {
            try {
                String input = tobTob.readInput();
                String output = parser.parse(input);

                tobTob.printTobTobBoundary();
                tobTob.printTobTobIndent(output);
                tobTob.printTobTobBoundary();

                if (input.toLowerCase().equals("bye")) {
                    break;
                }
            } catch (DukeException e) {
                tobTob.printTobTobBoundary();
                tobTob.printTobTobIndent(e.getMessage());
                tobTob.printTobTobBoundary();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}