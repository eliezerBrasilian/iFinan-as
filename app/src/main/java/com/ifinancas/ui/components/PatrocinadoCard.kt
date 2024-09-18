package com.ifinancas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifinancas.R
import com.ifinancas.data.gitignore.discordGroupInviteLink


@Composable
fun PatrocinadoCard(
    imageResource: Int = R.drawable.discord,
    title: String = "Be Dev Studios ⚡",
    description: String = stringResource(R.string.entre_se_voc_gosta_de_programa_o_e_games),
    buttonText: String = stringResource(R.string.entrar_na_comunidade),
    link: String = discordGroupInviteLink,
    hasPadding: Boolean = true,
    isVerified: Boolean = false,
    backgroundColor: Color = Color.White
) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    Box(modifier = Modifier.padding(if (hasPadding) 10.dp else 0.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
                containerColor = backgroundColor
            ), elevation = CardDefaults.cardElevation(9.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
                Column {
                    ContentVerifiedLabel()
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = description,
                        fontSize = 10.sp,
                        color = Color.Blue,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            uriHandler.openUri(link)
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xff02020B))
                    ) {
                        Text(
                            text = buttonText,
                            fontSize = 11.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun ContentVerifiedLabel() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.verified),
            contentDescription = null,
            modifier = Modifier
                .size(15.dp)
                .clip(RoundedCornerShape(15.dp))
        )
        Text(
            text = stringResource(R.string.patrocinado),
            fontSize = 10.sp,
            color = Color(0xff4059AD),
            fontWeight = FontWeight.SemiBold
        )
    }
}