
public class Duke {

    private static Tasks[] tasks = new Tasks[100];

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Command cmd = new GreetCmd("");
        cmd.execute("", tasks);
        Echo start = new Echo();
        start.echo(tasks);
    }
}
