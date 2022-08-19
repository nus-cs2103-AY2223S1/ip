package utils;

import enums.*;

public class OutputLogger {

    /**
     * General display function to print messages to the console
     * 
     * @param command The command that is being exectued
     * @param message Any additional information that should be printed with the
     *                command
     */
    public void display(Commands command, String... message) {
        System.out.println(Messages.LINE_SEPARATION);
        switch (command) {
            case EXIT:
                System.out.println(Messages.EXIT);
                break;
            case SHOW_LIST:
                break;
            case GREET:
                System.out.println(Messages.GREET);
                System.out.println(Messages.LOGO);
                break;
            case ADD_TASK:
                break;
            case ADD_TODO:
                System.out.println(Messages.ADD_TODO);
                break;
            case ADD_EVENT:
                System.out.println(Messages.ADD_EVENT);
                break;
            case ADD_DEADLINE:
                System.out.println(Messages.ADD_DEADLINE);
                break;
            case MARK_DONE:
                System.out.println(Messages.MARK_DONE);
                break;
            case MARK_UNDONE:
                System.out.println(Messages.MARK_UNDONE);
                break;
            case DELETE:
                System.out.println(Messages.DELETE);
            default: // Every other command and invalid commands
                break;
        }

        for (String msg : message) {
            System.out.println(msg);
        }
        System.out.println(Messages.LINE_SEPARATION);
    }
}
