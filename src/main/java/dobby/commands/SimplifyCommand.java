package dobby.commands;

import java.io.IOException;

import dobby.DobbyChat;
import dobby.DobbyList;
import dobby.UserInput;
/**
 * Class that updates simplified command as user wishes
 */
public class SimplifyCommand extends Command {
    @Override
    public void execute(DobbyList dl, UserInput ui) throws IOException {
        String initialCmd = ui.getInitialCmd();
        String newCmd = ui.getNewCmd();
        if (initialCmd.equals("missingInitialCommand") | newCmd.equals("missingNewCommand")) {
            DobbyChat.noCommandToSimplify();
        } else if (!ui.isCommandType(initialCmd)) {
            DobbyChat.wrongCommandToSimplify();
        } else {
            ui.setSimplifiedCommand();
            DobbyChat.simplified(initialCmd, newCmd);
        }
    }
}
