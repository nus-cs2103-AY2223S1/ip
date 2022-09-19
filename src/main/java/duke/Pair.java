package duke;

/**
 * A Pair contains 2 pieces of data, x and y.
 */
public class Pair <s, t> {

    /** First stored data x, of type s. */
    public s x;
    /** Second stored data y, of type t. */
    public t y;

    /**
     * Constructs a Pair.
     *
     * @param x data stored x.
     * @param y data stored in y.
     */
    Pair(s x, t y) {
        this.x = x;
        this.y = y;
    }


}
