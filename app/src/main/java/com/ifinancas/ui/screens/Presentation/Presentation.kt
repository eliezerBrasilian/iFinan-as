package com.ifinancas.ui.screens.Presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ifinancas.R
import com.ifinancas.navigation.NavigationScreens
import com.ifinancas.ui.components.AppLogoText
import com.ifinancas.ui.theme.MAINBLUE
import com.ifinancas.ui.theme.Typography
import com.ifinancas.utils.AppUtils

@Composable
fun Presentation(nav: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    val generalModifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(15.dp)

    if (AppUtils.isTabletInLandscape(context)) Row(
        modifier = generalModifier.padding(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(100.dp)
    ) {
        Column {
            AppLogoText(textAlign = TextAlign.Center)
            Image(
                painter = painterResource(id = R.drawable.mulher_tela1),
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .width(240.dp)
            )
        }

        Column {
            Text(
                text = stringResource(R.string.entre_ou_cadastre_se),
                style = Typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = stringResource(R.string.gerencie_seus_gastos_de_forma_r_pida_e_pr_tica),
                style = Typography.labelSmall,
                color = Color.DarkGray,
                fontSize = 13.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = { nav.navigate(NavigationScreens.SIGN_UP) }, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(width = 1.dp, color = MAINBLUE, shape = RoundedCornerShape(9.dp))
            ) {
                Text(
                    text = stringResource(R.string.cadastrar),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = { nav.navigate(NavigationScreens.LOGIN) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(width = 1.dp, color = MAINBLUE, shape = RoundedCornerShape(9.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            ) {
                Text(
                    text = stringResource(R.string.ja_sou_cadastrado),
                    color = Color.Blue,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                )
            }
        }
    }
    else {
        Column(
            modifier = generalModifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppLogoText()

            Image(
                painter = painterResource(id = R.drawable.mulher_tela1),
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .width(240.dp)
            )
            Text(
                text = stringResource(R.string.entre_ou_cadastre_se),
                style = Typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = stringResource(R.string.gerencie_seus_gastos_de_forma_r_pida_e_pr_tica),
                style = Typography.labelSmall,
                color = Color.DarkGray,
                fontSize = 13.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = { nav.navigate(NavigationScreens.SIGN_UP) }, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(width = 1.dp, color = MAINBLUE, shape = RoundedCornerShape(9.dp))
            ) {
                Text(
                    text = stringResource(R.string.cadastrar),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = { nav.navigate(NavigationScreens.LOGIN) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(width = 1.dp, color = MAINBLUE, shape = RoundedCornerShape(9.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            ) {
                Text(
                    text = stringResource(R.string.ja_sou_cadastrado),
                    color = Color.Blue,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                )
            }
        }
    }

}
