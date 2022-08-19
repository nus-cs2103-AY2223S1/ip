package enums;

import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;

/**
 * This contains the commands a user can use to trigger the bot
 * 
 * @author Pei Cheng Yi A029823Y
 */

public enum Commands {
    EXIT("bye"),
    LIST("list"),
    GREET("greet"),
    TASK("Add Task"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    DELETE("delete"),
    MARK("mark"),
    ERROR("");

    private String command;

    Commands(String cmd) {
        command = cmd;
    }

    @Override
    public String toString() {
        return command;
    }
}
