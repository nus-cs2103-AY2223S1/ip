package duke;

import duke.exceptions.InvalidOrderException;

/**
 * Represents order of sorting either in increasing or decreasing.
 */
public enum Order {
    increasing,
    decreasing,
    chrono,
    lexi;

    /**
     * Validate target string as a valid order.
     * @param str
     * @return Order
     * @throws InvalidOrderException where string does not follow
     *                               specified order.
     */
    public static Order validate(String str) throws InvalidOrderException {
        try {
            return Order.valueOf(str);
        } catch (IllegalArgumentException e) {
            throw new InvalidOrderException(str);
        }
    }
}
