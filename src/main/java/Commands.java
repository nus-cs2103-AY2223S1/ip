import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;

/**
 * This contains the commands a user can use to trigger the bot
 * 
 * @author Pei Cheng Yi A029823Y
 */

public enum Commands {
    GET_COMMAND(""),
    EXIT("bye"),
    SHOW_LIST("list"),
    GREET("greet"),
    ADD_TASK("Add Task"),
    ADD_TODO("todo"),
    ADD_EVENT("event"),
    ADD_DEADLINE("deadline"),
    MARK_DONE("mark"),
    MARK_UNDONE("mark"),
    ERROR("");

    public String command;

    Commands(String cmd) {
        command = cmd;
    }
}
