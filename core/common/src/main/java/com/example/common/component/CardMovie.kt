package com.example.common.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.common.theme.MovieTheme
import com.example.common.util.clickableWithoutRipple

@Composable
fun ItemMovie(
    id: Int,
    posterPathUrl: String,
    title: String,
    overview: String,
    releaseDate: String,
    @DrawableRes selectIcon: Int,
    modifier: Modifier = Modifier,
    selectOrUnselect: () -> Unit,
    openDetails: (id: Int) -> Unit
) {
    Card(
        modifier = modifier
            .clickable { openDetails(id) },
        backgroundColor = MaterialTheme.colors.background
    ) {
        MovieContent(
            posterPathUrl = posterPathUrl,
            title = title,
            overview = overview,
            releaseDate = releaseDate,
            selectIcon = selectIcon,
            selectOrUnselect = selectOrUnselect
        )
    }
}

@Composable
private fun MovieContent(
    posterPathUrl: String,
    title: String,
    overview: String,
    releaseDate: String,
    @DrawableRes selectIcon: Int,
    modifier: Modifier = Modifier,
    selectOrUnselect: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        AsyncImage(
            model = posterPathUrl,
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .height(125.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight()
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = overview,
                fontSize = 13.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = releaseDate,
                fontSize = 12.sp,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Icon(
                    painter = painterResource(id = selectIcon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(24.dp, 24.dp)
                        .clickableWithoutRipple { selectOrUnselect() }
                )
            }
        }
    }
}

@Preview
@Composable
private fun ItemMoviePreview() {
    MovieTheme {
        ItemMovie(
            id = 0,
            title = "Ant-Man and the Wasp: Quantumania",
            overview = "Super-Hero partners Scott Lang and Hope van Dyne, " +
                    "along with with Hope's parents Janet van Dyne and Hank Pym, " +
                    "and Scott's daughter Cassie Lang, find themselves exploring the Quantum Realm, " +
                    "interacting with strange new creatures and embarking on an adventure that " +
                    "will push them beyond the limits of what they thought possible.",
            releaseDate = "02/17/2023",
            posterPathUrl = "",
            selectIcon = 0,
            modifier = Modifier
                .fillMaxWidth(),
            selectOrUnselect = {},
            openDetails = {}
        )
    }
}