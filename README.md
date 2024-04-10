# SNAKE GAME

This repository is the final project (Java GUI) of the Object Oriented Programming Class, Informatics Engineering, Universitas Padjadjaran.

[Challenge Guide](challenge-guideline.md)

The Snake game is a game where the player controls a line that grows in length, with the line itself being the main obstacle.

## Credit

| NPM | Name |
| ------------ | -------------------------- |
| 14080190009 | Farhan Gunadi |
| 14080190025 | Aghniya Abdurrahman Mannan |
| 14080190037 | Bagas Adi Firdaus |

## Change log

- **[Sprint Planning](changelog/sprint-planning.md) - (18 November 2020)**

  - Divide tasks for the first week
  - Create Sprint 1

- **[Sprint 1](changelog/sprint-1.md) - (18 November 2020 - 24 November 2020)**

  - Making board games
  - Create a snake object and place it on the board
  - Create apple objects and place them on the board randomly
  - Make movement of snake object

- **[Sprint 2](changelog/sprint-2.md) - (25 November 2020 - 02 December 2020)**

  - Create a scoring system
  - Make game ending condition
  - Create application start and end conditions

- **[Sprint 3](changelog/sprint-3.md) - (03 December 2020 - 09 December 2020)**

  - Create application start and end conditions
  - Created an additional condition where the snake's movement is accelerated if the user presses the shift key
  - Cleaned up the code and complements README.md

## Running Application

The main file used in this program is SnakeGame.java.

**1. Open Terminal in the IDE you are using**

**2. Run the Gameplay.java class**

```
javac src/Gameplay.java
```

**3. Run the SnakeGame.java class**

```
javac src/SnakeGame.java
```

```
java src/SnakeGame.java
```

**4. Enjoy the game**

## Class Used

**1. Main Application** - `SnakeGame.java`

- The main program to handle Display and Gameplay

**2. Gameplay** - `Gameplay.java`

- Class for running game process
- 13 Variable Classes
  - snake head: Image Icon
  - Timer: Timer
  - delay: to
  - SnakeBody: ImageIcon
  - speedUp: AtomicBoolean
  - snakeHeadXPos: int
  - appleImage: ImageIcon
  - xPos: int
  - yPos: int
  - titleImage: ImageIcon
  - High score: String
  - arrowImage: ImageIcon
  - shiftImage: ImageIcon
- 6 Methods
  - **Gameplay()** - For constructor classes
  - **paint(g)** - For frame/UI design
  - **drawString(g, text, x, y)** - to display a string on the screen with \n in it
  - **actionPerformed(e)** - Used to set the snake's movement
  - **keyPressed(e)** - To set conditions when pressing keyboard keys
  - **keyReleased (e)** - To set when user release/press shift

**3. Score** - `Score.java`

- Class to set the score game
- 1 Variable Class
  - score: be
- 7 Methods
  - **Score()** - For constructor classes
  - **increaseScore()** - To increase/increase score
  - **resetScore()** - To reset score
  - **getScore()** - to return value to Gameplay view
  - **getHighScore()** - Function to get HighScore
  - **sortHighScore()** - to sort high scores
  - **saveNewScore()** - Function to write new score in file

**4. Snake** - `Snake.java`

- Class to manage snake
- 9 Variable Class
  - snakexLength: int []
  - snake length: int []
  - lengthOfSnake: int
  - move: to
  - left: boolean
  - right: boolean
  - top: boolean
  - bottom: boolean
  - death: boolean
- 10 Methods
  - **Snake()** - For constructor classes
  - **moveRight()** - To move the snake to the right
  - **moveLeft()** - To move the snake to the left
  - **moveUp()** - To move the snake up
  - **moveDown()** - To move the snake down
  - **dead()** - Function turns off so as not to repeat writing code many times
  - **movementRight()** - For snake movement to the right
  - **movementLeft()** - For snake movement to the left
  - **movementUp()** - For snake movement up
  - **movementDown()** - For downward movement of the snake

**5. Apple** - `Apple.java`

- Class to set the position of the apple when the game starts
- 2 Variable Class
  - applexPos: int[]
  - appleyPos: int []
- 0 Method

## UML

![UML](/images/UML_Project_Snake.png)

## Leading Assumptions and App Design Details

- App Design
  - The game board is designed with a size of 100x100 cells.
  - Game will start when user press SPACEBAR
- Score
  - The game starts with a score = 0 and will increase by 1 every time you eat an apple
  - When the program is first run, the highscore will be = 0
  - High score will be saved when snake has been played to death (game over)
  - The program will store the 10 highest highscores
  - Highscore will not disappear even if the application is closed
  - Score will be saved in `highscore.dat`
- Snake
  - The initial length of the snake before it starts = 5 cells and will increase by 1 cell every time it eats an apple
  - The snake will die when it hits the wall or hits the body
  - Boostspeed when pressing SHIFT on the keyboard
  - Every time the score reaches a multiple of 5, the snake moves faster
   - Boostspeed adjusts the speed of the snake
- Apple
   - Apples will appear randomly when the game starts
   - Apple size 1 cell

## UI

1. **Before the game starts**
    ![Start Game](/images/UI.png)

1. **After the game ends**
    ![End Game](/images/UI2.PNG)
