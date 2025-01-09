package com.esiea.quizzapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.esiea.quizzapp.data.AnswerEntity

@Composable
fun QuizScreen(
    question: String,
    answers: List<AnswerEntity>,
    score: Int,
    onAnswerSelected: (Boolean) -> Unit,
    onNextClicked: () -> Unit
) {
    var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {

            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Score: $score",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Black
                )
            }


            Text(
                text = question,
                modifier = Modifier.padding(vertical = 16.dp),
            )

            // Liste des réponses
            Column(modifier = Modifier.fillMaxWidth()) {
                answers.forEachIndexed { index, answer ->
                    Button(
                        onClick = {
                            if (selectedAnswerIndex == index) {
                                selectedAnswerIndex = null // Désélectionner
                            } else if (selectedAnswerIndex == null) {
                                selectedAnswerIndex = index // Sélectionner
                                onAnswerSelected(answer.isCorrect)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedAnswerIndex == index) Color.Green else MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        enabled = selectedAnswerIndex == null || selectedAnswerIndex == index
                    ) {
                        Text(text = answer.answerText)
                    }
                }
            }

            Button(
                onClick = {
                    if (selectedAnswerIndex != null) {
                        selectedAnswerIndex = null
                        onNextClicked()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Next",
                    color = if (selectedAnswerIndex != null) Color.White else Color.DarkGray
                )
            }
        }
    }
}




