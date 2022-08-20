import exceptions.ImproperCommandSyntaxException;
import exceptions.NoSuchCommandException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Henry {

    private final List<Task> tasks;
    private final Scanner sc;
    private static final HashMap<String, Commands> language = new HashMap<>();
    private boolean activated;

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
        System.out.println(
            "  _    _ ______ _   _ _______     __\n"
            + " | |  | |  ____| \\ | |  __ \\ \\   / /\n"
            + " | |__| | |__  |  \\| | |__) \\ \\_/ /\n"
            + " |  __  |  __| | . ` |  _  / \\   /\n"
            + " | |  | | |____| |\\  | | \\ \\  | |\n"
            + " |_|  |_|______|_| \\_|_|  \\_\\ |_|");
        tasks = new ArrayList<>();
        sc = new Scanner(System.in);
        activated = true;
        output("HELLO. I AM HENRY. HOW MAY I ASSIST YOU TODAY?");
    }

    public boolean isActivated() {
        return activated;
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
                default:
                    deleteTask(command);
                    break;
                }
            } else {
                throw new NoSuchCommandException();
            }
        } catch (NoSuchCommandException e1) {
            System.out.println(NoSuchCommandException.ERROR_MESSAGE);
        } catch (ImproperCommandSyntaxException e3) {
            switch (e3.getErrorType()) {
            case 0:
                System.out.println(ImproperCommandSyntaxException.ERROR_MESSAGE);
                break;
            case 1:
                System.out.println(ImproperCommandSyntaxException.ERROR_MESSAGE_NO_BY);
                break;
            default:
                System.out.println(ImproperCommandSyntaxException.ERROR_MESSAGE_NO_AT);
                break;
            }
        } catch (IndexOutOfBoundsException e5) {
            output("NO SUCH TASK");
        } catch (NumberFormatException e6) {
            output("INVALID TASK INDEX. PLEASE ENTER A NUMBER");
        }
    }

    public void output(String message) {
        System.out.println(formatResponse(message));
    }

    public void echo(String input) {
        System.out.println(formatResponse(input));
    }

    public void deleteTask(String command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command.split(" ")[1]);
        String removed = tasks.remove(index).toString();
        output("NOTED. I REMOVED THIS TASK:\n\t\t\t" + removed + "\n\t\tNOW YOU HAVE " + tasks.size()
               + (tasks.size() == 1 ? " TASK" : " TASKS") + " IN YOUR LIST.");
    }

    public void markTask(String command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command.split(" ")[1]);
        tasks.get(index).setComplete(true);
        output("I'VE MARKED THIS TASK AS DONE:\n\t\t\t" + tasks.get(index));
    }

    public void unmarkTask(String command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command.split(" ")[1]);
        tasks.get(index).setComplete(false);
        output("I'VE MARKED THIS TASK AS NOT DONE: \n" + tasks.get(index));
    }

    public void handleAddTask(String command) throws ImproperCommandSyntaxException {
        Task task;
        if (command.matches("deadline (.+) /by (.+)")) {
            int indexSlash = command.indexOf('/');
            String description = command.substring(0, indexSlash).replace("deadline", "").trim();
            String modifier = command.substring(indexSlash + 1).replace("by", "").trim();
            task = new Task(description, modifier, Commands.DEADLINE);
        } else if (command.matches("event (.+) /at (.+)")) {
            int indexSlash = command.indexOf('/');
            String description = command.substring(0, indexSlash).replace("event", "").trim();
            String modifier = command.substring(indexSlash + 1).replace("at", "").trim();
            task = new Task(description, modifier, Commands.EVENT);
        } else if (command.matches("todo (.+)")) {
            String description = command.replace("todo", "").trim();
            task = new Task(description, "", Commands.TODO);
        } else {
            throw new ImproperCommandSyntaxException();
        }
        addToList(task);
    }

    public void addToList(Task task) {
        tasks.add(task);
        output("OK. I ADDED THIS TASK TO MY LIST:\n\t\t\t" + task.toString() + "\n\t\tNOW YOU HAVE " + tasks.size()
               + (tasks.size() == 1 ? " TASK" : " TASKS") + " IN YOUR LIST.");
    }

    public void getList() {
        System.out.println(formatList());
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void close() {
        output("GOODBYE!");
        activated = false;
    }

    private String formatList() {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________");
        sb.append("\n HENRY:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(" ").append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        sb.append("____________________________________________________________");
        return sb.toString();
    }

    private String formatResponse(String input) {
        return "____________________________________________________________" + "\n HENRY: " + input + "\n" + "____________________________________________________________";
    }
}
