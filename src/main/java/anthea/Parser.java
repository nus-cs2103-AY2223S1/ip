package anthea;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles gathering commands.
 */
public class Parser {

    /**
     * Lists objects in a String[].
     *
     * @param response String that describes the objects.
     * @param objects List of objects.
     * @param <T> Type of objects.
     * @return String[] of objects.
     */
    public static <T> String[] listObjects(String response, List<T> objects) {
        assert response != null;
        assert objects != null;
        String[] output = new String[objects.size() + 1];
        output[0] = response;
        for (int i = 0; i < objects.size(); i++) {
            assert objects.get(i) != null;
            output[i + 1] = (i + 1) + "." + objects.get(i).toString();
        }
        return output;
    }

    /**
     * Lists numbered objects in a String[].
     *
     * @param response String that describes the objects.
     * @param objects List of numbered objects.
     * @param <T> Type of objects.
     * @return String[] of objects.
     */
    public static <T> String[] listNumberedObjects(String response, List<Pair<Integer, T>> objects) {
        assert response != null;
        assert objects != null;
        String[] output = new String[objects.size() + 1];
        output[0] = response;
        for (int i = 0; i < objects.size(); i++) {
            Pair<Integer, T> pair = objects.get(i);
            assert pair != null;
            assert pair.getFirst() != null;
            assert pair.getSecond() != null;
            output[i + 1] = (pair.getFirst() + 1) + "." + pair.getSecond().toString();
        }
        return output;
    }

    /**
     * Gets the chatbot commands in an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> getCommands() {
        ArrayList<CommandMatcher> commands = new ArrayList<>();

        // This has to be before since the commands are more specific.
        commands = addNoteCommands(commands);

        commands = addTaskCommands(commands);
        commands = addTaskModificationCommands(commands);
        commands = addTaskViewingCommands(commands);

        commands = addCatchAllCommand(commands);

        return commands;
    }

    /**
     * Adds adding task commands to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addTaskCommands(ArrayList<CommandMatcher> commands) {
        assert commands != null;
        commands.add(Commands.getAddDeadlineCommand());
        commands.add(Commands.getAddEventCommand());
        commands.add(Commands.getAddToDoCommand());
        return commands;
    }

    /**
     * Adds task modification commands to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addTaskModificationCommands(ArrayList<CommandMatcher> commands) {
        assert commands != null;
        commands.add(Commands.getMarkCommand());
        commands.add(Commands.getUnmarkCommand());
        commands.add(Commands.getDeleteCommand());
        commands.add(Commands.getRescheduleCommand());
        return commands;
    }

    /**
     * Adds task viewing commands to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addTaskViewingCommands(ArrayList<CommandMatcher> commands) {
        assert commands != null;
        commands.add(Commands.getListTasksCommand());
        commands.add(Commands.getFindTasksCommand());
        return commands;
    }

    /**
     * Adds note commands to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addNoteCommands(ArrayList<CommandMatcher> commands) {
        assert commands != null;
        commands.add(Commands.getListNotesCommand());
        commands.add(Commands.getFindNotesCommand());
        commands.add(Commands.getViewNoteCommand());
        commands.add(Commands.getDeleteNoteCommand());
        commands.add(Commands.getAddNoteCommand());
        return commands;
    }

    /**
     * Adds the command that runs otherwise
     * to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addCatchAllCommand(ArrayList<CommandMatcher> commands) {
        commands.add(
                new CommandMatcher((str) -> true, (str) -> new ChatbotResponse(
                "(>.<') I'm sorry, I don't really know what that means.")));
        return commands;
    }
}
