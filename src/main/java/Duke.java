import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) throws DukeException {
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
