package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Handles user input.
 *
 * @author Yuvaraj Kumaresan
 */
public class Parser {

    /**
     * Stores tasks in a text file.
     */
    protected static Storage storage;

    /**
     * Handles User interaction.
     */
    protected static Ui ui;

    /**
     * Constructor.
     *
     * @param list The storage list used.
     * @param ui   The Ui interface used.
     */
    public Parser(Storage list, Ui ui) {
        this.storage = list;
        this.ui = ui;
    }

    /**
     * Handles parsing of text input by the user.
     *
     * @return
     * @throws IOException
     */
    public static String parse(String text) throws IOException {

        if (text.equalsIgnoreCase("list")) {
            return ui.list();

        } else if (text.equalsIgnoreCase("bye")) {
            return ui.bye();

        } else if (text.startsWith("delete")) {
            try {
                if (text.equalsIgnoreCase("delete") || text.equalsIgnoreCase("delete ")) {
                    return ui.deleteNoNumber();
                } else {
                    int deletable = Integer.parseInt(text.replace("delete ", "")) - 1;
                    if (deletable < storage.taskList.arrayList.size() && deletable >= 0) {
                        Task deleted = storage.taskList.arrayList.get(deletable);
                        storage.taskList.delete(deletable);
                        return ui.delete(deleted);
                    } else {
                        return ui.deleteError();
                    }

                }
            } catch (NumberFormatException error) {
                return ui.deleteNumberFormatError();
            }

        } else if (text.startsWith("todo")) {
            if (text.equalsIgnoreCase("todo") || text.equalsIgnoreCase("todo ") || text.replace("todo ", "").trim().length() < 1) {
                try {
                    throw new DukeException.DukeToDoException("Please provide a description for your todo task.");
                } catch (DukeException.DukeToDoException error) {
                    return ui.todoError(error);
                }
            } else {
                ToDo item = new ToDo(text.replace("todo ", ""));
                storage.taskList.addTodo(item);
                return ui.todo(item);
            }

        } else if (text.startsWith("deadline")) {
            try {
                String[] description = text.replace("deadline ", "").split("/by ");
                try {
                    if (description[1].length() > 10) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        Deadline item = new Deadline(description[0], LocalDateTime.parse(description[1], formatter));
                        storage.taskList.addDeadline(item);
                        return ui.deadline(item);
                    } else {
                        description[1] = description[1] + " 00:00";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        Deadline item = new Deadline(description[0], LocalDateTime.parse(description[1], formatter));
                        storage.taskList.addDeadline(item);
                        return ui.deadline(item);
                    }
                } catch (DateTimeParseException e) {
                    return ui.dateTimeParseError();
                }
            } catch (ArrayIndexOutOfBoundsException error) {
                return ui.dateTimeArrayException();
            }

        } else if (text.startsWith("event")) {
            try {
                String[] description = text.replace("event ", "").split("/at ");
                Event item = new Event(description[0], description[1]);
                storage.taskList.addEvent(item);
                return ui.event(item);
            } catch (ArrayIndexOutOfBoundsException error) {
                return ui.eventError();
            }

        } else if (text.startsWith("mark")) {
            try {
                if (text.equalsIgnoreCase("mark") || text.equalsIgnoreCase("mark ")) {
                    return ui.noNumberToMark();
                } else {
                    if (Integer.parseInt(text.replace("mark ", "")) - 1 < storage.taskList.arrayList.size()
                            && Integer.parseInt(text.replace("mark ", "")) > 0) {
                        Task toBeMarked = storage.taskList.arrayList.get(Integer.parseInt(text.replace("mark ", "")) - 1);
                        if (!toBeMarked.getIsDone()) {
                            storage.taskList.mark(toBeMarked);
                            return ui.mark(text);
                        } else {
                            return ui.alreadyMarked();
                        }
                    } else {
                        return ui.noItemToMark();
                    }
                }
            } catch (NumberFormatException error) {
                return ui.markFormatError();
            }

        } else if (text.startsWith("unmark")) {
            try {
                if (text.equalsIgnoreCase("unmark") || text.equalsIgnoreCase("unmark ")) {
                    return ui.noNumberToUnmark();
                } else {
                    if (Integer.parseInt(text.replace("unmark ", "")) - 1 < storage.taskList.arrayList.size()
                            && Integer.parseInt(text.replace("unmark ", "")) > 0) {
                        Task toBeUnmarked = storage.taskList.arrayList.get(Integer.parseInt(text.replace("unmark ", "")) - 1);
                        if (toBeUnmarked.getIsDone()) {
                            storage.taskList.unmark(toBeUnmarked);
                            return ui.unmark(text);
                        } else {
                            return ui.alreadyUnmarked();
                        }
                    } else {
                        return ui.noItemToUnmark();
                    }
                }
            } catch (NumberFormatException error) {
                return ui.unmarkFormatError();
            }

        } else if (text.startsWith("help")) {
            return ui.help();

        } else if (text.startsWith("find")) {
            if (text.startsWith("find ")) {
                String search = text.replace("find ", "");
                return ui.find(search);
            } else if (text.equalsIgnoreCase("find")) {
                return ui.noSearchError();
            } else {
                return ui.findError();
            }

        } else {
            try {
                throw new DukeException.DukeCommandException("Invalid command");
            } catch (DukeException.DukeCommandException error) {
                return ui.generalError(error);
            }
        }
    }

}
