package com.example.twitterchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twitterchallenge.ui.theme.TwitterChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TwitterChallengeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFF161D26)
                ) { innerPadding ->
                    Column (modifier = Modifier.fillMaxSize()){
                        TwitterCard(
                            modifier = Modifier.padding(innerPadding)
                        )
                        TwitDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun TwitterCard(modifier: Modifier = Modifier) {
    var chat by remember {
        mutableStateOf(false)
    }
    var rt by remember {
        mutableStateOf(false)
    }
    var like by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF161D26))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "",
            modifier = Modifier
                .clip(CircleShape)
                .size(55.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextTitle(text = "@ArisDev", modifier = Modifier.padding(end = 8.dp))
                DefaultText(text = "@ArisDev", modifier = Modifier.padding(end = 8.dp))
                DefaultText(text = "• 2h", modifier = Modifier.padding(end = 8.dp))
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_dots),
                    contentDescription = "",
                    tint = Color.White
                )
            }
            val text =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec odio" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec odio" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec odio"
            TextBody(text = text, modifier = Modifier.padding(bottom = 8.dp))
            Image(
                painter = painterResource(id = R.drawable.profile), contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10)),
                contentScale = ContentScale.Crop
            )
            Row(Modifier.padding(top = 16.dp)) {
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unSelectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat_filled),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    isSelected = chat,
                    onClick = {
                        chat = !chat
                    }
                )
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unSelectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_rt),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_rt),
                            contentDescription = "",
                            tint = Color(0xFF00FF27)
                        )
                    },
                    isSelected = rt,
                    onClick = {
                        rt = !rt
                    }
                )
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    unSelectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like_filled),
                            contentDescription = "",
                            tint = Color(0xFFFF0000)
                        )
                    },
                    isSelected = like,
                    onClick = {
                        like = !like
                    }
                )

            }
        }
    }

}

@Composable()
fun SocialIcon(
    modifier: Modifier = Modifier,
    unSelectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val defaultValue = 1
    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            selectedIcon()
        } else {
            unSelectedIcon()
        }

        Text(
            text = if (isSelected) (defaultValue + 1).toString() else defaultValue.toString(),
            color = Color(0xFF7E8B98),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )

    }

}

@Composable
fun TextBody(text: String, modifier: Modifier = Modifier) {
    Text(text = text, color = Color.White, modifier = modifier)
}

@Composable
fun TextTitle(text: String, modifier: Modifier = Modifier) {
    Text(text = text, color = Color.White, fontWeight = FontWeight.ExtraBold, modifier = modifier)
}

@Composable
fun DefaultText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, color = Color.Gray, modifier = modifier)
}

@Composable
fun TwitDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .height(0.5.dp), color = Color(0xFF7E8B98)
    )
}