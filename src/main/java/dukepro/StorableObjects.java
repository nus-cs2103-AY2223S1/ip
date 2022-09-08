package dukepro;

import java.time.LocalDate;

/**
 * Abstract class for objects that
 * can be stored by the GeneralStorage
 * class.
 */
public abstract class StorableObjects {
    /**
     * Returns a String representing
     * the StorableObject to be stored
     * in the file.
     *
     * @return String.
     */
    public abstract String toFileForm();

    /**
     * Returns if the name of this object
     * matches the user's query.
     *
     * @return boolean.
     */
    public abstract boolean getMatching(String search);

    /**
     * Returns whether the date of this
     * object matches the queried date.
     *
     * @return boolean.
     */
    public abstract boolean compareDate(LocalDate localDate);
}
