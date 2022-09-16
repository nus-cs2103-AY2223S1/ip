package hazell.ui;

import java.util.ArrayList;
import java.util.List;

import hazell.Hazell;

/**
 * A manager of multiple Ui objects.
 * Allows the bot to interface with the UiManager directly instead of handling each Ui object separately.
 */
public class UiManager implements UiInterface {

    private List<UiInterface> uiList;
    private Hazell hazell;

    /**
     * Creates a new UiManager object.
     */
    public UiManager() {
        uiList = new ArrayList<>();
    }

    /**
     * Gives a place for specifying an Ui object.
     * @param ui The Ui object
     */
    public void attachUiInstance(UiInterface ui) {
        uiList.add(ui);
    }

    @Override
    public void attachBotInstance(Hazell hazell) {
        this.hazell = hazell;
    }

    @Override
    public boolean hasNextUserInput() {
        return false;
    }

    @Override
    public String getNextUserInput() {
        return null;
    }

    @Override
    public void displayUserInput(String input) {
        for (UiInterface ui : uiList) {
            ui.displayUserInput(input);
        }
    }

    @Override
    public void displayBotResponse(String response) {
        for (UiInterface ui : uiList) {
            ui.displayBotResponse(response);
        }
    }

    @Override
    public void start() {
        for (UiInterface ui : uiList) {
            ui.start();
        }
    }


    /**
     * Does some actions when control is given.
     */
    public void step() {
        String userinput = null;
        for (UiInterface ui : uiList) {
            if (ui.hasNextUserInput()) {
                userinput = ui.getNextUserInput();
            }
        }
        if (userinput == null) {
            return;
        }
        String response = hazell.getResponse(userinput);
        for (UiInterface ui : uiList) {
            ui.displayUserInput(userinput);
            ui.displayBotResponse(response);
        }
    }

    @Override
    public void run() {
        uiList.get(0).run();
    }
}
