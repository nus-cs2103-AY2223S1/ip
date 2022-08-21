import exceptions.ImproperCommandSyntaxException;
import exceptions.NoSuchCommandException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class Henry {

    private final Ui ui;
    private final Storage storage;
    private List<Task> tasks;
    private static final HashMap<String, Commands> language = new HashMap<>();
    private static final String home = System.getProperty("user.home");
    private static final Path FILE_PATH = java.nio.file.Paths.get(home, "Desktop", "henry.txt");
    private boolean isActivated;
    private static final String DATE_FORMATTER = "dd-MM-yyyy HH:mm";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);


    static {
        language.put("echo", Commands.ECHO);
        language.put("list", Commands.LIST);
        language.put("bye", Commands.BYE);
        language.put("todo", Commands.TODO);
        language.put("deadline", Commands.DEADLINE);
        language.put("event", Commands.EVENT);
        language.put("mark", Commands.MARK);
        language.put("unmark", Commands.UNMARK);
        language.put("delete", Commands.DELETE);
    }

    public Henry() {
        ui = new Ui();
        storage = new Storage(FILE_PATH.toString());
        isActivated = true;
        tasks = storage.load();
    }

    public boolean isActivated() {
        return isActivated;
    }

    // Command handling
    public void parseCommand(String command) {
        try {
            String parse = command.contains(" ") ? command.split(" ")[0] : command;
            parse = parse.toLowerCase();
            String modifiers = command.contains(" ") ? command.split(" ")[1] : "";
            if (language.containsKey(parse)) {
                Commands cmd = language.get(parse);
                switch (cmd) {
                case ECHO:
                    echo(modifiers);
                    break;
                case LIST:
                    getList();
                    break;
                case BYE:
                    close();
                    break;
                case MARK:
                    markTask(command);
                    break;
                case UNMARK:
                    unmarkTask(command);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    handleAddTask(command);
                    break;
                case DELETE:
                    deleteTask(command);
                    break;
                default:
                    help();
                    break;
                }
            } else {
                throw new NoSuchCommandException();
            }
        } catch (NoSuchCommandException e1) {
            System.out.println(NoSuchCommandException.ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException | NumberFormatException e5) {
            throw new ImproperCommandSyntaxException();
        } catch (IOException e7) {
            ui.output("FILE NOT FOUND");
        } catch (ImproperCommandSyntaxException e3) {
            System.out.println(ImproperCommandSyntaxException.ERROR_MESSAGE);
        }
    }

    private void echo(String input) {
        ui.output(input);
    }

    private void help() {

    }

    private void deleteTask(String command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command.split(" ")[1]);
        String removed = tasks.remove(index).toString();
        ui.output("NOTED. I REMOVED THIS TASK:\n\t\t\t" + removed + "\n\t\tNOW YOU HAVE " + tasks.size()
                  + (tasks.size() == 1 ? " TASK" : " TASKS") + " IN YOUR LIST.");
    }

    private void markTask(String command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command.split(" ")[1]);
        tasks.get(index).setComplete(true);
        ui.output("I'VE MARKED THIS TASK AS DONE:\n\t\t\t" + tasks.get(index));
    }

    private void unmarkTask(String command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command.split(" ")[1]);
        tasks.get(index).setComplete(false);
        ui.output("I'VE MARKED THIS TASK AS NOT DONE: \n" + tasks.get(index));
    }

    private void handleAddTask(String command) throws ImproperCommandSyntaxException, IOException {
        Task task;
        if (command.matches("deadline (.+) /at (\\d){1,3}-(\\d){1,3}-(\\d){4} (\\d){2}:(\\d){2}")) {
            int indexSlash = command.indexOf('/');
            String description = command.substring(0, indexSlash).replace("deadline", "").trim();
            String modifier = command.substring(indexSlash + 1).replace("by", "").trim();
            LocalDateTime parsed = LocalDateTime.parse(modifier, formatter);
            task = new Task(Commands.DEADLINE, description, parsed);
        } else if (command.matches("event (.+) /at (\\d){1,3}-(\\d){1,3}-(\\d){4} (\\d){2}:(\\d){2}")) {
            int indexSlash = command.indexOf('/');
            String description = command.substring(0, indexSlash).replace("event", "").trim();
            String modifier = command.substring(indexSlash + 1).replace("at", "").trim();
            LocalDateTime parsed = LocalDateTime.parse(modifier, formatter);
            task = new Task(Commands.EVENT, description, parsed);
        } else if (command.matches("todo (.+)")) {
            String description = command.replace("todo", "").trim();
            task = new Task(Commands.TODO, description, null);
        } else {
            throw new ImproperCommandSyntaxException();
        }
        addToList(task);
    }

    public void addToList(Task task) {
        tasks.add(task);
        ui.output("OK. I ADDED THIS TASK TO MY LIST:\n\t\t\t" + task + "\n\t\tNOW YOU HAVE " + tasks.size()
                  + (tasks.size() == 1 ? " TASK" : " TASKS") + " IN YOUR LIST.");
    }

    public void getList() throws FileNotFoundException {
        ui.output(formatList());
    }

    public String getInput() {
        return ui.getInput();
    }

    public void close() throws IOException {
        ui.close();
        storage.close(tasks);
        isActivated = false;
    }

    private String formatList() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(" ").append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        return sb.toString();
    }
}
