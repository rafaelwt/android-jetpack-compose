package com.example.myjatpackcomponents

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myjatpackcomponents.ui.theme.MyJatpackComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJatpackComponentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val myText = remember {
                        mutableStateOf("Hello, World!")

                    }
                    Column {
                        MyTextField(myText.value, onValueChanged = {
                            myText.value = it
                        })
                        MyButtonExample()
                    }
                }
            }
        }
    }
}

@Composable
fun MyIcon () {
    
}

@Composable
fun MyImageAdvance() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Example",
        modifier = Modifier
            .clip(CircleShape)
            .border(4.dp, Color.Red, CircleShape)
    )
}


@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Example",
        alpha = 0.5f

    )
}

@Composable
fun MyButtonExample() {
    var enabled by rememberSaveable {
        mutableStateOf(true)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = {
                Log.i("MyButtonExample", "Button Clicked")
                enabled = !enabled
            },
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor = Color.Blue
            ),
            border = BorderStroke(4.dp, Color.Green),
        ) {
            Text(text = "Click Me")
        }
        OutlinedButton(
            onClick = { },
            colors = ButtonDefaults.outlinedButtonColors(
                disabledContainerColor = Color.Red,
                disabledContentColor = Color.Yellow
            )
        ) {
            Text(text = "Click Me")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Click Me")
        }

    }
}

@Composable
fun MyTextFieldOutlined() {
    val myText = remember {
        mutableStateOf("")

    }
    OutlinedTextField(value = myText.value, onValueChange = {
        myText.value = it
    }, label = { Text("Enter your name") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Blue
        ),
        shape = MaterialTheme.shapes.medium
    )

}

@Composable
fun MyTextFieldAdvance() {
    val myText = remember {
        mutableStateOf("")

    }
    TextField(value = myText.value, onValueChange = {
        if (it.contains("a")) {
            it.replace("a", "")
        } else {
            myText.value = it
        }
    }, label = { Text("Enter your name") })
}

@Composable
fun MyTextField(name: String, onValueChanged: (String) -> Unit) {

    TextField(value = name, onValueChange = { onValueChanged(it) })

}

@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {
        Text("Hello, World!")
        Text("Hello, World!", color = Color.Blue)
        Text("Hello, World!", fontWeight = FontWeight.Bold)
        Text("Hello, World!", fontWeight = FontWeight.Light)
        Text("Hello, World!", fontFamily = FontFamily.Cursive)
        Text("Hello, World!", style = TextStyle(textDecoration = TextDecoration.LineThrough))
        Text("Hello, World!", style = TextStyle(textDecoration = TextDecoration.Underline))
        Text(
            "Hello, World!",
            style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.LineThrough,
                        TextDecoration.Underline
                    )
                )
            )
        )
        Text(text = "Hello, World!", fontSize = 30.sp)

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyJatpackComponentsTheme {
        MyImageAdvance()
    }
}