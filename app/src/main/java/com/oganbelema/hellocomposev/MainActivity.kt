package com.oganbelema.hellocomposev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oganbelema.hellocomposev.navigation.MyAppNaVHost
import com.oganbelema.hellocomposev.ui.theme.HelloComposeVTheme
import com.oganbelema.hellocomposev.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloComposeVTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val noteViewModel by viewModels<NoteViewModel>()
                    NoteApp(noteViewModel)
                }
            }
        }
    }
}


@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun NoteApp(noteViewModel: NoteViewModel) {
    MyAppNaVHost(viewModel = noteViewModel)
}


@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloComposeVTheme {

    }
}