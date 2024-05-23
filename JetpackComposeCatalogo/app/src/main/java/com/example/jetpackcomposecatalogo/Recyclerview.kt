package com.example.jetpackcomposecatalogo

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue  // <--- Add this import for by remember
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalogo.model.SuperHero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerview() {
    val myList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7")
    LazyColumn() {
        item {
            Text("Header")
        }
        items(myList) {
            Text(it)
        }
        item {
            Text("Footer")
        }
    }
}

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperHeroes()) { superHero ->
            ItemHero(superHero, onItemSelected = { selectedHero ->
                // Do something with the selected hero
                Toast.makeText(context, "Selected: ${selectedHero.name}", Toast.LENGTH_SHORT).show()
            })
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun SuperHeroStickyView() {
    val context = LocalContext.current
    val superHero: Map<String, List<SuperHero>> = getSuperHeroes().groupBy { it.publisher }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        superHero.forEach { (publisher, heroes) ->
            stickyHeader {
                Text(
                    text = publisher,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp).background(Color.Green),
                    fontSize = 20.sp
                )
            }
            items(heroes) { superHero ->
                ItemHero(superHero, onItemSelected = { selectedHero ->
                    // Do something with the selected hero
                    Toast.makeText(context, "Selected: ${selectedHero.name}", Toast.LENGTH_SHORT)
                        .show()
                })
            }
        }
    }
}


@Composable
fun SuperHeroWithSpecialControlView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val courutineScope = rememberCoroutineScope()
    Column {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperHeroes()) { superHero ->
                ItemHero(superHero, onItemSelected = { selectedHero ->
                    // Do something with the selected hero
                    Toast.makeText(context, "Selected: ${selectedHero.name}", Toast.LENGTH_SHORT)
                        .show()
                })
            }
        }
        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0

            }
        }
        if (showButton) {
            Button(onClick = {
                courutineScope.launch {
                    rvState.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to top")
            }
        }

    }
}

@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ), content = {
            items(getSuperHeroes()) { superHero ->
                ItemHero(superHero, onItemSelected = { selectedHero ->
                    // Do something with the selected hero
                    Toast.makeText(context, "Selected: ${selectedHero.name}", Toast.LENGTH_SHORT)
                        .show()
                })
            }
        })
}


@Composable
fun ItemHero(superHero: SuperHero, onItemSelected: (SuperHero) -> Unit) {
    Card(border = BorderStroke(2.dp, Color.Red), modifier = Modifier
        .width(200.dp)
        .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
        .clickable { onItemSelected(superHero) }) {
        Column {
            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = superHero.realName,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = superHero.name, modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(
                text = superHero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superHero.publisher,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp),
                fontSize = 10.sp
            )
        }
    }
}

fun getSuperHeroes(): List<SuperHero> {
    return listOf(
        SuperHero("Spiderman", "Peter Parker", "Marvel", R.drawable.spiderman),
        SuperHero("Wolverine", "Logan", "Marvel", R.drawable.logan),
        SuperHero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        SuperHero("Thor", "Thor Odinson", "Marvel", R.drawable.thor),
        SuperHero("Flash", "Barry Allen", "DC", R.drawable.flash),
        SuperHero("Green Lantern", "Hal Jordan", "DC", R.drawable.green_lantern),
        SuperHero("WonderWoman", "Diana Prince", "DC", R.drawable.wonder_woman),
    )

}
