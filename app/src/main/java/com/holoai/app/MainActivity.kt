package com.holoai.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class Persona(
    val name: String,
    val subtitle: String,
    val accent: Color,
    val symbol: String
)

private val personas = listOf(
    Persona("ULTRON", "Synthetic intelligence", Color(0xFFFF3038), "◉"),
    Persona("JOKER", "Chaos with a punchline", Color(0xFFB45CFF), "♠"),
    Persona("NARUTO", "Never give up", Color(0xFFFF8A28), "忍"),
    Persona("KAKASHI", "Quiet precision", Color(0xFF62C9FF), "⚡"),
    Persona("HULK", "Maximum power", Color(0xFF62D66B), "H"),
    Persona("CHATGPT", "Clear and focused", Color(0xFF58D6B0), "✦")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { HoloApp() }
    }
}

@Composable
private fun HoloApp() {
    var selected by remember { mutableStateOf(personas.first()) }
    var prompt by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("SYSTEM ONLINE • LOCAL INTERFACE") }
    var messages by remember { mutableStateOf(listOf("HOLO: Interface initialized. Connect an AI provider to enable responses.")) }

    val bg = Color(0xFF080A0F)
    val panel = Color(0xFF121722)

    MaterialTheme(colorScheme = darkColorScheme(
        primary = selected.accent,
        background = bg,
        surface = panel
    )) {
        Column(
            modifier = Modifier.fillMaxSize().background(bg).padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("HOLO AI", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Black)
                    Text("BEYOND THE INTERFACE", color = selected.accent, fontSize = 10.sp, letterSpacing = 2.sp)
                }
                Text("●", color = Color(0xFF51E59A), fontSize = 18.sp)
            }

            Spacer(Modifier.height(20.dp))

            Box(
                modifier = Modifier.size(190.dp).border(2.dp, selected.accent.copy(alpha = .8f), CircleShape)
                    .background(panel, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(selected.symbol, color = selected.accent, fontSize = 58.sp, fontWeight = FontWeight.Black)
                    Text(selected.name, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold, letterSpacing = 3.sp)
                    Text("●       ●", color = selected.accent, fontSize = 19.sp)
                }
            }

            Spacer(Modifier.height(14.dp))
            Text(selected.subtitle.uppercase(), color = selected.accent, fontSize = 11.sp, letterSpacing = 2.sp)
            Text(status, color = Color(0xFF8E9AAF), fontSize = 10.sp)

            Spacer(Modifier.height(18.dp))
            Text("PERSONA SELECT", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth().heightIn(max = 116.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(personas) { persona ->
                    OutlinedButton(
                        onClick = { selected = persona; status = "${persona.name} MODE ACTIVE" },
                        modifier = Modifier.fillMaxWidth(),
                        border = androidx.compose.foundation.BorderStroke(
                            if (selected == persona) 2.dp else 1.dp,
                            persona.accent.copy(alpha = if (selected == persona) 1f else .45f)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 7.dp)
                    ) {
                        Text("${persona.symbol}   ${persona.name}", color = if (selected == persona) persona.accent else Color.White)
                        Spacer(Modifier.weight(1f))
                        if (selected == persona) Text("ACTIVE", color = persona.accent, fontSize = 10.sp)
                    }
                }
            }

            Spacer(Modifier.height(14.dp))
            Text("CONSOLE", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start))
            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f).background(panel, RoundedCornerShape(14.dp)).padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(messages) { line ->
                    Text(line, color = Color(0xFFD7DFEA), fontSize = 13.sp)
                }
            }

            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = prompt,
                    onValueChange = { prompt = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Enter a command…") },
                    singleLine = true
                )
                Spacer(Modifier.width(8.dp))
                Button(onClick = {
                    if (prompt.isNotBlank()) {
                        messages = messages + "YOU: $prompt" + "HOLO: AI provider is not connected yet."
                        prompt = ""
                    }
                }) { Text("SEND") }
            }
            Spacer(Modifier.height(6.dp))
            Text("Foundation build • AI, voice and integrations not connected yet",
                color = Color(0xFF7D899B), fontSize = 9.sp)
        }
    }
}
