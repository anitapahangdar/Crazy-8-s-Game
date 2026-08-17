# Crazy 8s — Java Console Game

A console-based Crazy 8s card game created by **Anita Pahangdar**. The project demonstrates Java fundamentals through a complete player-versus-computer game loop, account-file handling, randomized card dealing, and defensive input validation.

## Highlights

- Standard 52-card deck generated in code
- Fisher–Yates deck shuffling
- Seven-card starting hands
- Player-versus-computer turns
- Rank, suit, and wild-eight matching
- Local account creation and login
- Session win count
- Console input validation

## Technology

- Java
- Arrays and control flow
- Methods and procedural decomposition
- File I/O
- Random number generation
- Console interaction

## Run locally

### Requirements

Install a Java Development Kit (JDK) 8 or newer.

From the repository root:

```bash
javac AnitaPahangdarCrazy8.java
java AnitaPahangdarCrazy8
```

The program creates a local `information.txt` file when first launched. That file stores accounts created on your computer and is intentionally excluded from Git.

## How to play

1. Create a local account or sign in to one you previously created.
2. The computer and player each receive seven cards.
3. On your turn, play a card matching the discard pile's rank or suit, or play an eight.
4. Draw a card when you cannot or do not want to play.
5. The first participant to empty their hand wins.

## Project structure

```text
.
├── AnitaPahangdarCrazy8.java  # Complete game implementation
├── .gitignore                 # Local data and Java build exclusions
└── README.md
```

## Data and security note

This is an educational console project, not a production authentication system. Local account passwords are stored as plain text in `information.txt`. Do not reuse a real password. The data file is excluded from the repository so local account information is not published.

## Current limitations

- Win totals are displayed during a session but are not yet written back to the account file.
- Drawing assumes cards remain in the original deck.
- A wild eight does not currently prompt the player to choose a new suit.
- The application runs one game per program launch.

## Possible next steps

- Separate cards, players, accounts, and game state into classes
- Hash locally stored passwords
- Persist updated win totals safely
- Reshuffle the discard pile when the deck is exhausted
- Add automated tests for card matching and hand management

## Author

**Anita Pahangdar**

Created as a Java course project.
