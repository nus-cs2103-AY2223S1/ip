package duke;

import java.util.ArrayList;
import java.util.List;

import duke.note.Note;
import duke.note.NoteList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Handles creating commands through duke.CommandMatcher/duke.PrefixCommandMatcher.
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
        commands.add(new PrefixCommandMatcher("deadline", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = new Deadline(str, map.getOrDefault("by", "[unknown]"));
            TaskList.getTaskList().add(task);
            return new DukeResponse(
                    "Good luck with the deadline, here's the task:",
                    task.toString());
        }));

        commands.add(new PrefixCommandMatcher("todo", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = new ToDo(str);
            TaskList.getTaskList().add(task);
            return new DukeResponse(
                    "I've recorded this thing you need to do:",
                    task.toString());
        }));

        commands.add(new PrefixCommandMatcher("event", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = new Event(str, map.getOrDefault("at", "[unknown]"));
            TaskList.getTaskList().add(task);
            return new DukeResponse(
                    "That's going to happen at some time later:",
                    task.toString());
        }));

        return commands;
    }

    /**
     * Adds task modification commands to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addTaskModificationCommands(ArrayList<CommandMatcher> commands) {
        assert commands != null;
        commands.add(PrefixCommandMatcher.of("mark", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = TaskList.getTask(str);
            task.markAsDone();
            return new DukeResponse(
                    "Marked your task as done:",
                    task.toString());
        }));

        commands.add(PrefixCommandMatcher.of("unmark", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = TaskList.getTask(str);
            task.markAsNotDone();
            return new DukeResponse(
                    "Aw... it's not done yet:",
                    task.toString());
        }));

        commands.add(PrefixCommandMatcher.of("delete", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = TaskList.getTask(str);
            TaskList.getTaskList().remove(task);
            return new DukeResponse(
                    "It seems you didn't need this task anymore, so I removed it:",
                    task.toString(),
                    String.format("You have %d tasks left.", TaskList.getTaskList().size()));
        }));

        commands.add(PrefixCommandMatcher.of("reschedule", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = TaskList.getTask(str);
            if (task instanceof ToDo) {
                return new DukeResponse("That's a todo, it doesn't have a date.");
            }
            Task newTask;
            if (task instanceof Event) {
                if (!map.containsKey("at")) {
                    return new DukeResponse("Do specify /at for events.");
                }
                newTask = new Event(task.getDescription(), map.get("at"), task.isTaskDone());
            } else if (task instanceof Deadline) {
                if (!map.containsKey("at")) {
                    return new DukeResponse("Do specify /by for events.");
                }
                newTask = new Deadline(task.getDescription(), map.get("by"), task.isTaskDone());
            } else {
                return new DukeResponse("This is a strange task - I don't recognise it.");
            }
            List<Task> tasks = TaskList.getTaskList();
            tasks.set(tasks.indexOf(task), newTask);
            return new DukeResponse(
                    "I have rescheduled your task!",
                    newTask.toString());
        }));

        return commands;
    }

    /**
     * Adds task viewing commands to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addTaskViewingCommands(ArrayList<CommandMatcher> commands) {
        assert commands != null;
        commands.add(new CommandMatcher(str -> str.equals("list"), (str) -> {
            assert str != null;
            List<Task> tasks = TaskList.getTaskList();
            return new DukeResponse(
                    listObjects("Here, your tasks:", tasks));
        }));

        commands.add(new PrefixCommandMatcher("find", (str, map) -> {
            assert str != null;
            assert map != null;
            List<Pair<Integer, Task>> tasks = TaskList.filterTasks(str);
            return new DukeResponse(
                    listNumberedObjects("Here are the tasks that you might be looking for:", tasks));
        }));

        return commands;
    }

    /**
     * Adds note commands to an ArrayList.
     *
     * @return ArrayList of chatbot commands.
     */
    public static ArrayList<CommandMatcher> addNoteCommands(ArrayList<CommandMatcher> commands) {
        assert commands != null;
        commands.add(new CommandMatcher(str -> str.equals("list notes"), (str) -> {
            assert str != null;
            List<Note> notes = NoteList.getNoteList();
            return new DukeResponse(
                    listObjects("Here, your notes:", notes));
        }));

        commands.add(new PrefixCommandMatcher("find notes", (str, map) -> {
            assert str != null;
            assert map != null;
            List<Pair<Integer, Note>> notes = NoteList.filterNotes(str);
            return new DukeResponse(
                    listNumberedObjects("These notes match your query:", notes));
        }));

        commands.add(PrefixCommandMatcher.of("view note", (str, map) -> {
            assert str != null;
            assert map != null;
            Note note = NoteList.getNote(str);
            return new DukeResponse("Here's the note:", note.getTitle(), note.getContent());
        }));

        commands.add(PrefixCommandMatcher.of("delete note", (str, map) -> {
            assert str != null;
            assert map != null;
            Note note = NoteList.getNote(str);
            NoteList.getNoteList().remove(note);
            return new DukeResponse(
                    "I removed this note:",
                    note.toString());
        }));

        commands.add(PrefixCommandMatcher.of("note", (str, map) -> {
            assert str != null;
            assert map != null;
            Note note = new Note(str, map.getOrDefault("content", "[EMPTY]"));
            NoteList.getNoteList().add(note);
            return new DukeResponse(String.format("Added your note about %s.", str));
        }));

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
                new CommandMatcher((str) -> true, (str) -> new DukeResponse(
                "(>.<') I'm sorry, I don't really know what that means.")));
        return commands;
    }
}
