/**
 * Exception Class for when an unknown command is entered.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version CS2103 AY22/23 Sem 1
 */

public class UnknownCommandException extends RuntimeException {
    UnknownCommandException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return "Poolsheen has never seen this command and is confused :(";
    }
}