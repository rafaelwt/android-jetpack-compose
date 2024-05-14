package com.example.jetpackcomposecatalogo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalogo.ui.theme.JetpackComposeCatalogoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCatalogoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperHeroView()
                }
            }
        }
    }
}

@Composable
fun MyStateExample() {
    var counter by rememberSaveable {
        mutableIntStateOf(0)
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { counter +=1 }) {
            Text(text = "Pulsame")
        }
        Text(text = "He sido  pulsado ${counter} veces")
    }
}

@Composable
fun MyComplexLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.Cyan), contentAlignment = Alignment.Center) {
            Text(text = "Example 1")
        }
        Spacer(modifier = Modifier
            .width(0.dp)
            .height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(Color.Red), contentAlignment = Alignment.Center) {
                Text(text= "Example 2")
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Green),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Hello world jetpack Box")
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.Magenta), contentAlignment = Alignment.BottomCenter) {
            Text(text = "Example 4")
        }
    }
}

@Composable
fun MyRow() {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Example 1")
        Text(text = "Example 2")
        Text(text = "Example 3")
    }

}

@Composable
fun MyColumn() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            ), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Texto 1", modifier = Modifier
                .background(Color.Yellow)
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(
            "Texto 2", modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(
            "Texto 3", modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(
            "Texto 4", modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .height(100.dp)
        )
    }


}

@Composable
fun MyBox(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .background(Color.Cyan)
                .verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center
        ) {
            Text("Hello world jetpack Box")
        }
    }
}

@Preview(showBackground = true, device = "id:Nexus 5")
@Composable
fun GreetingPreview() {
    JetpackComposeCatalogoTheme {
        MyStateExample()
    }
}