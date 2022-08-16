/**
 * Exception Class for when an incomplete command is entered.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version CS2103 AY22/23 Sem 1
 */

public class IncompleteCommandException extends RuntimeException {
    String commandType;
    String whatToDo;

    IncompleteCommandException(String input, String commandType, String whatToDo) {
        super(input);
        this.commandType = commandType;
        this.whatToDo = whatToDo;
    }

    @Override
    public String toString() {
        return "Poolsheen thinks you did not fill up the " + commandType +
                " command properly\n" + Poolsheen.startReply + this.whatToDo;
    }
}