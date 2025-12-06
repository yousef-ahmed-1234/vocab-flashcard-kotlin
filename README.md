# 📚 Flashcard App — Jetpack Compose + ViewModel

A simple Flashcard learning app built using **Jetpack Compose**, **StateFlow**, and **ViewModel**.  
It demonstrates state management, state hoisting, UI updates via flows, and basic component composition.

---

## 🚀 Features

- Flip between **word** and **definition**
- Navigate to the **next flashcard**
- **Reset** the deck to the first card
- Reactive UI using **StateFlow** and `collectAsState()`
- Stateless + stateful composable separation (state hoisting)
- Material 3 UI components

---

## 🧠 Architecture Overview

This project follows the same patterns used in modern Compose apps:

### ✔ ViewModel  
Handles all business logic:
- flipping cards  
- progressing to the next card  
- resetting the deck  

### ✔ `FlashcardUiState`  
A clean immutable state class containing:
- `currentWord`
- `currentDefinition`
- `isFlipped`
- `cardCount`

### ✔ Compose UI  
UI is split into:
- `FlashcardScreen()` → stateful, talks to ViewModel  
- `FlashcardContent()` → stateless, fully reusable UI  

This is identical to the **Tip Time**, **Cupcake**, and **Unscramble** architectures.

---

## 📂 Project Structure

