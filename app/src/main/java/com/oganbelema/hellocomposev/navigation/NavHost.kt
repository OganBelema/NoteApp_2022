package com.oganbelema.hellocomposev.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oganbelema.hellocomposev.screen.NoteDetailScreen
import com.oganbelema.hellocomposev.screen.NoteScreen
import com.oganbelema.hellocomposev.viewmodels.NoteViewModel

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MyAppNaVHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationScreens.NotesScreen.name,
    viewModel: NoteViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationScreens.NotesScreen.name) {
            NoteScreen(notes = viewModel.noteList.collectAsState().value,
                onAddNote = {
                    viewModel.addNote(it)
                },
                onRemoveNote = {
                    viewModel.removeNote(it)
                },
                onNoteClicked = {
                    navController.navigate(NavigationScreens.NoteScreen.name+"/${it.id}")
                })
        }

        composable(NavigationScreens.NoteScreen.name+"/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })) { navBackStackEntry ->

            val id = navBackStackEntry.arguments?.getString("id")

            if (id != null) {
                viewModel.getNote(id)

                NoteDetailScreen(modifier = modifier,
                    note = viewModel.note.collectAsState().value,
                    onNavigateBack = { navController.popBackStack() })
            }

        }
    }

}