package dobby;

import java.util.Scanner;

/**
 * UserInput is a class that deals with interactions with the user.
 */
public class UserInput {
    private Scanner scanner = new Scanner(System.in);

    private String taskType;
    private String rest;
    private int ind;
    private String desc;
    private String date;

    /**
     * Reads the user input
     * and updates all required information
     * and returns a string of the task type.
     *
     * @return String of the task type.
     */
    public String readCommand() {
        String input = scanner.nextLine();
        if (!input.contains(" ")) {
            taskType = input;
        } else {
            taskType = Parser.getCmd(input);
            rest = Parser.getRest(input);
            if (taskType.equals("mark") || taskType.equals("unmark") || taskType.equals("delete")) {
                ind = Integer.parseInt(rest);
            } else if (taskType.equals("todo") || taskType.equals("find")) {
                desc = rest;
            } else if (taskType.equals("deadline") || taskType.equals("event")) {
                String dateType = Parser.getDateType(rest);
                if (dateType.equals("noDate")) {
                    date = "noDate";
                    desc = Parser.getDesc(rest);
                } else if (taskType.equals("deadline") && !(dateType.equals("by"))) {
                    date = "wrongDeadline";
                    desc = Parser.getDesc(rest);
                } else if(taskType.equals("event") && !(dateType.equals("at"))) {
                    date = "wrongEvent";
                    desc = Parser.getDesc(rest);
                } else {
                    date = Parser.getDate(rest);
                    desc = Parser.getDesc(rest);
                }
            }
        }
        return taskType;
    }

    /**
     * Returns the index of UserInput object.
     *
     * @return index of UserInput object.
     */
    public int getInd() {
        return ind;
    }

    /**
     * Returns the description of UserInput object.
     *
     * @return description of UserInput object.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns the date of UserInput object.
     *
     * @return date of UserInput object.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the task type of UserInput object.
     *
     * @return task type of UserInput object.
     */
    public String getTaskType() {
        return taskType;
    }
}
