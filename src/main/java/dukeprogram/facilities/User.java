package dukeprogram.facilities;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * A user profile for the current user of the program.
 * Each User is associated with their own program data.
 */
public class User implements Serializable {

    public static final Image DUKE_IMAGE = new Image(
            Objects.requireNonNull(User.class.getResourceAsStream("/images/DaDuke.png")));

    public static final Image USER_IMAGE = new Image(
            Objects.requireNonNull(User.class.getResourceAsStream("/images/DaUser.png")));

    public static final User DUKE = new User("Duke", DUKE_IMAGE);

    private String userName;
    private transient Image profilePicture;

    /**
     * Constructs a new user profile with the given userName
     * @param userName the user's name
     */
    public User(String userName, Image profilePicture) {
        this.userName = userName;
        this.profilePicture = profilePicture;
    }

    /**
     * Retrieves the name of this user
     * @return name of the user
     */
    public String getName() {
        return userName;
    }

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

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        profilePicture = SwingFXUtils.toFXImage(ImageIO.read(stream), null);
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        ImageIO.write(
                SwingFXUtils.fromFXImage(profilePicture, null),
                "png", stream);
    }
}
