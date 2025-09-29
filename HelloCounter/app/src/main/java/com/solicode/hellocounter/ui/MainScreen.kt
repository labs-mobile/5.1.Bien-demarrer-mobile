package com.solicode.hellocounter.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.solicode.hellocounter.R

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var greetingName by remember { mutableStateOf<String?>(null) }
    var count by remember { mutableStateOf(0) }

    // 🔹 Préparer les textes d’accessibilité (utilisés dans semantics)
    val decrementCd = stringResource(R.string.cd_decrement)
    val incrementCd = stringResource(R.string.cd_increment)
    val counterCd = stringResource(R.string.cd_counter_value, count)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1) Champ Prénom
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.label_firstname)) },
            placeholder = { Text(stringResource(R.string.ph_firstname)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth().testTag("tf_firstname")
        )

        // 2) Bouton Dire bonjour
        Button(
            onClick = {
                greetingName = name.takeIf { it.isNotBlank() }?.trim()
            },
            enabled = name.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_greet))
        }

        // 3) Message
        if (greetingName != null) {
            AssistChip(
                onClick = { },
                label = {
                    Text(stringResource(R.string.msg_greeting, greetingName!!))
                }
            )
        }

        // 4) Titre compteur
        Text(
            text = stringResource(R.string.title_counter),
            style = MaterialTheme.typography.titleMedium
        )

        // 5) Bloc compteur
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IconButton(
                onClick = { if (count > 0) count-- },
                enabled = count > 0,
                modifier = Modifier
                    .size(48.dp)
                    .semantics { contentDescription = decrementCd }
            ) {
                Icon(Icons.Filled.Remove, contentDescription = null)
            }

            Text(
                text = "$count",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .widthIn(min = 40.dp)
                    .semantics { contentDescription = counterCd }
            )

            IconButton(
                onClick = { count++ },
                modifier = Modifier
                    .size(48.dp)
                    .semantics { contentDescription = incrementCd }
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    }
}
