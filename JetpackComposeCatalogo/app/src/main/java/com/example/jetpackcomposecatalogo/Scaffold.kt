package com.example.jetpackcomposecatalogo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue  // <--- Add this import for by remember
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun ScaffoldExample() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                MyDrawer(onCloseDrawer = {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                })
            }
        }) {

        Scaffold(
            topBar = {/**/
                MyTopBar(onClickDrawer = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }, onClickIcon = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Clicked on $it")
                    }
                })

            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            bottomBar = {
                MyBottomNavigation()
            },
            floatingActionButton = {
                MyFAB()
            },
            floatingActionButtonPosition = FabPosition.Center,

            ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {}
        }
    }


}

@ExperimentalMaterial3Api
@Composable
fun MyTopBar(onClickDrawer: () -> Unit, onClickIcon: (String) -> Unit) {
    TopAppBar(
        title = { Text(text = "My toolbar") }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ), navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Open drawer")
            }
        }, actions = {
            IconButton(onClick = { onClickIcon("Search") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(onClick = { onClickIcon("Date") }) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Date")
            }
        }
    )
}

@Composable
fun MyBottomNavigation() {
    var index by remember { mutableIntStateOf(0) }
    NavigationBar(contentColor = MaterialTheme.colorScheme.primary) {

        NavigationBarItem(
            selected = index == 0,
            onClick = { index = 0 },
            label = { Text(text = "Home") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home"
                )
            })

        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            label = { Text(text = "Favorite") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorite"
                )
            })

        NavigationBarItem(
            selected = index == 2,
            onClick = { index = 2 },
            label = { Text(text = "Search") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            })

    }
}

@Composable
fun MyFAB() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        contentColor = Color.Black,
        containerColor = Color.Yellow
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
    }
}

@Composable
fun MyDrawer(onCloseDrawer: () -> Unit) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Item 1", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
        Text(
            text = "Item 2", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
        Text(
            text = "Item 3", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
        Text(
            text = "Item 4", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )

    }
}