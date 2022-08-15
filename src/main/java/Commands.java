import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;

/**
 * This contains the commands a user can use to trigger the bot
 * 
 * @author Pei Cheng Yi A029823Y
 */

public enum Commands {
    EXIT("bye");

    public String command;

    Commands(String cmd) {
        command = cmd;
    }
}
