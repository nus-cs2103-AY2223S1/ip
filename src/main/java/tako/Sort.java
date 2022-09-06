package tako;

/**
 * Set of valid sort methods.
 */
public enum Sort {
    ALPHABET, DATE;

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
}
