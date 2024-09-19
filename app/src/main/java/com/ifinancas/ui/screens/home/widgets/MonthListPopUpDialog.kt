package com.br.ifinancas.ui.screens.home.widgets

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@SuppressLint("NewApi")
@Composable
fun MonthListPopUpDialog(
    onDismissRequest: () -> Unit = {},
    monthSelected: String = "",
    onChangeMonthItem: (month: String) -> Unit = {},
    monthListVisible: Boolean = false
) {
//
//    //val dateTimeViewModel: DateTimeViewModel = hiltViewModel()
//
//    val months by remember {
//        mutableStateOf(dateTimeViewModel.getMonths())
//    }
//
//    /*    val isNeedExpansion by remember{ mutableStateOf(false) }
//
//        val animatedSizeDp: Dp by animateDpAsState(targetValue = if (isNeedExpansion) 350.dp else 100.dp)*/
//
//
//    val scale by remember {
//        mutableStateOf(Animatable(0f))
//    }
//
//    LaunchedEffect(key1 = true, block = {
//        scale.animateTo(0.9f, animationSpec = tween(800, easing = {
//            OvershootInterpolator(2f).getInterpolation(it)
//        }))
//    })
//
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 95.dp, start = 20.dp, end = 20.dp)
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .scale(scale.value),
//            colors = CardDefaults.cardColors(Color.White),
//            shape = RoundedCornerShape(15.dp), elevation = CardDefaults.cardElevation(10.dp)
//        ) {
//            LazyColumn {
//                items(months) {
//                    MonthListPopUpDialogItem(
//                        text = it,
//                        monthSelected = monthSelected,
//                        onChangeMonthItem = onChangeMonthItem
//                    )
//                }
//            }
//        }
//    }
//}
//
//
//@Preview
//@Composable
//fun MonthListPopUpDialogItem(
//    text: String = "Item",
//    monthSelected: String = "",
//    onChangeMonthItem: (month: String) -> Unit = {}
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onChangeMonthItem(text) },
//        verticalArrangement = Arrangement.Center
//    ) {
//        Spacer(modifier = Modifier.height(5.dp))
//        if (monthSelected == text) {
//            Box(
//                modifier = Modifier.background(Color.LightGray),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = text,
//                    color = MAINBLUE,
//                    fontSize = 16.sp,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//        } else {
//            Text(
//                text = text,
//                color = Color.Black,
//                fontSize = 16.sp,
//                textAlign = TextAlign.Center,
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//        Spacer(modifier = Modifier.height(5.dp))
//        Line()
//    }
}
