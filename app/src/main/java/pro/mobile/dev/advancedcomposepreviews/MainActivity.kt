package pro.mobile.dev.advancedcomposepreviews

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import pro.mobile.dev.advancedcomposepreviews.ui.theme.AdvancedComposePreviewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdvancedComposePreviewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StarWarsMovieList(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun StarWarsMovieList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(8.dp)) {
        items(movies) { movie ->
            StarWarsMovieCard(movie)
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun StarWarsMovieCard(movie: StarWarsMovie) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = movie.imageRes),
                contentDescription = "${movie.title} Poster",
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "Released: ${movie.releaseYear}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun StarWarsMovieCardWithConditions(movie: StarWarsMovie) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (image, titleAndCount, chevron) = createRefs()
            Image(
                modifier = Modifier
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                painter = painterResource(id = movie.imageRes),
                contentDescription = "${movie.title} Poster",
            )
            Column(
                modifier = Modifier
                    .constrainAs(titleAndCount) {
                        top.linkTo(parent.top)
                        start.linkTo(image.end)
                        bottom.linkTo(parent.bottom)
                        // Notice the issue here, if the chevron is not present the view breaks
                        end.linkTo(chevron.start)
                        width = Dimension.fillToConstraints
                    },
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "Released: ${movie.releaseYear}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if (movie.title.contains("Force")) {
                Image(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    modifier = Modifier
                        .constrainAs(chevron) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    alignment = Alignment.CenterEnd,
                    contentDescription = null
                )
            }
        }
    }
}

// Plain
@Preview
@Composable
fun StarWarsMoviePreview() {
    StarWarsMovieCard(movie = movies[1])
}

// Night mode
@Preview(name = "Night mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun StarWarsMovieDarkModePreview() {
    AdvancedComposePreviewsTheme {
        Box(modifier = Modifier.padding(8.dp)) {
            StarWarsMovieCard(movie = movies[2])
        }
    }
}

// Large font
@Preview(name = "Large font", fontScale = 1.5f)
@Composable
fun StarWarsMovieLargeFontPreview() {
    StarWarsMovieCard(movie = movies[3])
}

// Different devices - note you can add multiple @Preview annotations
@Preview(device = Devices.PIXEL_4)
@Preview(device = Devices.NEXUS_7)
@Composable
fun StarWarsMovieMultipleDevicesPreview() {
    StarWarsMovieCard(movie = movies[4])
}

// Background color
@Preview(backgroundColor = 0xFFFF0000, showBackground = true)
@Composable
fun StarWarsMovieWithBackgroundPreview() {
    Box(modifier = Modifier.padding(8.dp)) {
        StarWarsMovieCard(movie = movies[5])
    }
}

// System UI
@Preview(showSystemUi = true)
@Composable
fun StarWarsMovieWithSystemUIPreview() {
    StarWarsMovieList(modifier = Modifier.padding(top = 40.dp))
}

// Conditional rendering
@Preview
@Composable
fun StarWarsMovieCardWithConditionsPreview() {
    StarWarsMovieCardWithConditions(movie = movies[6])
}

@Preview
@Composable
fun StarWarsMovieCardWithConditionsFailPreview() {
    StarWarsMovieCardWithConditions(movie = movies[7])
}
