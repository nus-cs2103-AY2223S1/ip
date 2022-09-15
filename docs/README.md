# Duke User Guide
## How to Use
For each command format, **bold** words are fixed, which means you cannot change them and their relative position. However, you can replace \<angle brackets\> with real arguments. Command word and arguments should be separated by at least one white space, but no end-of-line characters.
## Command List
| Command | Format | Description |
|----|-----|-----|
| Add an event task | **event** \<description\> /at \<yyyy-MM-dd HH:mm:ss\> | Add an event that takes place at year **_yyyy_**, month **_MM_**, day **_dd_**, hour **_HH_**, minute **_mm_**, and second **_ss_**. \<description\> is a paragraph that describes what to do, and can have any length and any number of whitespace characters. However, it cannot be empty or blank. |
| Add a deadline task | **deadline** \<description\> /by \<yyyy-MM-dd HH:mm:ss\> | Add a deadline at year **_yyyy_**, month **_MM_**, day **_dd_**, hour **_HH_**, minute **_mm_**, and second **_ss_**. \<description\> is a paragraph that describes what to do, and can have any length and any number of whitespace characters. However, it cannot be empty or blank. |
| Add a to-do task | **todo** \<description\> | Add a to-do task that does not have a specific deadline to meet or a specific date and time. It just has to be done some time. \<description\> is a paragraph that describes what to do, and can have any length and any number of whitespace characters. However, it cannot be empty or blank. |
| List all tasks saved | **list** | List all tasks, along with basic information about them. You can see the status (whether it is done or not) and date and time (if any). Note that date and time will be displayed in a different format (**yyyy/MM/dd HH:mm:ss**) from that one in which you input. |
| Delete a task | **delete** \<index\> | Delete the task corresponding to \<index\>, which should be a one-based integer index. After this operation, you no longer see this task in the list. |
| Mark a task as done | **mark** \<index\> | Mark the task corresponding to \<index\> as done. \<index\> should be a one-based integer index. Done tasks are indicated by an "X" |
| Mark a task as not done | **unmark** \<index\> | Mark the task corresponding to \<index\> as not done. \<index\> should be a one-based integer index. Undone tasks are indicated by a whitespace. |
| Sort the list | **sort** | Sort the tasks in a ascending chronological order. Tasks without date and time, such as to-do tasks are sorted to the end of list. More urgent tasks are closer to the top, for your convenience. |
| Find some tasks | **find** \<keyword\> | List all tasks that contain the \<keyword\>, which can be arbitrarily long and can have whitespaces. |
| Exit | **bye** | Save the list and exit the program immediately. |