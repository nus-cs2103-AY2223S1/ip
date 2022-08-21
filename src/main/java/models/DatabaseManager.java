package models;

import database.Database;
import database.TaskDatabase;
import exceptions.DukeException;

import java.util.HashMap;

/**
 * Fictional database manager that indexes the "tables" in a database
 *
 * @author Emily Ong Hui Qi
 */
public class DatabaseManager {
    //
    private final HashMap<DatabaseType, ? extends Database> databases;

    public DatabaseManager() throws DukeException {
        this.databases = new HashMap<>(){{
            put(DatabaseType.TASK, new TaskDatabase());
        }};

        for (Database database : this.databases.values()) {
            database.initialize();
        }
    }

    public TaskDatabase getTaskDatabase() {
        return (TaskDatabase) this.databases.get(DatabaseType.TASK);
    }
}
