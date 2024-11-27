package com.dov.navigationformlistcompose
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun CalculatorScreen(name: String, navController: NavHostController) {
    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bonjour $name", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = firstNumber,
            onValueChange = { firstNumber = it },
            label = { Text("Saisir le premier nombre") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true

        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = secondNumber,
            onValueChange = { secondNumber = it },
            label = { Text("Saisir le second nombre") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = {
                    result = calculateOperation(firstNumber, secondNumber, '+')
                },
                modifier = Modifier.weight(1f).padding(end = 4.dp)
            ) {
                Text("+")
            }

            Button(
                onClick = {
                    result = calculateOperation(firstNumber, secondNumber, '-')
                },
                modifier = Modifier.weight(1f).padding(start = 4.dp)
            ) {
                Text("-")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(result, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("history") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Historique")
        }
    }
}

fun calculateOperation(first: String, second: String, operator: Char): String {
    return try {
        val firstNum = first.toDouble()
        val secondNum = second.toDouble()
        val result = when (operator) {
            '+' -> firstNum + secondNum
            '-' -> firstNum - secondNum
            else -> 0.0
        }
        val operation = "$first $operator $second = $result"
        ApplicationData.operationsHistory.add(operation)
        operation
    } catch (e: NumberFormatException) {
        "Veuillez remplir tous les champs"
    }
}