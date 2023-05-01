package com.example.detail.presentation

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.common.theme.MovieTheme
import com.example.detail.R
import com.example.detail.presentation.components.UserScoreIndicator
import com.example.movie.TMDB_IMAGE
import com.example.movie.model.GenreMovie

@Composable
internal fun MovieDetailScreen(
    onBackClick: () -> Unit
) {
    val movieDetailVM: MovieDetailVM = hiltViewModel()
    val uiState by movieDetailVM.uiState.collectAsStateWithLifecycle()

    MovieDetail(state = uiState, onBackClick = onBackClick)
}

@Composable
internal fun MovieDetail(
    state: MovieDetailState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val isLoadingState = state is MovieDetailState.Loading
    val movieDetailData = state as? MovieDetailState.MovieDetailData
    val selectedIcon =
        if (movieDetailData?.data?.isSelected == true) painterResource(id = com.example.common_ui_res.R.drawable.ic_select)
        else painterResource(id = com.example.common_ui_res.R.drawable.ic_unselect)
    val scrollState = rememberScrollState()

    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(
                    id = R.drawable.ic_back
                ),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onBackClick() }
                    .testTag("onBackClick")
            )
            MoviePoster(
                isLoadingState = isLoadingState,
                posterPath = movieDetailData?.data?.posterPath.orEmpty(),
                selectedIcon = selectedIcon
            )
            Spacer(modifier = Modifier.height(10.dp))
            MovieTitle(
                isLoadingState = isLoadingState,
                titleText = movieDetailData?.data?.title.orEmpty()
            )
            Spacer(modifier = Modifier.height(10.dp))
            MovieInfoText(
                isLoadingState = isLoadingState,
                title = R.string.overview,
                fraction = 1F,
                actualContent = {
                    InfoText(
                        title = R.string.overview,
                        description = movieDetailData?.data?.overview.orEmpty()
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            MovieGenre(
                isLoadingState = isLoadingState,
                title = R.string.genres,
                genres = movieDetailData?.data?.genreApis.orEmpty()
            )
            Spacer(modifier = Modifier.height(10.dp))
            MovieInfoText(
                isLoadingState = isLoadingState,
                title = R.string.release_date,
                fraction = 0.9F,
                actualContent = {
                    InfoText(
                        title = R.string.release_date,
                        description = movieDetailData?.data?.releaseDate.orEmpty()
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            MovieInfoText(
                isLoadingState = isLoadingState,
                title = R.string.budget,
                fraction = 0.3F,
                actualContent = {
                    InfoText(
                        title = R.string.budget,
                        description = (movieDetailData?.data?.budget ?: 0).toString() + "$"
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            MovieInfoText(
                isLoadingState = isLoadingState,
                title = R.string.runtime,
                fraction = 0.7F,
                actualContent = {
                    InfoText(
                        title = R.string.runtime,
                        description = (movieDetailData?.data?.runtime ?: 0).toString() + "m"
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            MovieInfoText(
                isLoadingState = isLoadingState,
                title = R.string.status,
                fraction = 0.4F,
                actualContent = {
                    InfoText(
                        title = R.string.status,
                        description = movieDetailData?.data?.status.orEmpty()
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "User score: ",
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))

                val voteValue = ((movieDetailData?.data?.voteAverage ?: 0.0).toFloat() * 100) / 10

                UserScoreIndicator(
                    sizeImage = 70.dp,
                    score = voteValue
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MovieGenre(
    isLoadingState: Boolean,
    @StringRes title: Int,
    genres: List<GenreMovie> = emptyList()
) {
    MovieInfoText(
        isLoadingState = isLoadingState,
        title = title,
        fraction = 0.6F,
        actualContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = title),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(16F, TextUnitType.Sp),
                    style = MaterialTheme.typography.body1,
                )
                Spacer(modifier = Modifier.width(10.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    genres.forEach {
                        MovieChip(text = it.name)
                    }
                }
            }
        }
    )
}

@Composable
private fun MovieTitle(
    isLoadingState: Boolean,
    titleText: String
) {
    if (isLoadingState) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp, 20.dp)
                    .clip(CircleShape)
                    .shimmerEffect()
            )
        }
    } else {
        Text(
            text = titleText,
            fontSize = TextUnit(18F, TextUnitType.Sp),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun MovieChip(
    text: String
) {
    Surface(
        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = 32.dp)
                .padding(start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text)
        }

    }
}

@Composable
private fun MovieInfoText(
    isLoadingState: Boolean,
    @StringRes title: Int,
    fraction: Float,
    actualContent: @Composable () -> Unit
) {
    if (isLoadingState) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction)
                    .height(15.dp)
                    .clip(CircleShape)
                    .shimmerEffect()
            )
        }
    } else {
        actualContent()
    }
}

@Composable
private fun InfoText(
    @StringRes title: Int,
    description: String
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            ) {
                append(stringResource(id = title) + " ")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal,
                    color = Color.LightGray
                )
            ) {
                append(description)
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun MoviePoster(
    isLoadingState: Boolean,
    posterPath: String,
    selectedIcon: Painter
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            AsyncImage(
                model = "$TMDB_IMAGE$posterPath",
                contentDescription = null,
//                placeholder = painterResource(id = R.drawable.ic_default_image),
                modifier = Modifier
                    .size(200.dp, 250.dp)
                    .shimmerEffect(isLoadingState)
            )
            Icon(
                painter = selectedIcon,
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {

                    }
            )
        }
    }
}

@Composable
private fun shimmerBrush(showShimmer: Boolean = true, targetValue: Float = 1000f): Brush {
    return if (showShimmer) {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition()
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800)
            )
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}

private fun Modifier.shimmerEffect(showShimmer: Boolean = true): Modifier = composed {
    background(
        brush = shimmerBrush(showShimmer)
    )
}

@Preview
@Composable
fun MovieChipPreview() {
    MovieTheme {
        MovieChip("Adventure")
    }
}

@Preview
@Composable
fun MovieInfoTextPreview() {
    MovieTheme {
        MovieInfoText(
            isLoadingState = true,
            title = R.string.overview,
            fraction = 0.4F,
            actualContent = {
                InfoText(
                    title = R.string.overview,
                    description = "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's " +
                            "parents Janet van Dyne and Hank Pym, and Scott's daughter Cassie Lang, " +
                            "find themselves exploring the Quantum Realm"
                )
            }
        )
    }
}

@Preview
@Composable
fun MovieDetailPreview() {
    MovieTheme {
        MovieDetail(state = MovieDetailState.Loading, onBackClick = {})
    }
}