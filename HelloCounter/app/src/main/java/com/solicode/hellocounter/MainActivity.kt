package com.solicode.hellocounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}

@Composable
fun App() {
    MaterialTheme {
        Greeting()

    }
}

@Composable
fun Greeting() {
    var count by remember { mutableStateOf(0) }

    // Box = conteneur qui permet d'aligner son contenu
    Box(
        modifier = Modifier.fillMaxSize(),              // occupe tout l'écran
        contentAlignment = Alignment.Center             // centre le contenu
    ) {
        Button(onClick = { count++ }) {
            Text("Hello, count = $count")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    App()
}