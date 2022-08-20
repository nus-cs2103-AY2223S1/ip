import exceptions.ImproperCommandSyntaxException;
import exceptions.NoDescriptionException;
import exceptions.NoSuchCommandException;
import exceptions.NoTaskIndexGivenException;

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
            " .----------------.  .----------------.  .-----------------. .----------------.  .----------------.\n" +
            "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
            "| |  ____  ____  | || |  _________   | || | ____  _____  | || |  _______     | || |  ____  ____  | |\n" +
            "| | |_   ||   _| | || | |_   ___  |  | || ||_   \\|_   _| | || | |_   __ \\    | || | |_  _||_  _| | |\n" +
            "| |   | |__| |   | || |   | |_  \\_|  | || |  |   \\ | |   | || |   | |__) |   | || |   \\ \\  / /   | |\n" +
            "| |   |  __  |   | || |   |  _|  _   | || |  | |\\ \\| |   | || |   |  __ /    | || |    \\ \\/ /    | |\n" +
            "| |  _| |  | |_  | || |  _| |___/ |  | || | _| |_\\   |_  | || |  _| |  \\ \\_  | || |    _|  |_    | |\n" +
            "| | |____||____| | || | |_________|  | || ||_____|\\____| | || | |____| |___| | || |   |______|   | |\n" +
            "| |              | || |              | || |              | || |              | || |              | |\n" +
            "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
            " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'");
        tasks = new ArrayList<>();
        sc = new Scanner(System.in);
        activated = true;
        output("HELLO. I AM HENRY. HOW MAY I ASSIST YOU TODAY?");
    }

    // Command handling
    public void parseCommand(String command) {
        try {
            String parse = command.contains(" ") ? command.split(" ")[0] : command;
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
        } catch (NoDescriptionException e2) {
            System.out.println(NoDescriptionException.ERROR_MESSAGE);
        } catch (ImproperCommandSyntaxException e3) {
            switch (e3.getErrorType()) {
                case 0:
                    System.out.println(
                        ImproperCommandSyntaxException.ERROR_MESSAGE);
                    break;
                case 1:
                    System.out.println(
                        ImproperCommandSyntaxException.ERROR_MESSAGE_NO_BY);
                    break;
                default:
                    System.out.println(
                        ImproperCommandSyntaxException.ERROR_MESSAGE_NO_AT);
                    break;
            }
        } catch (NoTaskIndexGivenException e4) {
            System.out.println(NoTaskIndexGivenException.ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e5) {
            output("NO SUCH TASK");
        }
    }

    public void output(String message) {
        System.out.println(formatResponse(message));
    }

    public boolean isActivated() {
        return activated;
    }

    public void close() {
        output("GOODBYE!");
        activated = false;
    }

    public void echo(String input) {
        System.out.println(formatResponse(input));
    }

    public void deleteTask(String command) throws NoTaskIndexGivenException,
                                                  IndexOutOfBoundsException {
        int index;
        if (command.split(" ").length <= 1) {
            throw new NoTaskIndexGivenException();
        } else {
            index = Integer.parseInt(command.split(" ")[1]);
        }
        String removed = tasks.remove(index).toString();
        output("NOTED. I REMOVED THIS TASK:\n\t\t\t" + removed +
               "\n\t    NOW YOU HAVE " + tasks.size() + " TASK" +
               (tasks.size() == 1 ? "" : "S") + " IN YOUR LIST.");
    }

    public void markTask(String command) throws NoTaskIndexGivenException,
                                                IndexOutOfBoundsException {
        int index;
        if (command.split(" ").length <= 1) {
            throw new NoTaskIndexGivenException();
        } else {
            index = Integer.parseInt(command.split(" ")[1]);
        }
        tasks.get(index).setComplete(true);
        output("I'VE MARKED THIS TASK AS DONE:\n\t\t\t" + tasks.get(index));
    }

    public void unmarkTask(String command) throws NoTaskIndexGivenException,
                                                  IndexOutOfBoundsException {
        int index;
        if (command.split(" ").length <= 1) {
            throw new NoTaskIndexGivenException();
        } else {
            index = Integer.parseInt(command.split(" ")[1]);
        }
        tasks.get(index).setComplete(false);
        output("I'VE MARKED THIS TASK AS NOT DONE: \n" + tasks.get(index));
    }

    public void handleAddTask(String command) {
        Task task;
        if (command.startsWith("deadline") || command.startsWith("event")) {
            int indexSlash = command.indexOf('/');
            if (indexSlash == -1 ||
                (!command.startsWith("at", indexSlash + 1) &&
                 !command.startsWith("by", indexSlash + 1))) {
                throw new ImproperCommandSyntaxException();
            } else {
                String taskDescription;
                if (command.substring(0, indexSlash).split("deadline ").length <
                    1 ||
                    command.substring(0, indexSlash).replace("deadline", "")
                           .isBlank()) {
                    throw new NoDescriptionException();
                } else {
                    taskDescription = command.substring(0, indexSlash);
                }
                if (command.startsWith("deadline")) {
                    if (!command.contains("by")) {
                        throw new ImproperCommandSyntaxException(1);
                    } else {
                        String taskDeadline =
                            command.substring(indexSlash + 1).replace("by", "")
                                   .trim();
                        task = new Task(
                            taskDescription.replace("deadline", "").trim(),
                            taskDeadline, Commands.DEADLINE);
                    }
                } else {
                    if (!command.contains("at")) {
                        throw new ImproperCommandSyntaxException(2);
                    } else {
                        String taskTime =
                            command.substring(indexSlash + 1).replace("at", "")
                                   .trim();
                        task = new Task(
                            taskDescription.replace("event", "").trim(),
                            taskTime, Commands.EVENT);
                    }
                }
            }
        } else {
            if (command.split("todo").length < 1 ||
                command.replace("todo", "").isBlank()) {
                throw new NoDescriptionException();
            } else {
                String taskDescription = command.split("todo")[1];
                task = new Task(taskDescription, "", Commands.TODO);
            }
        }
        addToList(task);
    }

    public void addToList(Task task) {
        tasks.add(task);
        output("OK. I ADDED THIS TASK TO MY LIST:\n\t\t\t" + task.toString() +
               "\n\t    NOW YOU HAVE " + tasks.size() + " TASK" +
               (tasks.size() == 1 ? "" : "S") + " IN YOUR LIST.");
    }

    public void getList() {
        System.out.println(formatList());
    }

    public String getInput() {
        return sc.nextLine();
    }

    private String formatList() {
        StringBuilder sb = new StringBuilder();
        sb.append(
            "____________________________________________________________");
        sb.append("\n HENRY:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(" ").append(i).append(". ").append(tasks.get(i - 1))
              .append("\n");
        }
        sb.append(
            "____________________________________________________________");
        return sb.toString();
    }

    private String formatResponse(String input) {
        return "____________________________________________________________" +
               "\n HENRY: " + input + "\n" +
               "____________________________________________________________";
    }
}
