package com.br.ifinancas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.ifinancas.data.enums.Category

@Composable
fun CategoryChooserItem(
    categorySelected: Category,
    it: Category,
    handleCategoryChange: (category: Category) -> Unit
) {
    val context = LocalContext.current
    if (categorySelected == it) {
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xff88527F),
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(3.dp)
                .clickable { handleCategoryChange(it) }
        ) {
            Text(
                text = it.getValue(context),
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal
            )
        }
    } else {
        Text(text = it.getValue(context),
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.clickable { handleCategoryChange(it) })
    }
}