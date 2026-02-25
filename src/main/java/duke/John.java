package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The main entry point of the John application.
 *
 * This class is responsible for initializing the core components
 * (Storage, Ui, and TaskList) and running the main command loop.
 */
public class John {
    private static Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a new John application instance.
     *
     * Initializes the storage using the specified file path,
     * creates the user interface, and loads the saved tasks
     * from storage into the task list.
     *
     * @param FILE_PATH The file path used to store and load task data.
     * @throws IOException If an error occurs while loading the file.
     */
    public John(String FILE_PATH) throws IOException {
        storage = new Storage(FILE_PATH);
        ui = new Ui();
        tasks = new TaskList(storage.Load());
    }

    /**
     * Starts the main execution loop of the application.
     *
     * Displays the welcome message and continuously reads user input,
     * parses it into a command, executes the command, and checks
     * whether the program should exit.
     *
     * Any DukeException or IOException encountered during execution
     * will be caught and displayed to the user.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method that launches the John application.
     *
     * @param args Command line arguments (not used).
     * @throws DukeException If a Duke-related error occurs.
     * @throws IOException If a file I/O error occurs.
     */
    public static void main(String[] args) throws DukeException, IOException {
        new John(java.nio.file.Paths.get("data", "john.txt").toString()).run();
    }
}
