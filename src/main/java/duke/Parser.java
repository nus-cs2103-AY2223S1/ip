package duke;

/**
 * Parser that takes user input and outputs commands DukeBot can understand.
 */
public class Parser {

    /**
     * Static method used to parse player inputs into a String array
     * that is understood by Duke.
     *
     * @param s String input by user to be parsed.
     * @return String array to be processed by Duke.
     * @throws InvalidTaskException Thrown if user input wrong task format.
     * @throws EmptyDescriptionException Throw if task description is empty.
     * @throws InvalidFormatException Thrown if invalid task format.
     */
    public static String[] parseInput(String s) throws InvalidTaskException,
            EmptyDescriptionException, InvalidFormatException {
        s = s.trim();
        String[] helper = s.split(" ");
        String[] result = new String[6];

        if (helper.length == 1) {
            String temp = helper[0];
            if (temp.equals("bye")) {
                result[0] = "bye";
                return result;
            } else if (temp.equals("list")) {
                result[0] = "list";
                return result;

            } else if (temp.equals("mark") || temp.equals("unmark")
                    || temp.equals("delete") || temp.equals("todo")
                    || temp.equals("deadline") || temp.equals("event")
                    || temp.equals("find")) {

                throw new EmptyDescriptionException("Empty descriptor", temp);
            } else {
                throw new InvalidTaskException("No valid task descriptor");
            }
        }

        if (helper[0].equals("mark")) {
            if (helper.length == 2) {
                result[0] = "mark";
                result[1] = helper[1];
                return result;
            } else {
                throw new InvalidTaskException("No valid task descriptor");
            }
        } else if (helper[0].equals("unmark")) {
            if (helper.length == 2) {
                result[0] = "unmark";
                result[1] = helper[1];
                return result;
            } else {
                throw new InvalidTaskException("No valid task descriptor");
            }
        } else if (helper[0].equals("delete")) {
            if (helper.length == 2) {
                result[0] = "delete";
                result[1] = helper[1];
                return result;
            } else {
                throw new InvalidTaskException("No valid task descriptor");
            }
        } else if (helper[0].equals("find")) {
            result[0] = "find";
            String[] findDescription = s.split(" ", 2);
            result[1] = findDescription[1];
            return result;

        } else if (helper[0].equals("todo")) {
            result[0] = "todo";
            String[] todoString = s.split(" ", 2);
            result[1] = todoString[1];
            return result;

        } else if (helper[0].equals("deadline")) {
            result[0] = "deadline";
            String descript = s.substring(8).trim();
            if (!s.contains("/by")) {
                throw new InvalidFormatException("Invalid format");
            }
            String[] temp = descript.split("/by", 2);
            if (temp[1].trim().equals("")) {
                throw new InvalidTaskException("Invalid format");

            }
            result[1] = temp[0].trim();
            result[2] = temp[1].trim();
            return result;

        } else if (helper[0].equals("event")) {
            result[0] = "event";
            String descript = s.substring(6).trim();
            if (!s.contains("/at")) {
                throw new InvalidFormatException("Invalid format");
            }
            String[] temp = descript.split("/at", 2);
            if (temp[1].trim().equals("")) {
                throw new InvalidFormatException("Invalid format");
            }

            result[1] = temp[0].trim();
            result[2] = temp[1].trim();
            return result;
        } else {
            throw new InvalidTaskException("No valid task descriptor");
        }
    }

}
