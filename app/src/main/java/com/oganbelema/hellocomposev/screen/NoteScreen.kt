package com.oganbelema.hellocomposev.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oganbelema.hellocomposev.R
import com.oganbelema.hellocomposev.components.NoteButton
import com.oganbelema.hellocomposev.components.NoteInputText
import com.oganbelema.hellocomposev.data.NoteDataSource
import com.oganbelema.hellocomposev.formatDate
import com.oganbelema.hellocomposev.model.Note

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
    onNoteClicked: (Note) -> Unit
) {
    val context = LocalContext.current

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
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notification Icon"
                )
            })

        //content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 8.dp
                ),
                text = title.value,
                label = stringResource(id = R.string.title),
                onTextChange = {
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
                    description.value = it
                })

            NoteButton(text = stringResource(id = R.string.save),
                onClick = {
                    if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                        //save/add to the list
                        onAddNote(Note(title = title.value,
                        description = description.value))

                        title.value = ""
                        description.value = ""
                        Toast.makeText(context, context.getString(R.string.note_added),
                            Toast.LENGTH_SHORT).show()
                    }
                })
        }

        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(items = notes) { note ->
                NoteRow(note = note, onNoteLongClicked = {
                    onRemoveNote(it)
                }, onNoteClicked = {
                    onNoteClicked(it)
                })
            }
        }
    }
}

@ExperimentalFoundationApi
@SuppressLint("NewApi")
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteLongClicked: (Note) -> Unit,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFF9734ff),
        elevation = 6.dp
    ) {
        Column(
            modifier = modifier
                .combinedClickable (
                    onClick = { onNoteClicked.invoke(note) },
                    onLongClick = { onNoteLongClicked.invoke(note) }

                )
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ),
        horizontalAlignment = Alignment.Start) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            
            Text(text = note.entryDate.formatDate(), style = MaterialTheme.typography.caption)
        }
    }
}


@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {},
        onNoteClicked = {})
}
