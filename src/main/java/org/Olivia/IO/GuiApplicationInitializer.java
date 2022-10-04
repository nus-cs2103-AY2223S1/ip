package org.Olivia.IO;

import javafx.scene.image.Image;
import org.Olivia.Dispatchers.GuiEventDispatcher;
import org.Olivia.calendar.Calendar;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;

public class GuiApplicationInitializer {
    @Nullable
    public static Image getUserIcon() {
        try {
            InputStream userIco = GuiApplicationInitializer.class.getResourceAsStream("/images/User.jpg");
            assert userIco != null;
            return new Image(userIco);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static Image getOliviaIcon() {
        try {
            InputStream oliviaIco = GuiApplicationInitializer.class.getResourceAsStream("/images/Olivia.jpg");
            assert oliviaIco != null;
            return new Image(oliviaIco);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @NotNull
    public static GuiEventDispatcher setUpGuiEventDispatcher() {
        GuiEventDispatcher ans = new GuiEventDispatcher(new Calendar(), new FileHandler());
        try {
            ans.initialize();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        return ans;
    }
}
