package com.example.profile.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.profile.R

@Composable
internal fun ProfileScreen(modifier: Modifier = Modifier) {
    val profileVM: ProfileVM = hiltViewModel()
    val profileState by profileVM.profileState.collectAsStateWithLifecycle()

    Profile(
        state = profileState,
        changeFirstName = profileVM::changeFirstName,
        changeLastName = profileVM::changeLastName,
        saveChanges = profileVM::saveChanges,
        modifier = modifier
    )
}

@Composable
private fun Profile(
    state: ProfileState,
    modifier: Modifier = Modifier,
    changeFirstName: (text: String) -> Unit,
    changeLastName: (text: String) -> Unit,
    saveChanges: () -> Unit
) {
    val scrollState = rememberScrollState()
    val firstNameFocusRequester = remember { FocusRequester() }
    val lastNameFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Surface(
        color = MaterialTheme.colors.background,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        ConstraintLayout {
            val (column, box) = createRefs()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.constrainAs(column) {
                    top.linkTo(parent.top)
                }
            ) {
                Spacer(modifier = Modifier.height(90.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp, 100.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))
                ProfileTextFieldInput(
                    text = state.profile.firstName,
                    labelText = R.string.first_name,
                    changeText = { changeFirstName(it) },
                    modifier = Modifier.focusRequester(firstNameFocusRequester)
                )
                Spacer(modifier = Modifier.height(15.dp))
                ProfileTextFieldInput(
                    text = state.profile.lastName,
                    labelText = R.string.last_name,
                    changeText = { changeLastName(it) },
                    modifier = Modifier.focusRequester(lastNameFocusRequester)
                )
            }

            Box(
                modifier = Modifier
                    .constrainAs(box) {
                        top.linkTo(column.bottom)
                        bottom.linkTo(parent.bottom)
                        linkTo(
                            top = column.bottom, bottom = parent.bottom,
                            topMargin = 15.dp, bottomMargin = 15.dp, bias = 1F
                        )
                        height = Dimension.fillToConstraints
                    },
                contentAlignment = Alignment.BottomCenter
            ) {
                if (state.isApplyBtnEnabled) {
                    TextButton(
                        onClick = {
                            saveChanges()
                            focusManager.clearFocus()
                        },
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp,
                            disabledElevation = 0.dp
                        ),
                        shape = MaterialTheme.shapes.medium.copy(
                            CornerSize(20.dp)
                        ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue.copy(alpha = 0.7F)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(start = 15.dp, end = 15.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.save_changes),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileTextFieldInput(
    text: String,
    @StringRes labelText: Int,
    changeText: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = { changeText(it) },
        placeholder = {
            Text(
                text = stringResource(id = labelText),
                color = Color.LightGray
            )
        },
//        label = {
//            Text(text = stringResource(id = labelText))
//        },
        shape = MaterialTheme.shapes.small.copy(
            CornerSize(20.dp)
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.LightGray.copy(alpha = 0.4F)
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    )
}

@Preview
@Composable
private fun ProfilePreview() {
    Profile(
        state = ProfileState(
            profile = com.example.profile.domain.model.Profile()
        ),
        changeFirstName = {},
        changeLastName = {},
        saveChanges = {}
    )
}