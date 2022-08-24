/**
 * General Exception Class for when the Poolsheen program encounters an error.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version CS2103 AY22/23 Sem 1
 */

public class PoolsheenException extends RuntimeException {
    PoolsheenException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return "The Poolsheen program has encountered an error.";
    }
}