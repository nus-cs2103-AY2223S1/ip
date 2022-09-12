package tako;

/**
 * Set of valid sort methods.
 */
public enum Sort {
    ALPHABET, DATE;

    //@@author Devashish Bansal
    // Reused from https://stackoverflow.com/questions/4936819/java-check-if-enum-contains-a-given-string
    // with minor modifications
    /**
     * Check if Sort has the specified way of sorting.
     *
     * @param s String to check against Sort values.
     * @return boolean for whether the sort is valid.
     */
    public static boolean hasSortBy(String s) {
        for (Sort sortBy : Sort.values()) {
            if (sortBy.name().equals(s)) {
                return true;
            }
        }
        return false;
    }
    //@@author
}
