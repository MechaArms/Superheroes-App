package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.HeroesDataSource
import com.example.superheroes.model.Hero
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                SuperheroesApp()
            }
        }
    }
}

@Composable
fun SuperheroesApp() {
    Scaffold(
        topBar = {
            SuperheroesTopAppBar()
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(padding)
        ) {
            items(HeroesDataSource.HeroesRepository.heroes) { hero ->
                HeroCard(hero)
            }
        }
    }
}

@Composable
fun SuperheroesTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun HeroCard(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        elevation = 2.dp,
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            end = 16.dp,
            bottom = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            HeroInformation(hero.nameRes,hero.descriptionRes)
            Spacer(Modifier.weight(1f))
            HeroIcon(hero.imageRes)
        }
    }
}

@Composable
fun HeroInformation(@StringRes nameRes: Int, @StringRes descriptionRes: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(nameRes),
            style = MaterialTheme.typography.h3,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(descriptionRes),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun HeroIcon(@DrawableRes imageRes: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8)),
        contentScale = ContentScale.Crop,
        painter = painterResource(imageRes),
        contentDescription = null
    )
}

// Light Theme Card Preview
@Preview(name = "Light Theme Card")
@Composable
fun SuperheroesCardPreview() {
    SuperheroesTheme(darkTheme = false) {
        val hero = Hero(
            nameRes = R.string.hero1,
            descriptionRes = R.string.description1,
            imageRes = R.drawable.android_superhero1
        )
        HeroCard(hero = hero)
    }
}

// Dark Theme Card Preview
@Preview(name = "Dark Theme Card")
@Composable
fun SuperheroesCardDarkPreview() {
    SuperheroesTheme(darkTheme = true) {
        val hero = Hero(
            nameRes = R.string.hero1,
            descriptionRes = R.string.description1,
            imageRes = R.drawable.android_superhero1
        )
        HeroCard(hero = hero)
    }
}

// Light Theme App Preview
@Preview(name = "Light Theme App")
@Composable
fun SuperheroesLightPreview(){
    SuperheroesTheme(darkTheme = false) {
        SuperheroesApp()
    }
}


// Dark Theme App Preview
@Preview(name = "Dark Theme App")
@Composable
fun SuperheroesDarkPreview() {
    SuperheroesTheme(darkTheme = true) {
        SuperheroesApp()
    }
}