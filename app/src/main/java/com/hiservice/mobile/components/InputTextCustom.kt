package com.hiservice.mobile.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hiservice.mobile.R
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.GreyDark
import com.hiservice.mobile.ui.theme.GreyLight
import com.hiservice.mobile.ui.theme.HiServiceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextCustom(hint: String){
    var text by rememberSaveable  { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { newText -> text = newText},
        label = {Text(hint)},
        modifier = Modifier
            .height(54.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = GreyDark,
            unfocusedLabelColor = GreyDark,
            containerColor = GreyLight,
            cursorColor = DarkCyan,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputText() {
    var password by rememberSaveable  { mutableStateOf("") }
    var isError by rememberSaveable  { mutableStateOf(false) }
    var passwordVisibility by rememberSaveable  { mutableStateOf(false) }

    val visibilityIcon: Painter = painterResource(id = if (passwordVisibility) R.drawable.ic_visibility_off else R.drawable.ic_visibility_on)

    if (isError) {
        Text(
            fontSize = 12.sp,
            text = "Password kurang dari 8 karakter",
            color = Color.Red.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth()
        )
    }
    TextField(
        value = password,
        onValueChange = { newPassword ->
            password = newPassword
            isError = newPassword.length < 8 // Cek panjang password
        },
        label = { Text("Password") },
        modifier = Modifier
            .height(54.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth(),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    painter = visibilityIcon,
                    contentDescription = "Toggle password visibility"
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = GreyDark,
            unfocusedLabelColor = GreyDark,
            containerColor = GreyLight,
            cursorColor = DarkCyan,
        )
        ,isError = isError,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInputText() {
    var text by rememberSaveable  { mutableStateOf("") }
    var isError by rememberSaveable  { mutableStateOf(false) }

    val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")

    if (isError) {
        Text(
            fontSize = 12.sp,
            text = "Format e-mail salah.",
            color = Color.Red.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth()
        )
    }
    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
            isError = !newText.matches(emailRegex) // Cek validitas email
        },
        label = { Text("Email") },
        modifier = Modifier
            .height(54.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                isError = !text.matches(emailRegex) // Cek validitas email saat tombol "Done" ditekan
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = GreyDark,
            unfocusedLabelColor = GreyDark,
            containerColor = GreyLight,
            cursorColor = DarkCyan
        ),
        isError = isError,
        singleLine = true
    )
}

@Composable
@Preview(showBackground = true)
fun InputTextCustomPreview() {
    HiServiceTheme {
        InputTextCustom(hint = "Full name")
    }
}

@Composable
@Preview(showBackground = true)
fun EmailInputTextPreview() {
    HiServiceTheme {
        EmailInputText()
    }
}

@Composable
@Preview(showBackground = true)
fun PasswordInputTextPreview() {
    HiServiceTheme {
        PasswordInputText()
    }
}
