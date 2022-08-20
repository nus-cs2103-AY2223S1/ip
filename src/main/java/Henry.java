import exceptions.ImproperCommandSyntaxException;
import exceptions.NoSuchCommandException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Henry {

    private List<Task> tasks;
    private final Scanner sc;
    private static final HashMap<String, Commands> language = new HashMap<>();
    private static final String home = System.getProperty("user.home");
    private static final Path FILE_PATH = java.nio.file.Paths.get(home, "Desktop", "henry.txt");
    private boolean isActivated;


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
        sc = new Scanner(System.in);
        isActivated = true;
        output("HELLO. I AM HENRY. HOW MAY I ASSIST YOU TODAY?");
        try {
            File savedList = new File(FILE_PATH.toUri());
            if (savedList.createNewFile()) {
                tasks = new ArrayList<>();
                output("CREATED NEW TASK LIST");
            } else {
                output("LOADED EXISTING TASK LIST");
                tasks = parseTasksFromFile(savedList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Task> parseTasksFromFile(File savedList) throws FileNotFoundException {
        Scanner s = new Scanner(savedList);
        List<Task> tasks = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] tokens = line.split("\\|");
            String description;
            String modifier = "";
            String prefix;
            boolean isComplete;
            Commands type;
            prefix = tokens[0].trim();
            switch (prefix) {
            case "T":
                type = Commands.TODO;
                isComplete = tokens[1].trim().equals("1");
                description = tokens[2].trim();
                break;
            case "D":
                type = Commands.DEADLINE;
                isComplete = tokens[1].trim().equals("1");
                description = tokens[2].trim();
                modifier = tokens[3].trim();
                break;
            default:
                type = Commands.EVENT;
                isComplete = tokens[1].trim().equals("1");
                description = tokens[2].trim();
                modifier = tokens[3].trim();
                break;
            }
            tasks.add(new Task(description, modifier, type, isComplete));
        }
        return tasks;
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
        } catch (IOException e7) {
            output("FILE NOT FOUND");
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

    public void handleAddTask(String command) throws ImproperCommandSyntaxException, IOException {
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

    public void addToList(Task task) throws IOException {
        tasks.add(task);
        appendToFile(task.toSimpleString());
        output("OK. I ADDED THIS TASK TO MY LIST:\n\t\t\t" + task + "\n\t\tNOW YOU HAVE " + tasks.size()
               + (tasks.size() == 1 ? " TASK" : " TASKS") + " IN YOUR LIST.");
    }

    private void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(Henry.FILE_PATH.toFile(), true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    public void getList() throws FileNotFoundException {
        output(formatList());
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void close() {
        output("GOODBYE!");
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

    private String formatResponse(String input) {
        return "____________________________________________________________" + "\n HENRY: "
               + input + "\n" + "____________________________________________________________";
    }
}
