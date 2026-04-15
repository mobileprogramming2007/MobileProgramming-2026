package com.example.mobileprogramminglabs.presentation.navigation.bottom_bar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.navigation.Screen
import com.example.mobileprogramminglabs.presentation.theme.AliceBlueLight
import com.example.mobileprogramminglabs.presentation.theme.DustyOliveLight
import com.example.mobileprogramminglabs.presentation.theme.MobileProgrammingLabsTheme

@Composable
fun BottomBarNavigationComponent(
    items: List<BottomBarNavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.size_small),
            topEnd = dimensionResource(id = R.dimen.size_small)
        ),
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.width_xxssmall),
            color = AliceBlueLight
        )
    ) {
        NavigationBar(
            containerColor = Color.Transparent
        ) {
            items.forEachIndexed { index, item ->
                val selected = selectedItemIndex == index

                NavigationBarItem(
                    modifier = Modifier.wrapContentSize(),
                    selected = selected,
                    onClick = { onItemSelected(index) },
                    label = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = item.titleId),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = if (selected) Blue else DustyOliveLight,
                                    fontSize = 12.sp,
                                    fontWeight =  if (selected) FontWeight.W400 else FontWeight.W300
                                )
                            )
                        }
                    },
                    icon = {
                            Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = stringResource(id = item.titleId),
                                tint = if (selected) Blue else DustyOliveLight
                            )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = DustyOliveLight,
                        unselectedIconColor = DustyOliveLight,
                        selectedTextColor = if (selected) DustyOliveLight else Blue,
                        unselectedTextColor = DustyOliveLight,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomBarNavigationComponentPreview() {
    MobileProgrammingLabsTheme {
        val items = listOf(
            BottomBarNavigationItem(
                titleId = R.string.home,
                iconRes = R.drawable.apple__streamline_unicons,
                route = Screen.HomeShortcut.route
            ),
            BottomBarNavigationItem(
                titleId = R.string.quest,
                iconRes = R.drawable.apple__streamline_unicons,
                route = Screen.Quest.route
            ),
            BottomBarNavigationItem(
                titleId = R.string.habit,
                iconRes = R.drawable.apple__streamline_unicons,
                route = Screen.Habit.route
            ),
            BottomBarNavigationItem(
                titleId = R.string.profile,
                iconRes = R.drawable.apple__streamline_unicons,
                route = Screen.Profile.route
            )
        )
        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

        BottomBarNavigationComponent(
            items = items,
            selectedItemIndex = selectedItemIndex,
            onItemSelected = { index -> selectedItemIndex = index }
        )
    }
}