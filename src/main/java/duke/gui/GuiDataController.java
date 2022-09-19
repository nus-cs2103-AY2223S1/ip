package duke.gui;

import javafx.scene.image.Image;

/**
 * Class to control the general images and other data of the GUI.
 */
public class GuiDataController {
    private static GuiDataController singleton;


    private Image userImage;
    private Image dukeImage;


    GuiDataController() {
        userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    }

    /**
     * Gets the current user image.
     *
     * @return
     */
    public Image getUserImage() {
        return userImage;
    }

    /**
     * Gets the current duke image.
     *
     * @return
     */
    public Image getDukeImage() {
        return dukeImage;
    }

    /**
     * Sets the image for user and duke to a new specified image
     *
     * @param user
     * @param duke
     */
    public void setFace(Image user, Image duke) {
        if (!user.isError()) {
            this.userImage = user;
        }
        if (!duke.isError()) {
            this.dukeImage = duke;
        }
    }

    public static GuiDataController getSingleton() {
        if (singleton == null) {
            singleton = new GuiDataController();
        }
        return singleton;
    }

}
