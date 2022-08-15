public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private DukeIO userIO;

    Duke() {
        userIO = new DukeIO();
        userIO.printTask(LOGO);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
    }
}
