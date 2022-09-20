package dukeprogram.facilities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import dukeprogram.storage.SaveManager;
import javafx.scene.image.Image;

/**
 * A user profile for the current user of the program.
 * Each User is associated with their own program data.
 */
public class User implements Serializable {

    public static final User DUKE = new User("Duke",
            SaveManager.loadProfilePicture(SaveManager.ImageType.DukeImage));

    @JsonProperty("userName")
    private String userName;

    @JsonIgnore
    private Image profilePicture;

    /**
     * Constructs a new user profile with the given userName
     * @param userName the user's name
     */

    public User(String userName, Image profilePicture) {
        this.userName = userName;
        this.profilePicture = profilePicture;
    }

    /**
     * Constructs a new user profile with the given userName
     */
    public User() {
        this.profilePicture = SaveManager.loadProfilePicture(SaveManager.ImageType.UserImage);
    }

    /**
     * Retrieves the name of this user
     * @return name of the user
     */
    public String getName() {
        return userName;
    }

    @JsonIgnore
    public Image getUserImage() {
        return profilePicture;
    }

    /**
     * Renames the name of this user
     * @param userName the new name of this user
     */
    public void setName(String userName) {
        this.userName = userName;
    }

    /*private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        profilePicture = SwingFXUtils.toFXImage(ImageIO.read(stream), null);
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        ImageIO.write(
                SwingFXUtils.fromFXImage(profilePicture, null),
                "png", stream);
    }*/
}
