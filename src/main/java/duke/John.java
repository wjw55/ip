package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class John {

//    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static Storage storage;
    private Ui ui;
    private TaskList tasks;

    public John(String FILE_PATH) throws IOException {
        storage = new Storage(FILE_PATH);
        ui = new Ui();
        tasks = new TaskList(storage.Load());

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
//        String line;
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) {
//            try {
//                line = in.nextLine();
//                if (line.equals("bye")) {
//                    break;
//                }
//                String[] words = line.split(" ");
//                } else if (words[0].equals("mark")) {
//                    if (words.length < 2) {
//                        throw new DukeException("Please specify which task you want to mark.");
//                    }
//                    int item = Integer.parseInt(words[1]) - 1;
//                    if (item > tasks.size() - 1) {
//                        throw new DukeException("This task does not exist.");
//                    }
//                    tasks.get(item).markAsDone();
//                    System.out.println("Nice! I've marked this task as done: ");
//                    System.out.println(tasks.get(item));
//                } else if (words[0].equals("unmark")) {
//                    if (words.length < 2) {
//                        throw new DukeException("Please specify which task you want to unmark.");
//                    }
//                    int item = Integer.parseInt(words[1]) - 1;
//                    if (item > tasks.size() - 1) {
//                        throw new DukeException("This task does not exist.");
//                    }
//                    tasks.get(item).markAsUndone();
//                    System.out.println("OK, I've marked this task as not done yet: ");
//                    System.out.println(tasks.get(item));
//                    storage.Save(tasks);
//
//                } else {
//                    throw new DukeException("OOPS! Sorry I don't understand this :(");
//                }
//            } catch (DukeException | IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
        new John(java.nio.file.Paths.get("data", "john.txt").toString()).run();
    }
}
