<a name="getting-started"></a>
## Get started with Luna ⛅️
1. Download `luna-v1.0.jar` from this [page](https://github.com/fannyjian/ip/releases/tag/A-Release)
2. Navigate to the directory containing the `jar` file in terminal
3. Run `java -jar luna-v1.0.jar`
4. Give Luna commands to complete your tasks!
5. Say `bye` to Luna to exit and save your tasks for next time 💫

**⚡️ Please only use `bye` to exit Luna instead of closing the window, 
or Luna will not be able to save your tasks to storage! ⚡️** 
<br/><br/>

<a name="user-guide"></a>
## User Guide 🌙

### 1. `todo` 🌸

Create a new 'todo'.

Tell Luna your command in the format: 

`todo <task description>`

Example:
```
Input: 
    todo ip
Output:
    Luna has added:
    [T][ ] ip
    1 task(s) left in your list 🌻
```
---
### 2. `deadline` 🌺
Create a new `deadline` task with a date to complete the task by.

Tell Luna your command in the format:

`deadline <task description> /by <yyyy-MM-dd>`

Example:
```
Input: 
    deadline wash laundry /by 2022-09-16
Output: 
    Luna has added:
    [D][ ] wash laundry BY 16 Sep 2022
    2 task(s) left in your list 🌻
```
---
### 3. `event` 🌼
Create a new `event` with its occurrence date.
Tell Luna your command in the format:

`event <event description> /at <yyyy-MM-dd>`

Example:
```
Input: 
    event meet bestie /at 2022-09-16
Output: 
    Luna has added:
    [E][ ] meet bestie AT 16 Sep 2022
    3 task(s) left in your list 🌻
```
---
### 4. `list` 💐

View all your tasks.

Tell Luna your command in the format:

`list`

Example:
```
Input: 
    list
Output: 
    ☀️ Stuff you have to do! ☀️
        1. [T][ ] ip
        2. [D][ ] wash laundry BY 16 Sep 2022
        3. [E][ ] meet bestie AT 16 Sep 2022
```
---
### 5. `mark` 🌹
Update the status of a specific task in your list as completed.

Tell Luna your command in the format:

`mark <task number>`

Example:
```
Input: 
    mark 2
Output: 
    Marked as completed 🌈️
    [D][✧] wash laundry BY 16 Sep 2022
```
### 4. `unmark` 🥀
Update the status of a specific task in your list as uncompleted.

Tell Luna your command in the format:

`unmark <task number>`

Example:
```
Input: 
    unmark 2
Output: 
    Marked as uncompleted 🌩
    [D][ ] wash laundry BY 16 Sep 2022
```
---
### 5. `find` 🌷
Search for specific tasks in your list by a keyword (partial match supported).

Tell Luna your command in the format:

`find <keyword>`

Example:

```
Input:
    find laundry
Output:
    ☁️ Here are the tasks Luna has found! ☁️
        1. [D][ ] wash laundry BY 16 Sep 2022
```
---
### 6. `bye` 💐
Exit Luna. 

**⚡️ Please only use this command to exit Luna instead of closing the window, 
or Luna will not be able to save your tasks to storage! ⚡️** 

Tell Luna your command in the format:

`bye`

---
<div align="center"> . ❍  ❃ ☆  ✶ ❅  🌙 Goodbye from Luna 🌙  ❅ ✶  ☆ ❃  ❍  . </div>
