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
        String date = "";
        String misc = "";
        String time = "";
        String output = "";
        boolean isDateTime = false;
        int index = 0;
        while (index < task.size()) {
            String top = task.get(index);
            if (top.charAt(0) == '/') {
                isDateTime = true;
                output += "(" + top.substring(1) + ": ";
                index++;
                continue;
            }
            if (isDateTime) {
                if (date.equals("")) {
                    date = top;
                } else {
                    time = top;
                }
                misc += top + " ";
            } else {
                output += top + " ";
            }
            index++;
        }
        if (!misc.equals("")) {
            misc = misc.substring(0, misc.length() - 1);
        }
        if (isDateTime) {
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
        return output.substring(0, output.length() - 1);
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

    String parseReply(String input, UI ui, BotList botList) {
        String reply;
        List<String> separate = new ArrayList<>(List.of(input.split("\\s+")));
        KeyPhrases keyPhrase = KeyPhrases.get(separate.remove(0));
        try {
            switch (keyPhrase) {
            case BYE:
                reply = ui.end();
                break;
            case LIST:
                reply = botList.toString();
                break;
            case MARK:
                reply = botList.mark(Integer.parseInt(separate.get(0)));
                break;
            case UNMARK:
                reply = botList.unmark(Integer.parseInt(separate.get(0)));
                break;
            case DELETE:
                reply = botList.delete(Integer.parseInt(separate.get(0)));
                break;
            case DEADLINE:
                reply = botList.add(new Deadline(separate));
                break;
            case EVENT:
                reply = botList.add(new Event(separate));
                break;
            case TODO:
                reply = botList.add(new ToDo(separate));
                break;
            case FIND_DATE:
                reply = botList.find_date(separate.get(0));
                break;
            case FIND_WORD:
                reply = botList.find_word(separate.get(0));
                break;
            default:
                throw new DekuExceptions("I have no idea what that means. (T _ T)");
            }
        } catch (DekuExceptions e) {
            reply = e.toString();
        }
        return ui.reply(reply);
    }
}
