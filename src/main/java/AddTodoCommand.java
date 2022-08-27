import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddTodoCommand extends AddCommand {
    private AddTodoCommand(String command, Todo todo) {
        super(command, todo);
    }

    public static AddTodoCommand of(String command) throws IllegalArgumentException {
        boolean isDone = command.contains("/done");
        if (isDone) {
            command = command.replace("/done", "");
        }

        String text = command.replaceFirst("todo", "").strip();

        if (text.isEmpty()) {
            throw new IllegalArgumentException("🙁 OOPS!!! The description of a todo cannot be empty.\n");
        } else {
            return new AddTodoCommand(command, new Todo(isDone, text));
        }
    }
}
