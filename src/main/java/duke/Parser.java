package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Parser {

    private Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

    public static int parseInteger(String input) throws WrongMessageException {
        if (input.length() == 0) {
            throw new WrongMessageException("Did you forget to specify which task to delete?");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new WrongMessageException("Come on. Give me a number instead!");
        }
    }

    /*public static Todo todo(String require) throws WrongMessageException{
        String content = require.substring(4).trim();
        if (content.equals("")) {
            throw new WrongMessageException();
        }
        Todo td = new Todo(content);
        System.out.println("Added task: " + td.toString());
        return td;
    }

    public static Deadline deadline(String require) throws WrongMessageException {
        String info = require.substring(8).trim();
        String ddlDate = require.split("/by")[1].trim();
        String content = info.split("/by")[0].trim();
        if (content.equals("") || info.startsWith("/by")) {
            throw new WrongMessageException();
        }
        Deadline ddl = new Deadline(content, ddlDate);
        System.out.println("Added task: " + ddl.toString());
        return ddl;
    }

    public static Event event(String require) throws WrongMessageException {
        String info = require.substring(5).trim();
        String happenTime = require.split("/at")[1].trim();
        String content = info.split("/at")[0].trim();
        if (content.equals("") || info.startsWith("/at")) {
            throw new WrongMessageException();
        }
        Event evt = new Event(content, happenTime);
        System.out.println("Added task: " + evt.toString());
        return evt;
    }*/

    public static String convertInfo(String require) throws WrongMessageException {
        String info = require.substring(6).trim();
        if (info.equals("")) {
            throw new WrongMessageException();
        }
        return info;
    }

    public String run(String requirement) {
        String[] splitInput = requirement.split(" ", 2);
        try {
            switch (splitInput[0]) {
                //Handle case when task a Todo
                case "todo":
                    return duke.todo(requirement);

                //Handle case when task is a deadline
                case "deadline": {
                    return duke.deadline(requirement);
                }

                //Handle case when task is an event
                case "event": {
                    return duke.event(requirement);
                }

                //Handle case when user wants to list tasks
                case "list":
                    return duke.showList();

                //Handle case when user wants to handleMark task
                case "mark": {
                    checkForMissingArgs(splitInput);
                    //-1 to get index in 0 indexing
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    return duke.mark(index);
                }

                //Handle case when user wants to handleUnmark task
                case "unmark": {
                    checkForMissingArgs(splitInput);
                    //-1 to get index in 0 indexing
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    return duke.unmark(index);
                }

                //Handle case when user wants to handleDelete task
                case "delete": {
                    checkForMissingArgs(splitInput);
                    //-1 to get in 0 indexing
                    //int index = Integer.parseInt(splitInput[1]);
                    return duke.delete(requirement);
                }

                //Handle case when user wants to find tasks
                case "search":
                    checkForMissingArgs(splitInput);
                    String str = splitInput[1].trim();
                    return duke.search(str);

                //Handle case when user wants to quit bot
                case "bye":
                    return duke.bye();

                case "on":
                    LocalDate lc = LocalDate.parse(splitInput[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    return duke.getOnDate(lc);

                case "hi":
                    return duke.hi();

                //Default case: Not any of the tasks(aTodo, Deadline, Event) and hence, throws an Exception
                default:
                    //To handle any extra words the user keyed in
                    throw new CannotUnderstandException();

            }
        } catch (WrongMessageException | CannotUnderstandException | IOException e) {
            return e.getMessage();
        }
    }

    public void checkForMissingArgs(String[] input) throws WrongMessageException {
        if (input.length == 1 || input[1].trim().isEmpty()) {
            throw new WrongMessageException("MISSING");
        }
    }
}