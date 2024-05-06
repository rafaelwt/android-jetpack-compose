package com.example.jetpackcomposecatalogo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

// @Preview
@Composable()
fun ConstraintExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (redBox, blueBox, yellowBox, magentaBox, greenBox) = createRefs()
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                top.linkTo(redBox.bottom)
                start.linkTo(redBox.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                bottom.linkTo(redBox.top)
                end.linkTo(redBox.start)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(magentaBox) {
                bottom.linkTo(redBox.top)
                start.linkTo(redBox.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Green)
            .constrainAs(greenBox) {
                top.linkTo(redBox.bottom)
                end.linkTo(redBox.start)
            })

    }
}

// @Preview
@Composable
fun ConstraintExampleGuide() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        // var startGuide = createGuidelineFromStart(10.dp)
        val boxRed = createRef()
        val topGuide = createGuidelineFromTop(0.1f)
        val startGuide = createGuidelineFromStart(0.25f)
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
            })
    }
}
// @Preview
@Composable
fun ConstraintBarrier() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (redBox, blueBox, yellowBox) = createRefs()
        val barrier = createEndBarrier(redBox, blueBox)
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                start.linkTo(parent.start, margin = 16.dp)
            })
        Box(modifier = Modifier
            .size(235.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                 top.linkTo(redBox.bottom)
                    start.linkTo(parent.start, margin = 32.dp)
            })
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                start.linkTo(barrier)
                top.linkTo(redBox.top)
            })
    }
}
@Preview
@Composable
fun ConstraintChainExmple() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (greenBox, redBox, yellowBox) = createRefs()

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Green)
            .constrainAs(greenBox) {
                start.linkTo(parent.start)
                end.linkTo(redBox.start)
            })
        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                start.linkTo(greenBox.end)
                end.linkTo(yellowBox.start)
            })
        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                start.linkTo(redBox.end)
                end.linkTo(parent.end)
            })
        createHorizontalChain(greenBox, redBox, yellowBox, chainStyle = ChainStyle.SpreadInside)
    }
}

