package duke;
import java.util.Arrays;

public class Parser {

    public Parser(){

    }

    public String[] parseInput(String inputString) throws DukeException {
        String[] input = inputString.split(" ");
            if (inputString.equals("bye")) {
                return new String[]{"bye"};

            } else if (inputString.equals("list")) {
                return new String[]{"list"};

            } else if (input[0].equals("todo")) {
                String taskName = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                return new String[]{"todo",taskName};

            } else if (input[0].equals("deadline")) {
                int indexOfDate = findDate(input);
                if( indexOfDate == -1){
                    throw new DukeException("☹ OOPS!!! Please add a date for your deadline with /by.");
                } else {
                    String taskName = String.join(" ", Arrays.copyOfRange(input, 1, indexOfDate));
                    String date = input[indexOfDate+1];
                    if( taskName.equals("")){
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    return new String[]{"deadline",taskName,date};
                }

            } else if (input[0].equals("event")) {
                int indexOfDate = findDate(input);
                if( indexOfDate == -1){
                    throw new DukeException("☹ OOPS!!! Please add a date for your event with /at.");
                } else {
                    String taskName = String.join(" ", Arrays.copyOfRange(input, 1, indexOfDate));
                    String date = input[indexOfDate+1];
                    if( taskName.equals("")){
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    return new String[]{"deadline",taskName,date};
                }

            } else if (input.length == 2 && isNumeric(input[1])) {
                String keyword = input[0];
                String index = input[1];
                return new String[]{keyword,index};

            } else if (input.length == 2 && input[0].equals("date")) {
                return new String[]{"date",input[1]};
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
    }

    /**
     * Determines if the input string is a number (only 0-9)
     * @param input The string to be tested.
     * @return The result of the test.
     */
    private static boolean isNumeric(String input) {
        return input.matches("^[0-9]*$");
    }

    /**
     * Find the index in the parsed input where the description ends and the time begins
     * @param split The parsed input.
     * @return The index of the /by or /at in the input string. Returns -1 if not found.
     */
    private static int findDate(String[] split) {
        for(int i = 0; i<split.length; i++){
            if(split[i].equals("/by") || split[i].equals("/at")){
                return i;
            }
        }
        return -1;
    }




}
