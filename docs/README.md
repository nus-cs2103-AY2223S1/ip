## Duke
> “Your mind is for having ideas, not holding them.” – David Allen

Duke frees your mind of  of having to remember things u need to, its
1. *text-based*
2. ***VERY*** user friendly
3. ~~*USEFUL*~~ ***SUPER USEFUL*** at organising your tasks


## Quick Start
1. Ensure you have Java 11 or above installed in your computer.
2. Download it from [here](https://github.com/JordanChua/ip/releases)
3. Copy the file to the folder you want to use as the home folder for your chat bot.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

<img src="https://user-images.githubusercontent.com/88762462/190427386-0ceb27f9-33d7-413e-91b2-05bd48e1e46a.png"  width="400">

## Command Summary
Type the command in the textbox below and click the send button to input the command.
Here is a summary of the available commands along with some examples:
- `add` - add a todo/Deadline/Event task. Note that input in square brackets [...] is only for deadlines/events <br/>

  Format : `add <task> [dateTime]`
  
  Note that the format of the date and time **MUST** be of the format dd/MM/yyyy HHmm<br/>

  E.g. `add return book`, `add event Project meeting/at 17/09/2022`
- `mark/unmark` - marks/unmarks a task to indicate whether a task is done or not done. <br/>

  Format : `mark <taskIndex>` <br/>

  E.g. `mark 3`, `unmark 6`

- `find` - finds tasks that have the keyword we are trying to find <br/>

  Format: `find <Keyword>` <br/>

  Example: `find book`

- `delete` - deletes a task from our task list <br/>

  Format: `delete <taskIndex>` <br/>

  Example: `delete 2`

- `list` - lists the tasks that have been added to our task list <br/>

  Format: `list`

- `sort` - outputs the list of deadlines/events in chronological order <br/>

  Format: `sort deadlines` OR `sort events`

- `bye` - exits the program once we are done <br/>

  Format: `bye`
