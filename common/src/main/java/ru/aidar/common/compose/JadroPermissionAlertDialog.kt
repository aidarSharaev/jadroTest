package ru.aidar.common.compose

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.aidar.common.R
import ru.aidar.common.utils.JadroAppStyle

@Composable
fun JadroPermissionAlertDialog(
    @StringRes confirmButtonText: Int,
    @StringRes text: Int,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
    @StringRes dismissButtonText: Int = R.string.cancel,
    @StringRes title: Int = R.string.provide_permission,
) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            Button(onClick = onConfirmClick) {
                Text(text = stringResource(id = confirmButtonText))
            }
        },
        dismissButton = {
            Button(onClick = onDismissClick) {
                Text(
                    text = stringResource(id = dismissButtonText),
                    fontFamily = JadroAppStyle.JadroFontFamily
                )
            }
        },
        text = {
            Text(
                text = stringResource(id = text),
                fontFamily = JadroAppStyle.JadroFontFamily
            )
        },
        title = {
            Text(
                text = stringResource(title),
                fontFamily = JadroAppStyle.JadroFontFamily
            )
        }
    )
}