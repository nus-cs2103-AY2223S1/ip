package duke.command.response;

public class CommandResponse {

    private final String responseStr;
    private final boolean isSave;
    private final boolean isTerminate;

    /**
     * Constructor for CommandResponse class.
     *
     * @param responseStr response string.
     * @param triggerSave boolean to trigger a save to cache.
     * @param triggerTerminate boolean to trigger a termination.
     */
    public CommandResponse(String responseStr, boolean triggerSave, boolean triggerTerminate) {
        assert !responseStr.isBlank() : "Command response should not be blank!";

        this.responseStr = responseStr;
        this.isSave = triggerSave;
        this.isTerminate = triggerTerminate;
    }

    public String getResponseStr() {
        return responseStr;
    }

    public boolean isTriggerSave() {
        return isSave;
    }

    public boolean isTriggerTerminate() {
        return isTerminate;
    }
}
