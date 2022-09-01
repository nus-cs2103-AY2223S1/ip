import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Lists the list of commands.
     */
    public static void mismatch() {
        Ui.reply("list of commands: list, mark, unmark, todo, deadline, event, delete");
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        SaveFile.loadSaveFile();
        for (SaveLine i : SaveFile.getFileData()) {
            if (i.getInfoType().equals("todo")) {
                TaskList.taskList.add(new Todo(i));
                continue;
            }
            if (i.getInfoType().equals("deadline")) {
                TaskList.taskList.add(new Deadline(i));
                continue;
            }
            if (i.getInfoType().equals("event")) {
                TaskList.taskList.add(new Event(i));
                continue;
            }
        }
        Ui.reply("What can I do for you?");
        while (true) {
            String line = Ui.readLine();
            String[] arguments = Ui.parse(line);
            if (arguments[0].equals("bye")) {
                Ui.bye();
                break;
            }
            if (arguments[0].equals("list")){
                TaskList.list();
                continue;
            }
            if (arguments[0].equals("mark")) {
                TaskList.mark(arguments);
                continue;
            }
            if (arguments[0].equals("unmark")) {
                TaskList.unmark(arguments);
                continue;
            }
            if (arguments[0].equals("todo")) {
                TaskList.todo(arguments);
                continue;
            }
            if (arguments[0].equals("deadline")) {
                TaskList.deadline(arguments);
                continue;
            }
            if (arguments[0].equals("event")) {
                TaskList.event(arguments);
                continue;
            }
            if (arguments[0].equals("delete")) {
                TaskList.delete(arguments);
                continue;
            }
            mismatch();
        }
    }
}
