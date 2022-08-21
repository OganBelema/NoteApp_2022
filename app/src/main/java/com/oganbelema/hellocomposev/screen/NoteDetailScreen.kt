package com.oganbelema.hellocomposev.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oganbelema.hellocomposev.R
import com.oganbelema.hellocomposev.data.NoteDataSource
import com.oganbelema.hellocomposev.model.Note

@Preview
@Composable
fun NoteDetailScreen(modifier: Modifier = Modifier, note: Note = NoteDataSource().loadNotes()[0]) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = modifier,
                elevation = 6.dp,
                title = {
                    Text(text = stringResource(id = R.string.note_detail))
                },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back arrow")
                    }
                }
            ) 
        }
    ) {
        NoteInfoColumn(note = note)
    }
}


@Composable
fun NoteInfoColumn(modifier: Modifier = Modifier, note: Note) {
    Column(modifier = modifier
        .padding(8.dp)
        .fillMaxSize(), 
        verticalArrangement = Arrangement.Top, 
        horizontalAlignment = Alignment.Start) {
        
        Text(text = note.title, style = MaterialTheme.typography.h5)
        
        Text(text = note.description, style = MaterialTheme.typography.body1)

    }

}