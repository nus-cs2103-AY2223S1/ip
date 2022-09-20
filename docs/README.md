# PoliteDuke User Guide


(Foreword: The format of this User Guide was adapted from that of AB3.)

PoliteDuke is a simple-to-use task manager that allows the user to add and manage one's tasks efficiently.
This user guide serves as a beginner's guide to the basic user controls that the app has to offer. Feel 
free to incorporate this into your daily scheduling, and do provide any feedbacks for improvements! :)

The name _PoliteDuke_ was inspired by the widely popular 'Polite Cat' meme; true to the name, you will be
interacting with a morph of Polite Cat himself!

-------------

## Features 

In the latest version of PoliteDuke, we support three broad categories of tasks:
1) ToDo task
2) Deadline task (with a specified '__by__' date)
3) Event task (with a specified '__at__' date)

Here's a glossary of the features that we have to support the user:
- Add task
- List tasks
- Delete task
- Mark task
- Unmark task
- Find task
- Tag task
- Untag task
- Bye

### Adding task - `todo`, `deadline`, `event`

Adds a task to the user's task list. 

Note:
- There is no explicit 'add' function: the user has to specify
the task type ("todo", "deadline", "event"), followed by description of the task.
- A `DATE` has to be specified for Deadline/Event tasks, in the following format: `DD-MM-YYYY HHmm`
  - e.g. `01-02-2022 1234` denotes 1 February 2022, 12.34p.m. (SGT)
- Each task comes with two markers:
  1) Task Type: `[T]`, `[D]`, `[E]` for ToDo, Deadline and Event tasks respectively
  2) The second marker denotes if a task is done. This will be explained in the `mark` section below.

The command format for each task type is as follows:

- Add ToDo task: `todo [DESCRIPTION]`
- Add Deadline task: `deadline [DESCRIPTION] /by [DATE] `
- Add Event task: `event [DESCRIPTION] /at [DATE] `

Examples: 
- `todo join hockey training`
- `deadline math assignment /by 30-08-2022 2359`
- `event meet Jessie /at 31-08-2022 1300`

### Listing tasks - `list`

Returns a list of tasks that have been pre-loaded or saved into the list.

Format: `list`

(The app comes with some pre-loaded tasks, so use `list` and alter them as you like with
other commands!)

### Deleting task - `delete`

Deletes a task with a specified index (in`list`)

Format: `delete [INDEX of task to delete]`

Example: `delete 3` will delete the third task in the tasklist (ordering is as seen in`list`)

Remark for user: An index that is larger than the size of the tasklist will not be registered.

### Marking task as done - `mark`

Marks a task with a specified index (in`list`) as done. 

Note: 
- The mark status is the second marker for a task:
  - [ X ] denotes that a task is done
  - [   ] denotes that a task is yet to be done

Format: `mark [index of task to mark]`

Example: `mark 3` will mark the third task in the tasklist as done (ordering is as seen in `list`)

Reminder: Similar to `delete`, an index that is larger than the size of the tasklist will not be registered.

### Marking task as undone - `unmark`

Marks a task with a specified index (in`list`) as done.

Format: `unmark [INDEX of task to unmark]`

Everything for `mark` applies to `unmark` as well, other than the change in the command name.

### Finding task with a specified keyword - `find`

Finds all tasks in the tasklist with a specified keyword. (__Note__: a phrase will work as well!)

Format: `find [KEYWORD]`

Example: Say there exists a task as follows: `event donut day /at 14-09-2022 1230`. 

The command `find donut d` returns the task, along with any other task that has the keyphrase `donut d`.

Of course, keying `find donut` will work as well.

### Tagging task - `tag`

Tags a task with the specified index (in`list`) with your favourite #hashtag. 

Format: `tag [INDEX of task to tag] [your TAG of choice]`

Example: `tag 3 #tgif` will add a tag of "#tgif" onto the third task in tasklist.

Note: 
- A task may have multiples tags, with no limits! (But be prudent with them, lest your
list looks too cluttered :))
- There is no need to include `#` when tagging the task; the hash is auto-included.

### Untagging task - `untag`

Removes the most recently added tag from the task with the specified index (in`list`)

Of course, if your task has no tags to begin with, then calling this command is futile. :)

Format: `untag [INDEX of task to tag] [your TAG of choice]`

Note: Everything else that applies to `tag` would apply for `untag` as well.

### Biding farewell to PoliteDuke - `bye`

Ends the app.

Format: `bye`

### Adding location tags (coming to you in the near future)

Details to come...

## Command summary

Action | Format, Examples
--------|------------------
**Add**    | `todo [DESCRIPTION]`<br> `deadline [DESCRIPTION] /by [DATE] `<br>`event [DESCRIPTION] /at [DATE] `
**List**   | `list`
**Delete** | `delete [INDEX]`<br> e.g., `delete 1`
**Mark**   | `mark [INDEX]`<br> e.g., `delete 2`
**Unmark** | `unmark [INDEX]`<br> e.g., `delete 3`
**Find**   | `find [KEYWORD] `<br> e.g., `find donut`
**Tag**    | `tag [INDEX] [HASHTAG]`<br> e.g. `tag 4 TGIF`
**Untag**  | `untag [INDEX]`<br> e.g. `untag 5`
**Bye**    | `bye`

