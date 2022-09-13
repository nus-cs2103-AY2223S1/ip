package deku;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import deku.task.Deadline;
import deku.task.Event;
import deku.task.ToDo;

/**
 * Parses input from user to bot understandable structure
 */
public class InputParser {
    private LocalDate date;
    private LocalTime time;

    /**
     * Parses input from user to bot understandable structure
     *
     * @param task Space separated List of instruction after the user command
     * @return String of friendly and readable output
     */
    public String parseTask(List<String> task) {
        StringBuilder date = new StringBuilder();
        StringBuilder time = new StringBuilder();
        StringBuilder misc = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int index;
        index = updateTillSpecialChar(output, task);
        if (index < task.size()) {
            updateSpecialCharOnwards(index, date, time, misc, task);
        }
        if (!misc.toString().equals("")) {
            misc = new StringBuilder(misc.substring(0, misc.length() - 1));
        }
        if (index != task.size()) {
            return dateTimeFormatter(output.toString(), misc.toString(), date.toString(), time.toString());
        }
        return output.substring(0, output.length() - 1);
    }

    // Helper method for parseTask
    private String dateTimeFormatter(String output, String misc, String date, String time) {
        parseDate(date);
        if (this.date == null) {
            return output + misc + ")";
        }
        output += this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (time.equals("")) {
            return output + ")";
        }
        parseTime(time);
        if (this.time == null) {
            return output + " " + time + ")";
        }
        return output
                + " "
                + this.time.format(DateTimeFormatter.ofPattern("HH:mm"))
                + ")";
    }

    // Helper method for parseTask
    private int updateTillSpecialChar(StringBuilder output, List<String> task) {
        int index = 0;
        while (index < task.size()) {
            String top = task.get(index);
            if (top.charAt(0) == '/') {
                output.append("(")
                        .append(top.substring(1))
                        .append(": ");
                index++;
                break;
            }
            output.append(top).append(" ");
            index++;
        }
        return index;
    }

    // Helper method for parseTask
    private void updateSpecialCharOnwards(int index,
                                          StringBuilder date,
                                          StringBuilder time,
                                          StringBuilder misc,
                                          List<String> tasks) {
        assert(index >= 0);
        for (int i = index; i < tasks.size(); i++) {
            String top = tasks.get(i);
            if (date.length() == 0) {
                date.append(top);
            } else {
                time.append(top);
            }
            misc.append(top).append(" ");
        }
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    void parseDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd-MM-yyyy][yyyy-MM-dd]");
            this.date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeException e) {
            this.date = null;
            System.out.println("Invalid date format! I will add this task, some functionalities might not work!\n"
                    + "Currently supports: dd/MM/yyyy | dd-MM-yyyy | yyyy-MM-dd |\n"
                    + "Example: 23/08/2022");
        }
    }

    void parseTime(String timeString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[HH:mm][HHmm]");
            this.time = LocalTime.parse(timeString, formatter);
        } catch (DateTimeException e) {
            if (this.date == null) {
                return;
            }
            this.time = null;
            System.out.println("Please input a valid time format! I will add this task, "
                    + "some functionalities might not work!\n"
                    + "Currently supports 24 hour format: HH:mm | HHmm |\n"
                    + "Example: 1800");
        }
    }

    String parseReply(String input, BotList botList) {
        String reply;
        List<String> separate = new ArrayList<>(List.of(input.split("\\s+")));
        KeyPhrases keyPhrase = KeyPhrases.get(separate.remove(0));
        try {
            switch (keyPhrase) {
            case BYE:
                reply = "Bye! See you next time!";
                break;
            case LIST:
                reply = "Current tasks are shown on the right panel!"; //botList.toString();
                break;
            case MARK:
                botList.overwriteUndo();
                reply = botList.mark(Integer.parseInt(separate.get(0)));
                break;
            case UNMARK:
                botList.overwriteUndo();
                reply = botList.unmark(Integer.parseInt(separate.get(0)));
                break;
            case DELETE:
                botList.overwriteUndo();
                reply = botList.delete(Integer.parseInt(separate.get(0)));
                break;
            case DEADLINE:
                botList.overwriteUndo();
                reply = botList.add(new Deadline(separate));
                break;
            case EVENT:
                botList.overwriteUndo();
                reply = botList.add(new Event(separate));
                break;
            case TODO:
                botList.overwriteUndo();
                reply = botList.add(new ToDo(separate));
                break;
            case FIND_DATE:
                reply = botList.find_date(separate.get(0));
                break;
            case FIND_WORD:
                reply = botList.find_word(separate.get(0));
                break;
            case UNDO:
                reply = botList.undo();
                break;
            default:
                throw new DekuExceptions("I have no idea what that means. (T _ T)");
            }
        } catch (DekuExceptions e) {
            reply = e.toString();
        } catch (NumberFormatException e) {
            reply = new DekuExceptions("I have no idea what that means. (T _ T)").toString();
        }
        return "\n" + reply;
    }
}
