package org.Olivia;
/**
 * Listens command from a given UiHandler
 * parse the command and execute the corresponding function
 * syncs with hard disk after each execution
 *
 * @author ZHANG TONGJUN (albertZhangTJ)
 */

import org.Olivia.IO.FileHandler;
import org.Olivia.IO.UiHandler;
import org.Olivia.calendar.*;

import java.security.InvalidParameterException;
import java.util.List;


public class GuiEventDispatcher {

    private Calendar table;
    private FileHandler disk;

    public GuiEventDispatcher(Calendar table, FileHandler disk) {
        this.table = table;
        this.disk = disk;
    }

    private String help() {
        return UiHandler.generateHelpMsg();
    }

    private String list() {
        return this.table.toString();
    }

    private String markAsDoneUndone(String input) throws Exception {
        String[] args = input.toLowerCase().split(" ");
        if (args.length != 2) {
            throw new InvalidParameterException("Sorry, which entry do you want me to mark/unmark?");
            //return 400;
        }
        if (args[0].equals("mark")) {
            int status = this.table.markAsDone(Integer.parseInt(args[1]));
            if (status == 200 || status == 208) {
                disk.syncToFile(this.table);
                return "Jawohl, I have marked the following event as completed:\n     " +
                        this.table.getEntry(Integer.parseInt(args[1])) + "\n";
            }

        }
        if (args[0].equals("unmark")) {
            int status = this.table.markAsUndone(Integer.parseInt(args[1]));
            if (status == 200 || status == 208) {
                disk.syncToFile(this.table);
                return "Jawohl, I have marked the following event as incomplete:\n     " +
                        this.table.getEntry(Integer.parseInt(args[1])) + "\n";
            }
        }
        throw new RuntimeException("Sorry, there seems to be some difficulties processing your command, could you check the syntax and try again later?");
    }

    private String delete(String input) throws Exception {
        String[] args = input.toLowerCase().split(" ");
        if (args.length != 2) {
            throw new InvalidParameterException("Sorry, which entry do you want me to delete?");
            //return 400;
        }
        disk.syncToFile(this.table);
        return "Jawohl, I have removed the following entry from your calendar:\n     " +
                this.table.deleteEntry(Integer.parseInt(args[1])) + "\n";
    }

    private String addEntryToCalendar(String input) throws Exception {
        String[] args = input.toLowerCase().split(" ");
        if (args[0].equals("todo") && args.length >= 2) {
            CalendarEntryTodo entry = new CalendarEntryTodo(input.substring(5));
            int status = this.table.addEntry(entry);
            if (status == 200) {
                disk.syncToFile(this.table);
                return "Verstehe, added: " + entry.toString() + "\n";
            }

        }
        else if (args[0].equals("deadline") && args.length >= 4) {
            if (input.indexOf("/by") == -1) {
                throw new InvalidParameterException("Sorry, what is the exact time of the deadline?\nCheck the help message for information on command syntax");
                //return 500;
            }
            input = input.substring(9);
            String time = input.substring(input.indexOf("/by") + 4);
            String title = input.substring(0, input.indexOf("/by") - 1);
            CalendarEntryDeadline entry = new CalendarEntryDeadline(title, time);
            int status = this.table.addEntry(entry);
            if (status == 200) {
                disk.syncToFile(this.table);
                return "Verstehe, added: " + entry.toString() + "\n";
            }

        }
        else if (args[0].equals("event") && args.length >= 4) {
            if (input.indexOf("/at") == -1 || input.indexOf(" - ") == -1) {
                throw new InvalidParameterException("Sorry, what is the exact time of the event?\nCheck the help message for information on command syntax");
                //return 500;
            }
            input = input.substring(6);
            String time = input.substring(input.indexOf("/at") + 4);
            String title = input.substring(0, input.indexOf("/at") - 1);
            CalendarEntryEvent entry = new CalendarEntryEvent(title, time.split(" - ")[0], time.split(" - ")[1]);
            int status = this.table.addEntry(entry);
            if (status == 200) {
                disk.syncToFile(this.table);
                return "Verstehe, added: " + entry.toString() + "\n";
            }

        }
        throw new InvalidParameterException("Sorry, I don't seem to understand you");
        //return 500;
    }

    private String find(String input) {
        input = input.substring(5);
        List<CalendarEntry> entries = this.table.getEntriesContains(input);
        String content = "";
        for (CalendarEntry e : entries) {
            content = content + e.toString() + "\n";
        }
        if (entries.size()==0){
            return "Es tut mir leid, cannot find any match entries";
        }
        return content;
    }

    /**
     * calls corresponding functions based on the input command
     *
     * @param input the array of string that contains the command and parameters
     * @return status code (adapted from http, with exceptions such as status 0 represents exit)
     */
    public String dispatchCommand(String input) throws Exception {
        if (input == null) {
            throw new InvalidParameterException("command string array is not expected to be null, internal error");
        }
        if (input.length() == 0) {
            throw new IllegalArgumentException("Sorry, command length cannot be zero");
            //return 400; //Bad request
        }
        if (input.toLowerCase().equals("exit") || input.toLowerCase().equals("bye")) {
            return "Bis später!"; //NOT http status code, exit
        }
        if (input.toLowerCase().equals("list") || input.toLowerCase().equals("ls")) {
            return list();
        }

        String[] splited_input = input.toLowerCase().split(" ");
        if (splited_input.length == 0) {
            throw new IllegalArgumentException("Sorry, I don't seem to understand you");
        }
        if (splited_input[0].equals("help")) {
            return help();
        }
        if (splited_input[0].equals("mark") || splited_input[0].equals("unmark")) {
            return markAsDoneUndone(input);
        }
        if (splited_input[0].equals("todo") || splited_input[0].equals("event") || splited_input[0].equals("deadline")) {
            return addEntryToCalendar(input);
        }
        if (splited_input[0].equals("delete")) {
            return delete(input);
        }
        if (splited_input[0].equals("find") && splited_input.length > 1) {
            return find(input);
        }
        throw new IllegalArgumentException("Sorry, I don't seem to understand you");
        //return 500; //not implemented
    }

}
