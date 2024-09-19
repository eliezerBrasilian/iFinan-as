package com.br.ifinancas.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.ifinancas.R
import com.br.ifinancas.db.models.TransactionModel.TransactionUiState
import com.br.ifinancas.db.models.TransactionModel.TransactionViewModel
import com.br.ifinancas.ui.theme.OVERVIEW
import com.br.ifinancas.utils.AppUtils

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreenOverlayView(
    uiState: State<TransactionUiState>,
    transactionViewModel: TransactionViewModel,
    tag: String?
) {

    val context = LocalContext.current
    val newTag =
        when (tag) {
            "receita" -> stringResource(id = R.string.receita)
            "despesa" -> stringResource(R.string.despesa)
            "reserva" -> stringResource(R.string.reserva_minusculo)
            else -> stringResource(id = R.string.receita)
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OVERVIEW, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        LightView {
            CategoryChooser(
                uiState.value.categorySelected,
                transactionViewModel.handleCategoryChange,
                uiState.value.categoryExpanded,
                transactionViewModel.toggleCategoryExpanded
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = uiState.value.descriptionInput,
            onValueChange = transactionViewModel.onDescriptionChange,
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(R.string.descri_o_da, tag.toString()),
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            },
            textStyle = TextStyle(color = Color.Black, fontSize = 13.sp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = AppUtils.getBackgroundColor(tag.toString()),
                selectionColors = TextSelectionColors(
                    handleColor = AppUtils.getBackgroundColor(tag.toString()),
                    backgroundColor = Color.Transparent
                )
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = {
                transactionViewModel.handleSendRegister(
                    context,
                    tag
                )
            },
            colors = ButtonDefaults.buttonColors(AppUtils.getBackgroundColor(tag.toString())),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.salvar),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(35.dp))
        if (uiState.value.savedSuccessfully) {
            Text(
                text = stringResource(R.string.sua_foi_registrada_com_sucesso, newTag),
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

    }
}