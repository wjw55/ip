package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class John {
    private static Storage storage;
    private Ui ui;
    private TaskList tasks;

    public John(String FILE_PATH) throws IOException {
        storage = new Storage(FILE_PATH);
        ui = new Ui();
        tasks = new TaskList(storage.Load());
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
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

    public static void main(String[] args) throws DukeException, IOException {
        new John(java.nio.file.Paths.get("data", "john.txt").toString()).run();
    }
}
