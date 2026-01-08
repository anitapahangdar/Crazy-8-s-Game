# Crazy 8s – Java Console Game

A Java-based console implementation of the classic card game *Crazy 8s*, where a user plays against the computer.  
The game includes user accounts, win tracking using file I/O, and full input validation.

---

## Features

- Classic **Crazy 8s gameplay**
- Player vs Computer
- **Account system**
  - Create a new account
  - Log in to an existing account
- **Persistent win tracking** using a text file
- Input validation for all user actions
- Randomized deck shuffling
- Clear, interactive console UI

---

## Technologies Used

- **Java**
- Java File I/O (`FileReader`, `BufferedReader`, `PrintWriter`)
- Object-oriented programming principles
- Arrays and control structures
- Random number generation

---

## How to Play

1. Run the program.
2. Log in with an existing account **or** create a new one.
3. You and the computer are each dealt 7 cards.
4. Players take turns:
   - Play a card that matches the **rank or suit** of the discard pile
   - Or draw from the deck if no valid card exists
5. The first player to run out of cards **wins**.
6. Your total number of wins is saved and updated automatically.

---

## How to Run

1. Clone the repository
2. Open the project in an IDE (IntelliJ, Eclipse, VS Code)
3. Make sure information.txt exists in the project directory.
4. Run.
   ```bash
   git clone https://github.com/your-username/crazy8s-java.git
