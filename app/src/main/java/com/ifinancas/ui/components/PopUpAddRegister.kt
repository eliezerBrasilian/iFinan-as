package com.br.ifinancas.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.br.ifinancas.R
import com.br.ifinancas.data.enums.Tags
import com.br.ifinancas.navigation.BottomBarScreen
import com.br.ifinancas.navigation.NavigationScreens

@SuppressLint("NewApi")
@Composable
fun PopUpAddRegisterDialog(
    onDismissRequest: () -> Unit = {},
    nav: NavHostController
) {

    val context = LocalContext.current
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
                PopUpAddRegisterDialogItem(
                    text = stringResource(R.string.hist_rico),
                    icon = R.drawable.historico_menu
                ) {
                    nav.navigate(BottomBarScreen.Historico(context).route)
                    onDismissRequest()
                }
                PopUpAddRegisterDialogItem(
                    text = stringResource(R.string.registrar_receita),
                    icon = R.drawable.receita_menu
                ) {
                    nav.navigate(NavigationScreens.REGISTER + "/${Tags.REVENUE.tag}")
                    onDismissRequest()
                }
                PopUpAddRegisterDialogItem(
                    text = stringResource(R.string.registrar_despesa),
                    icon = R.drawable.despesa_menu
                ) {
                    nav.navigate(NavigationScreens.REGISTER + "/${Tags.EXPENSE.tag}")
                    onDismissRequest()
                }

                PopUpAddRegisterDialogItem(
                    text = stringResource(R.string.reserva),
                    icon = R.drawable.pig_menu
                ) {
                    nav.navigate(NavigationScreens.REGISTER + "/${Tags.RESERVATION.tag}")
                    onDismissRequest()
                }
            }
        }
    }
}

@Composable
fun PopUpAddRegisterDialogItem(
    text: String,
    icon: Int = R.drawable.historico_menu,
    onClick: () -> Unit = {}
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )
        Text(text = text, color = Color.Black, fontSize = 16.sp)
    }
}