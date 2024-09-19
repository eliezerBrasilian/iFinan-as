package com.br.ifinancas.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.br.ifinancas.R
import com.br.ifinancas.ui.theme.BACKGROUNDGREEN
import com.br.ifinancas.ui.theme.BACKGROUNDRED
import com.br.ifinancas.utils.AppUtils.Companion.toDefaultCurrency
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Bell
import compose.icons.fontawesomeicons.solid.CaretDown
import compose.icons.fontawesomeicons.solid.CaretUp
import kotlin.random.Random

@Preview
@Composable
fun HomeTablet(
    nav: NavHostController = rememberNavController()
) {

    val userName = "Nicola Rich"

    val totalMoviments = 10000
    val totalIncome = 30000
    val totalExpense = 20000
    val totalSaving = 5000

    //todo usar fontFamily = MontserratFamily

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(15.dp)) {
            Top(nav, userName)
            Spacer(modifier = Modifier.height(50.dp))
            Row(horizontalArrangement = Arrangement.Center) {
                //primeiro card
                CardTotalMoviments(totalMoviments, totalIncome, totalExpense)
                Spacer(Modifier.size(30.dp))
                CardTotalSavings(totalMoviments, totalIncome, totalExpense)
            }
        }

    }
}

@Composable
private fun CardTotalSavings(
    totalMoviments: Int,
    totalIncome: Int,
    totalExpense: Int
) {
    Card(
        colors = CardDefaults.cardColors(Color(0xffDCD6F7)),

        ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = "Total de Reservas")
            Text(
                text = toDefaultCurrency(totalMoviments.toFloat()),
                fontSize = 30.sp, fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                RandomHeightBoxes()

            }

        }
    }
}

@Composable
fun RandomHeightBoxes() {
    val random = Random(System.currentTimeMillis())

    Row(
        modifier = Modifier.width(200.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(8) {
            val height = random.nextInt(10, 41).dp

            Box(
                modifier = Modifier
                    .width(10.dp)
                    .height(height)
                    .background(Color(0xff88A2AA))
            )
        }
    }
}


@Composable
private fun CardTotalMoviments(
    totalMoviments: Int,
    totalIncome: Int,
    totalExpense: Int
) {
    Card(
        colors = CardDefaults.cardColors(Color(0xffDCD6F7)),

        ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = "Total registrado esse mês")
            Text(
                text = toDefaultCurrency(totalMoviments.toFloat()),
                fontSize = 30.sp, fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                TotalMovimentsBottomItem(
                    totalIncome,
                    stringResource(id = R.string.receitas),
                    FontAwesomeIcons.Solid.CaretUp,
                    BACKGROUNDGREEN
                )
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .background(Color.Black)
                        .height(40.dp)
                )
                TotalMovimentsBottomItem(
                    totalExpense,
                    stringResource(id = R.string.despesas),
                    FontAwesomeIcons.Solid.CaretDown,
                    BACKGROUNDRED
                )
            }

        }
    }
}


@Composable
private fun TotalMovimentsBottomItem(
    totalMoviments: Int,
    stringResource: String,
    icon: ImageVector,
    iconColor: Color
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(9.dp),
                tint = iconColor
            )
            Text(text = stringResource)
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = toDefaultCurrency(totalMoviments.toFloat()),
            fontSize = 15.sp, fontWeight = FontWeight.SemiBold
        )
    }

}

@Composable
private fun Top(
    nav: NavHostController,
    userName: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = "Dashboard", fontSize = 22.sp)
        Row(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Bell,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
//            AsyncImage(
//                model = if (photo.isNullOrEmpty())
//                    R.drawable.user_profile else photo,
//                contentDescription = null,
//                contentScale = ContentScale.FillBounds,
//                modifier = Modifier
//                    .size(33.dp)
//                    .border(width = 1.dp, color = Color.Black)
//                    .clip(
//                        CircleShape
//                    )
//                    .clickable {
//                        nav.navigate(NavigationScreens.PROFILE)
//                    })
            Text(text = userName, fontSize = 14.sp)
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
