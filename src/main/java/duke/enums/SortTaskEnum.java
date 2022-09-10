package duke.enums;

/**
 * SortTaskEnum
 */
public enum SortTaskEnum {
    ASC, DESC;

    /**
     * Returns whether the test string is contained the sort task enum
     * @param test The test sting
     * @return Whether the test string is contained the sort task enum
     */
    public static boolean contains(String test) {

        for (SortTaskEnum e : SortTaskEnum.values()) {
            if (e.name().equals(test)) {
                return true;
            }
        }

        return false;
    }
}
