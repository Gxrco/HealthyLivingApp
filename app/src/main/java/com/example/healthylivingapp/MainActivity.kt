package com.example.healthylivingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.healthylivingapp.ui.theme.HealthyLivingAppTheme

class MainActivity : ComponentActivity() {

    data class Item(val name: String, val imageUrl: String)

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthyLivingAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    contentColor = Color.Blue
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        TopAppBar(
                            title = {
                                Text(
                                    text = "Healthy Living GT",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    ),
                                    modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center),
                                    color = Color.White
                                )
                            },
                            colors = TopAppBarColors(containerColor = Color.DarkGray, scrolledContainerColor = Color.Transparent, navigationIconContentColor = Color.Transparent, titleContentColor = Color.DarkGray, actionIconContentColor = Color.Transparent)
                        )
                    }
                        Column(
                            modifier = Modifier
                                .padding(vertical = 70.dp)
                                .fillMaxSize()

                        ) {
                            val items = remember { mutableStateListOf<Item>() }
                            val addItem: (Item) -> Unit = { item -> items.add(item) }

                            LabelAndPlaceHolders(addItem)
                            MyLazyColumn(items)
                        }
                    }
                }
            }
        }
    }


@Composable
fun LabelAndPlaceHolders(addItem: (MainActivity.Item) -> Unit) {
    var text1 by remember { mutableStateOf(TextFieldValue("")) }
    var text2 by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = text1,
            onValueChange = { text1 = it },
            label = { Text(text = "Nombre de receta") },
            placeholder = { Text(text = "Ingresa nombre") },
            modifier = Modifier
                .weight(1f)
        )

        TextField(
            value = text2,
            onValueChange = { text2 = it },
            label = { Text(text = "Imagen URL") },
            placeholder = { Text(text = "Ingresa URL") },
            modifier = Modifier
                .weight(1f)
        )
    }

    Button(
        onClick = {
            if (text1.text.isNotBlank() && text2.text.isNotBlank()) {
                addItem(MainActivity.Item(text1.text, text2.text))
                text1 = TextFieldValue("")
                text2 = TextFieldValue("")
            }
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.DarkGray
        )

    ) {
        Text("Añadir receta", color = Color.White)
    }
}

@Composable
fun MyLazyColumn(items: List<MainActivity.Item>) {
    LazyColumn {
        items(items) { item ->
            ItemCard(item)
        }
    }
}

@Composable
fun ItemCard(item: MainActivity.Item) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(item.imageUrl),
            contentDescription = null,  // Consider providing a proper content description
            modifier = Modifier.size(50.dp, 50.dp)
        )
        Text(text = item.name)
    }
}



