package com.example.detail.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.common.theme.MovieTheme

@Composable
internal fun UserScoreIndicator(
    score: Float = 0F,
    sizeImage: Dp = 100.dp,
    strokeWidth: Dp = 6.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val animateNumber = animateFloatAsState(
        targetValue = if (animationPlayed) score else 0F,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(sizeImage)
            .clip(CircleShape)
            .background(Color.Transparent)
            .padding(10.dp)
    ) {
        Canvas(
            modifier = Modifier
                .size(sizeImage)
        ) {
            //  Background circle
            drawCircle(
                color = scoreColor(score.toInt(), 0.2F),
                radius = this.size.height / 2,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            val sweepAngle = (animateNumber.value / 100) * 360

            // Foreground Arc
            drawArc(
                color = scoreColor(score.toInt()),
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                size = Size(size.width, size.height)
            )
        }
        Text(
            text = animateNumber.value.toInt().toString() + "%",
            color = Color.Black,
            style = MaterialTheme.typography.body2
        )
    }
}

private fun scoreColor(score: Int, alpha: Float = 1F): Color =
    when (score) {
        0 -> Color.LightGray.copy(alpha = alpha)
        in 0..30 -> Color.Red.copy(alpha = alpha)
        in 31..70 -> Color.Yellow.copy(alpha = alpha)
        in 71..100 -> Color.Green.copy(alpha = alpha)
        else -> Color.LightGray.copy(alpha = alpha)
    }

@Preview
@Composable
fun UserScoreIndicatorPreview() {
    MovieTheme {
        UserScoreIndicator()
    }
}