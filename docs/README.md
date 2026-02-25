# John Chatbot User Guide

Welcome to **John**, your personal task manager chatbot! John helps you manage tasks, deadlines, and todos quickly and easily, all through simple commands.

---

## Quick Command Reference

| Command    | Description                           | Example                                |
|------------|---------------------------------------|----------------------------------------|
| `todo`     | Add a task without a deadline         | `todo read book`                       |
| `deadline` | Add a task with a deadline            | `deadline return book /by 2019-12-02`  |
| `event`    | Add a task for an event with duration | `event team meeting /from 6pm /to 8pm` |
| `list`     | List all tasks                        | `list`                                 |
| `mark`     | Mark a task as done                   | `mark 1`                               |
| `unmark`   | Unmark a task as not done             | `unmark 2`                             |
| `delete`   | Delete a task                         | `delete 2`                             |
| `find`     | Search tasks by keyword               | `find meeting`                         |
| `bye`      | Exit John                             | `bye`                                  |

---

## Features

1. **Add Tasks**
    - **Todo**: Add a task without a deadline.
      ```
      todo <task description>
      ```
      Example:
      ```
      todo read book
      ```

    - **Deadline**: Add a task with a deadline.
      ```
      deadline <task description> /by yyyy-MM-dd HHmm
      ```
      Example:
      ```
      deadline return book /by 2019-12-02 1800
      ```

    - **Event**: Add a task for an event with date/time.
      ```
      event <task description> /from start time /to end time
      ```
      Example:
      ```
      event team meeting /from 5pm /to 6pm
      ```

2. **List Tasks**

list

Shows all tasks currently in your list with their status (done/not done).

3. **Mark Tasks as Done**

mark <task number>

Marks a task as completed.

4**Unmark Tasks as not Done**

unmark <task number>

Unmarks a task as not completed.

4. **Delete Tasks**

delete <task number>

Removes a task from the list.

5. **Find Tasks**

find <keyword>

Searches your task list for tasks containing the keyword.

6. **Exit John**

bye

Exits the chatbot with a goodbye message.

---

## Using John

1. **Starting John**
- Run the John program (`java -jar John.jar`) or launch via your IDE.

2. **Adding a Task**
- Type the appropriate command (`todo`, `deadline`, or `event`) followed by the task description.
- For deadlines and events, specify the date using `yyyy-MM-dd` (and time `HHmm` if applicable).

3. **Managing Tasks**
- Use `list` to see all tasks.
- Mark tasks done with `done <number>`.
- Delete tasks with `delete <number>`.

4. **Searching**
- Use `find <keyword>` to quickly locate tasks.

5. **Exiting**
- Type `bye` to exit. John will say goodbye and save your tasks automatically.

---

## Notes

- **Date Format:** Always use `yyyy-MM-dd` for dates. For events with time, use `yyyy-MM-dd HHmm`.
- **Task Numbers:** The number used in `done` or `delete` commands corresponds to the number shown in `list`.
- **Storage:** All tasks are saved automatically. You can restart John and your task list will persist.

---

## Example Session


todo read book
deadline return book /by 2019-12-02
event team meeting /from 5pm /to 6pm
list
mark 1
delete 2
find meeting
bye


---

Enjoy using **John** to manage your tasks efficiently! 🎉