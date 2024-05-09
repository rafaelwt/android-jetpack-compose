package com.example.myjatpackcomponents

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myjatpackcomponents.ui.theme.CheckInfo
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
                    val myOptions = getOptions(listOf("Option 1", "Option 2", "Option 3"))
                    Column {
                        MyTextField(myText.value, onValueChanged = {
                            myText.value = it
                        })
                        myOptions.forEach {
                            MyCheckBoxWithTextCompleted(it)
                        }
                        MyTriStatusCheckBox()

                        MyDropDownMenu()
                        AdvanceSlider()
                        var showDialog by remember {
                            mutableStateOf(false)
                        }
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Button(onClick = { showDialog = true}) {
                                Text("Show Dialog")
                            }
                            MyDialog(show = showDialog, onDismiss = { showDialog = false }, onConfirm = {
                                Log.i("MainActivity", "Dialog Confirmed")
                            })
                        }
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyJatpackComponentsTheme {
        BasicSlider()
    }
}
@Composable()
fun MyDropDownMenu() {
    val desserts = listOf("Cake", "Ice Cream", "Donut")
    var selectedText by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable {
                    expanded = true
                }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded, onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { dessert ->
                DropdownMenuItem( onClick = {
                    selectedText = dessert
                    expanded = false
                }, text = { Text(dessert) })
            }

        }
    }
}

@Composable()
fun MyDivider() {
    Divider(
        modifier = Modifier.padding(16.dp),
        color = Color.Red,
        thickness = 2.dp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable()
fun MyBadgeBox() {
    BadgedBox(badge = {
        Badge {
            val badgeNumber = "8"
            Text(
                badgeNumber,
                modifier = Modifier.semantics {
                    contentDescription = "$badgeNumber new notifications"
                }
            )
        }
    }) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Favorite"
        )
    }
}

@Composable()
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Red,
            contentColor = Color.Blue
        ),
        border = BorderStroke(5.dp, Color.Green),

        ) {
        Column(modifier = Modifier.padding(16.dp)) {
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
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable {
            mutableStateOf(false)
        }
        CheckInfo(it, selected = status, onCheckedChange = { myNewStatus ->
            status = myNewStatus
        })
    }
}

@Composable
fun MyRadioButtonList() {
    val options = listOf("PHP", "Java", "Kotlin", "Python")
    var selectedOption by remember { mutableStateOf(options[0]) }
    Column(Modifier.fillMaxWidth()) {
        options.forEach {
            Row {
                RadioButton(
                    selected = selectedOption == it, onClick = { selectedOption = it }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(it)
            }
        }
    }
}

@Composable
fun MyRadioButton() {
    var status by rememberSaveable {
        mutableStateOf(false)
    }
    Row(Modifier.fillMaxWidth()) {
        RadioButton(
            selected = status, onClick = { /*TODO*/ }, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Blue,
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("This is a radio button")
    }

}

@Composable
fun MyTriStatusCheckBox() {
    var status by rememberSaveable {
        mutableStateOf(ToggleableState.Off)
    }
    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun MyCheckBoxWithTextCompleted(checkInfo: CheckInfo) {

    Row(modifier = Modifier.padding(8.dp)) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(8.dp))
        Text(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp), text = checkInfo.title)
    }
}

@Composable
fun MyCheckBoxWithText() {
    var checked by rememberSaveable {
        mutableStateOf(false)
    }
    Row(modifier = Modifier.padding(8.dp)) {
        Checkbox(checked = checked, onCheckedChange = { checked = !checked })
        Spacer(modifier = Modifier.width(8.dp))
        Text(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp), text = "This is a checkbox")
    }
}

@Composable
fun MyCheckBox() {
    var checked by rememberSaveable {
        mutableStateOf(false)
    }
    Checkbox(checked = checked, onCheckedChange = { checked = !checked })
}

@Composable
fun MySwitch() {
    var state by rememberSaveable {
        mutableStateOf(true)
    }
    Switch(
        checked = state, onCheckedChange = { state = !state }, enabled = true, colors =
        SwitchDefaults.colors(
            checkedThumbColor = Color.Red,
            checkedTrackColor = Color.Green,
            uncheckedThumbColor = Color.Blue,
            uncheckedTrackColor = Color.Yellow
        )
    )
}


@Composable
fun MyProgressAdvance() {
    var progressStatus by rememberSaveable {
        mutableStateOf(0f)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            progress = progressStatus,
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                progressStatus += 0.1f
            }) {
                Text(text = "Increase Progress")

            }
            Button(onClick = {
                progressStatus -= 0.1f
            }) {
                Text(text = "Decrease Progress")
            }
        }
    }
}

@Composable
fun MyProgress() {
    var showLoading by remember {
        mutableStateOf(true)
    }
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator()
            LinearProgressIndicator()
        }
        Button(onClick = {
            showLoading = !showLoading
        }) {
            Text(text = "Toggle Progress")
        }
    }
}

@Composable
fun MyIcon() {
    Icon(imageVector = Icons.Default.Star, contentDescription = "Star Icon", tint = Color.Red)
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
