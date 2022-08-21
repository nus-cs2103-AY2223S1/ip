/**
 * Utility class to manage type conversions required in all Duke classes
 */
public class TypeConverter {

    /**
     * Converts line in disk file to corresponding Task.
     * @param line String stored in file.
     * @return Task object.
     */
    public static Task fileLineToTask(String line) throws DukeException{
        String taskType, description, date;
        boolean marked = false;
        taskType = line.substring(1,2);
        marked =  line.substring(4, 5).equals(" ");
        switch(taskType) {
        case "T":
            description = line.substring(6);
            Task t = new Task(description);
            if (marked) {
                t.markAsDone();
            }
            return t;
        case "D":
            description = line.substring(6, line.indexOf("("));
            date = line.substring(line.indexOf("("));
            Deadline d = new Deadline(description, date);
            if (marked) {
                d.markAsDone();
            }
            return d;
        case "E":
            description = line.substring(6, line.indexOf("("));
            date = line.substring(line.indexOf("("));
            Event e = new Event(description, date);
            if (marked) {
                e.markAsDone();
            }
            return e;
        }
        throw new DukeException("file corrupted");
    }


    /**
     * Creates Task from user input format of task object creation.
     * @param str user input command.
     * @return Task object.
     * @throws DukeException when user input does not follow correct command structure.
     */
    public static Task stringToTask(String str) throws DukeException{
        Duke.CommandType command = getCommand(str);
        String description = getDescription(str, command.toString().toLowerCase()), date;
        switch (command) {
        case TODO:
            return new Task(description);
        case DEADLINE:
            date = getDate(str);
            return new Deadline(description, date);
        case EVENT:
            date = getDate(str);
            return new Event(description, date);
        }
        throw new DukeException("Cannot create task object");
    }

    /**
     * Creates Deadline from user input format of deadline object creation.
     * @param str user input command.
     * @return Deadline object.
     * @throws DukeException when user input does not follow correct command structure.
     */
    public static Deadline stringToDeadline(String str) throws DukeException{
        Duke.CommandType command = getCommand(str);
        String description = getDescription(str, command.toString().toLowerCase()), date;
            date = getDate(str);
            return new Deadline(description, date);
    }

    /**
     * Creates Event from user input format of event object creation.
     * @param str user input command.
     * @return Event object.
     * @throws DukeException when user input does not follow correct command structure.
     */
    public static Event stringToEvent(String str) throws DukeException{
        Duke.CommandType command = getCommand(str);
        String description = getDescription(str, command.toString().toLowerCase()), date;
            date = getDate(str);
            return new Event(description, date);
    }


    /**
     * Extract task number from string input.
     * @param s extracts task number from user input <command> <number>.
     * @return index of the task in the list plus one.
     */
    public static int getTaskNumber(String s, int listSize) throws DukeException {
        // credit: https://stackoverflow.com/questions/14974033/extract-digits-from-string-stringutils-java
        String numberOnly= s.replaceAll("[^0-9]", "");
        int n;
        if (numberOnly.length() <= 0) {
            throw new DukeException("no number given\n>>");
        } else {
            n = Integer.parseInt(numberOnly);
            if (n > listSize) {
                throw new DukeException("task does not exist in list\n>>");
            } else {
                return n;
            }
        }
    }

    /**
     * Grabs description from string which is expected to have format:
     * <command> <description>...
     * @param input the full string input.
     * @param commandUsed command type used.
     * @return description.
     */
    private static String getDescription(String input, String commandUsed) throws DukeException{
        String description;
        int startDescriptionIndex = input.indexOf(commandUsed) + commandUsed.length();
        int endDescriptionIndex = input.indexOf(" /");
        if (startDescriptionIndex < 0) {
            throw new DukeException("Command does not follow pattern <command> <description>...\n>>");
        } else {
            if (commandUsed.equals("event") || commandUsed.equals("deadline")) {
                if (endDescriptionIndex < 0) {
                    throw new DukeException("Command does not follow pattern  ... /<at/by> <date in HH:MM DD:MM:YYYY>\n>>");
                } else {
                    description = input.substring(startDescriptionIndex, endDescriptionIndex).trim();
                }
            } else {
                description = input.substring(startDescriptionIndex).trim();
            }
        }
        if (description.equals("")) {
            throw new DukeException("Empty description field\n>>");
        }
        return description;
    }

    /**
     * Grabs date from string which is expected to have format:
     * ... /<at/by> <date in HH:MM DD:MM:YYYY>.
     * @param command is the string to extract date from.
     * @return <date> component of command.
     * @throws DukeException when date format is not followed or date is empty.
     */
    private static String getDate(String command) throws DukeException{
        String date = "";
        int startDateIndex = command.indexOf("/") + 3;
        if (startDateIndex < 0) {
            throw new DukeException("Command does not follow pattern ... /<at/by> <date in HH:MM DD:MM:YYYY>\n>>");
        } else {
            date = command.substring(startDateIndex).trim();
        }
        if (date.equals("")) {
            throw new DukeException("Empty date field\n>>");
        }
        return date;
    }

    /**
     * Returns enum command type used.
     * @param userInput user input received.
     * @return enum command type.
     * @throws DukeException when no match found.
     */
    public static Duke.CommandType getCommand(String userInput) throws DukeException{
        int firstWhiteSpace = userInput.trim().indexOf(" ");
        String command;
        Duke.CommandType commandGiven;
        command = firstWhiteSpace < 0 ? userInput: userInput.trim().substring(0, firstWhiteSpace);
        if (command.equals("")) {
            throw new DukeException("no command given\n>>");
        } else {
            for (Duke.CommandType c : Duke.CommandType.values()) {
                if(c.name().equalsIgnoreCase(command)) {
                    return c;
                }
            }
        }
        return null;
    }

}
