package com.judahben149.flixfixx.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.judahben149.flixfixx.R
import com.judahben149.flixfixx.ui.theme.TransparentCustom
import com.judahben149.flixfixx.ui.theme.md_theme_dark_primaryContainer
import com.judahben149.flixfixx.ui.theme.seed
import com.judahben149.flixfixx.ui.theme.yellowRating

@Composable
fun CustomChip(chipText: String, hasChipIcon: Boolean = false, chipIcon: Painter) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(color = md_theme_dark_primaryContainer.copy(alpha = 0.3f))
            .padding(3.dp)
    ) {
        Row() {
            if (hasChipIcon) {
                Icon(
                    painter = chipIcon,
                    contentDescription = "Chip Icon",
                    modifier = Modifier.padding(start = 8.dp, top = 2.dp).size(16.dp),
                    tint = yellowRating
                )
            }
            Text(
                text = chipText,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp),
                fontSize = 13.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomChipPreview() {
    CustomChip("My Chip", true, painterResource(id = R.drawable.ic_star))
}
