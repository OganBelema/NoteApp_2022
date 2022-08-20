package com.oganbelema.hellocomposev.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oganbelema.hellocomposev.R
import com.oganbelema.hellocomposev.components.NoteButton
import com.oganbelema.hellocomposev.components.NoteInputText

@ExperimentalComposeUiApi
@Composable
fun NoteScreen() {
    val title = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notification Icon")
            })
        
        //content
        Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 8.dp
                ),
                text = title.value,
                label = stringResource(id = R.string.title),
                onTextChange ={
                    if (it.all { character ->
                        character.isLetter() || character.isWhitespace()
                        }) title.value = it
                })

            NoteInputText(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 8.dp
                ),
                text = description.value,
                label = stringResource(id = R.string.add_a_note),
                onTextChange = {
                    if (it.all { character ->
                            character.isLetter() || character.isWhitespace()
                        }) description.value = it
                })

            NoteButton(text = stringResource(id = R.string.save),
                onClick = {

                })
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen()
}
