package com.ifinancas.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ifinancas.R

@Preview
@SuppressLint("NewApi")
@Composable
fun PopUpDeleteRegister(
    onDismissRequest: () -> Unit = {},
    delete: () -> Unit = {},
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White), shape = RoundedCornerShape(15.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mao),
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
                Text(
                    text = stringResource(R.string.deseja_excluir_registro),
                    fontSize = 14.sp,
                    color = Color.Black
                )
                AuthButton(
                    backgroundColor = Color.Red,
                    text = stringResource(R.string.deletar),
                ) {
                    delete()
                }
            }
        }
    }
}

