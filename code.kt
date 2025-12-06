package com.example.flashcards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// -----------------------------
// UI STATE
// -----------------------------
data class FlashcardUiState(
    val currentWord: String = "Kotlin",
    val currentDefinition: String = "A statically typed programming language",
    val isFlipped: Boolean = false,
    val cardCount: Int = 1
)

// -----------------------------
// VIEWMODEL
// -----------------------------
class FlashcardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FlashcardUiState())
    val uiState: StateFlow<FlashcardUiState> = _uiState

    fun flipCard() {
        val state = _uiState.value
        _uiState.value = state.copy(isFlipped = !state.isFlipped)
    }

    fun nextCard() {
        val state = _uiState.value
        _uiState.value = state.copy(
            cardCount = state.cardCount + 1,
            isFlipped = false
        )
    }

    fun resetCards() {
        _uiState.value = FlashcardUiState()
    }
}

// -----------------------------
// SCREEN (STATEFUL)
// -----------------------------
@Composable
fun FlashcardScreen(
    viewModel: FlashcardViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    FlashcardContent(
        word = uiState.currentWord,
        definition = uiState.currentDefinition,
        flipped = uiState.isFlipped,
        count = uiState.cardCount,
        onFlip = viewModel::flipCard,
        onNext = viewModel::nextCard,
        onReset = viewModel::resetCards,
        modifier = Modifier
    )
}

// -----------------------------
// STATELESS CONTENT
// -----------------------------
@Composable
fun FlashcardContent(
    word: String,
    definition: String,
    flipped: Boolean,
    count: Int,
    onFlip: () -> Unit,
    onNext: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Center card + buttons
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = if (flipped) definition else word,
                    modifier = Modifier.padding(32.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Button(onClick = onFlip) {
                    Text("Flip")
                }
                Button(onClick = onNext) {
                    Text("Next")
                }
                Button(onClick = onReset) {
                    Text("Reset")
                }
            }
        }

        // Top-right card counter
        Text(
            text = "Card: $count",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
