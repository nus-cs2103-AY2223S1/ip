package duke.main;

enum LaunchFlagErrMsg {
    FILE_NOT_FOUND_EXIT("Fatal Error! The system will exit abnormally!"),
    UNIMPLEMENTED_FLAG("Unimplemented flag %s.%n"),
    UNRECOGNISED_FLAG("Unrecognised flag %s.%n"),
    UNSPECIFIED_SAVE_FILE("No save file is specified!"),
    CONFLICTING_LAUNCH("Make up your mind! GUI or no GUI!");


    private final String msg;

    LaunchFlagErrMsg(String str) {
        msg = str;
    }

    @Override
    public String toString() {
        return msg;
    }
}
