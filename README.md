Name: Youssef Megahed
ID: 900192072
Section: 3:30PM

# Description

The program is implemented as a GUI using Java Swing. Upon running, the initial page asks the user to input the names of the two players. Error handling for identical names is implemented. The user can then choose between Manual and Random modes and press the button to move to the next screen.

![image](https://user-images.githubusercontent.com/64082427/192876144-76052aed-9c49-400a-8e22-a44e11c2155e.png)


If manual mode is selected, the user can enter the winner of each point and see the score table dynamically update with each entry. Error handling for invalid player names is implemented. When a game ends in a scoring mode, the scoring for this mode is paused until the other mode completes the game. After each match ends, the judges' decisions are printed to the screen and the text field and the button become locked (no more input allowed).

![image](https://user-images.githubusercontent.com/64082427/192876432-29747f17-f625-48e7-b2d1-4d1575693031.png)

![image](https://user-images.githubusercontent.com/64082427/192876943-decd40ed-924f-40d5-8734-5819d0a95185.png)


If random mode is selected, a scrollable table is produced with every match round displayed and the judges' decisions are displayed on top. The games are randomized.

![image](https://user-images.githubusercontent.com/64082427/192877291-75e8da01-3495-4556-ad46-9f5204baa824.png)


# Code overview:

Inside the main class "SquashJudge", there are four functions: initialScreen(), generateGame(), getInput(), processPAR(), and processHIHO().

# initialScreen():

- Renders the initial screen of the program with all the text fields, buttons, etc. 
- Handles invalid input from the user
- Calls the appropriate function based on the user's selections

# generateGame():

- Is called when user chooses random mode. Generates the scrollable table and randomizes a game by repeatedly generating a random number (either 1 or 2) and calling processHIHO and processPAR with the point winner being player one if the number generated is 1 or player two if the number generated is 2.
- Ensures loop is exited when both matches conclude.

# getInput()

- Is called when user chooses manual mode.
- Renders the input fields and the score table on the screen 
- Accepts and validates input player names from the user
- Dynamically updates the table with each point entry by rerunning with every new input
- Calls processHIHO and processPAR with the inputted player name to process every point

# processPAR() and processHIHO()

- Take as a parameters the name of the player who won the point
- Increment the necessary attributes and handle the logic of each game mode
- Return the following:
  - 1 if player one won a point
  - 2 if player two won a point
  - 3 if player one won a game
  - 4 if player two won a game
  - 5 if player one won the match
  - 6 if player two won the match
- The returned values are then handled in the calling functions (getInput() and generateGame()) to determine what to present to the user
