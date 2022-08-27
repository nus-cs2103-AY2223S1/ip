package dukeprogram;

import java.io.Serializable;

/**
 * A user profile for the current user of the program.
 * Each User is associated with their own program data.
 */
public class User implements Serializable {
    private String userName;

    /**
     * Constructs a new user profile with the given userName
     * @param userName the user's name
     */
    public User(String userName) {
        this.userName = userName;
    }

    /**
     * Retrieves the name of this user
     * @return name of the user
     */
    public String getName() {
        return userName;
    }

    /**
     * Renames the name of this user
     * @param userName the new name of this user
     */
    public void setName(String userName) {
        this.userName = userName;
    }
}
