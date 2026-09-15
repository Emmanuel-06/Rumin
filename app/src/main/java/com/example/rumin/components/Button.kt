package com.example.rumin.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rumin.R
import com.example.rumin.ui.theme.sfRoundedFontFamily

@Composable
fun PrimaryButton(
    icon: Int,
    label: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {

    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Color.White),
        modifier = modifier.size(width = 180.dp, height = 48.dp).shadow( elevation = 60.dp,
            shape = RoundedCornerShape(100),
            clip = true,
            spotColor = Color.Black.copy(0.1f))
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = label,
            fontFamily = sfRoundedFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.Black,
        )

    }

}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(icon = R.drawable.favourite, label = "Favorite")
}