package me.justmambo.play.calorie.item

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Mon 27 Feb 2023
 */
@Composable
fun CalorieDetail(
    modifier: Modifier,
    borderStroke: BorderStroke,
    icon: @Composable () -> Unit,
    title: String,
    value: String
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        border = borderStroke,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(24.dp)
        ) {
            icon()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Text(text = title)
            Text(text = value)
        }

    }

}