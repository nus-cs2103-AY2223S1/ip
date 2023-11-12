package anthea;

import java.util.List;

import anthea.note.Note;
import anthea.note.NoteList;
import anthea.task.Deadline;
import anthea.task.Event;
import anthea.task.Task;
import anthea.task.TaskList;
import anthea.task.ToDo;

/**
 * Creates commands.
 */
public class Commands {
    /**
     * Creates the command to add deadlines.
     * @return Command for adding deadlines.
     */
    public static CommandMatcher getAddDeadlineCommand() {
        return new PrefixCommandMatcher("deadline", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = new Deadline(str, map.getOrDefault("by", "[unknown]"));
            TaskList.getTaskList().add(task);
            return new ChatbotResponse(
                    "Good luck with the deadline, here's the task:",
                    task.toString());
        });
    }

    /**
     * Creates the command to add events.
     * @return Command for adding events.
     */
    public static CommandMatcher getAddEventCommand() {
        return new PrefixCommandMatcher("event", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = new Event(str, map.getOrDefault("at", "[unknown]"));
            TaskList.getTaskList().add(task);
            return new ChatbotResponse(
                    "That's going to happen at some time later:",
                    task.toString());
        });
    }

    /**
     * Creates the command to add tasks.
     * @return Command for adding tasks.
     */
    public static CommandMatcher getAddToDoCommand() {
        return new PrefixCommandMatcher("todo", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = new ToDo(str);
            TaskList.getTaskList().add(task);
            return new ChatbotResponse(
                    "I've recorded this thing you need to do:",
                    task.toString());
        });
    }

    /**
     * Creates the command to mark tasks as done.
     * @return Command for marking tasks as done.
     */
    public static CommandMatcher getMarkCommand() {
        return PrefixCommandMatcher.of("mark", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = TaskList.getTask(str);
            task.markAsDone();
            return new ChatbotResponse(
                    "Marked your task as done:",
                    task.toString());
        });
    }

    /**
     * Creates the command to unmark tasks as done.
     * @return Command for unmarking tasks as done.
     */
    public static CommandMatcher getUnmarkCommand() {
        return PrefixCommandMatcher.of("unmark", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = TaskList.getTask(str);
            task.markAsNotDone();
            return new ChatbotResponse(
                    "Aw... it's not done yet:",
                    task.toString());
        });
    }

    /**
     * Creates the command to delete tasks.
     * @return Command for deleting tasks.
     */
    public static CommandMatcher getDeleteCommand() {
        return PrefixCommandMatcher.of("delete", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = TaskList.getTask(str);
            TaskList.getTaskList().remove(task);
            return new ChatbotResponse(
                    "It seems you didn't need this task anymore, so I removed it:",
                    task.toString(),
                    String.format("You have %d tasks left.", TaskList.getTaskList().size()));
        });
    }

    /**
     * Creates the command to reschedule.
     * @return Command for rescheduling.
     */
    public static CommandMatcher getRescheduleCommand() {
        return PrefixCommandMatcher.of("reschedule", (str, map) -> {
            assert str != null;
            assert map != null;
            Task task = TaskList.getTask(str);
            if (task instanceof ToDo) {
                return new ChatbotResponse("That's a todo, it doesn't have a date.");
            }
            Task newTask;
            if (task instanceof Event) {
                if (!map.containsKey("at")) {
                    return new ChatbotResponse("Do specify /at for events.");
                }
                newTask = new Event(task.getDescription(), map.get("at"), task.isTaskDone());
            } else if (task instanceof Deadline) {
                if (!map.containsKey("by")) {
                    return new ChatbotResponse("Do specify /by for deadlines.");
                }
                newTask = new Deadline(task.getDescription(), map.get("by"), task.isTaskDone());
            } else {
                return new ChatbotResponse("This is a strange task - I don't recognise it.");
            }
            List<Task> tasks = TaskList.getTaskList();
            tasks.set(tasks.indexOf(task), newTask);
            return new ChatbotResponse(
                    "I have rescheduled your task!",
                    newTask.toString());
        });
    }

    /**
     * Creates the command to list tasks.
     * @return Command for listing tasks.
     */
    public static CommandMatcher getListTasksCommand() {
        return new CommandMatcher(str -> str.equals("list"), (str) -> {
            assert str != null;
            List<Task> tasks = TaskList.getTaskList();
            return new ChatbotResponse(
                    Parser.listObjects("Here, your tasks:", tasks));
        });
    }

    /**
     * Creates the command to find tasks.
     * @return Command for finding tasks.
     */
    public static CommandMatcher getFindTasksCommand() {
        return new PrefixCommandMatcher("find", (str, map) -> {
            assert str != null;
            assert map != null;
            List<Pair<Integer, Task>> tasks = TaskList.filterTasks(str);
            return new ChatbotResponse(
                    Parser.listNumberedObjects("Here are the tasks that you might be looking for:", tasks));
        });
    }

    /**
     * Creates the command to list notes.
     * @return Command for listing notes.
     */
    public static CommandMatcher getListNotesCommand() {
        return new CommandMatcher(str -> str.equals("list notes"), (str) -> {
            assert str != null;
            List<Note> notes = NoteList.getNoteList();
            return new ChatbotResponse(
                    Parser.listObjects("Here, your notes:", notes));
        });
    }

    /**
     * Creates the command to find notes.
     * @return Command for finding notes.
     */
    public static CommandMatcher getFindNotesCommand() {
        return new PrefixCommandMatcher("find notes", (str, map) -> {
            assert str != null;
            assert map != null;
            List<Pair<Integer, Note>> notes = NoteList.filterNotes(str);
            return new ChatbotResponse(
                    Parser.listNumberedObjects("These notes match your query:", notes));
        });
    }

    /**
     * Creates the command to view notes.
     * @return Command for viewing notes.
     */
    public static CommandMatcher getViewNoteCommand() {
        return PrefixCommandMatcher.of("view note", (str, map) -> {
            assert str != null;
            assert map != null;
            Note note = NoteList.getNote(str);
            return new ChatbotResponse("Here's the note:", note.getTitle(), note.getContent());
        });
    }

    /**
     * Creates the command to delete notes.
     * @return Command for deleting notes.
     */
    public static CommandMatcher getDeleteNoteCommand() {
        return PrefixCommandMatcher.of("delete note", (str, map) -> {
            assert str != null;
            assert map != null;
            Note note = NoteList.getNote(str);
            NoteList.getNoteList().remove(note);
            return new ChatbotResponse(
                    "I removed this note:",
                    note.toString());
        });
    }

    /**
     * Creates the command to add notes.
     * @return Command for add notes.
     */
    public static CommandMatcher getAddNoteCommand() {
        return PrefixCommandMatcher.of("note", (str, map) -> {
            assert str != null;
            assert map != null;
            Note note = new Note(str, map.getOrDefault("content", "[EMPTY]"));
            NoteList.getNoteList().add(note);
            return new ChatbotResponse(String.format("Added your note about %s.", str));
        });
    }
}
