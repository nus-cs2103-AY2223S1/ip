package duke.enums;

/**
 * SortTaskEnum
 */
public enum SortTaskEnum {
    ASC, DESC;

    public static boolean contains(String test) {

        for (SortTaskEnum e : SortTaskEnum.values()) {
            if (e.name().equals(test)) {
                return true;
            }
        }

        return false;
    }
}
