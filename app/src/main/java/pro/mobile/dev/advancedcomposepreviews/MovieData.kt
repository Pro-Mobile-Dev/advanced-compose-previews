package pro.mobile.dev.advancedcomposepreviews

data class StarWarsMovie(
    val title: String,
    val releaseYear: String,
    val imageRes: Int
)

val movies = listOf(
    StarWarsMovie("A New Hope", "1977", R.drawable.a_new_hope),
    StarWarsMovie("The Empire Strikes Back", "1980", R.drawable.empire_strikes_back),
    StarWarsMovie("Return of the Jedi", "1983", R.drawable.return_of_the_jedi),
    StarWarsMovie("The Phantom Menace", "1999", R.drawable.phantom_menace),
    StarWarsMovie("Attack of the Clones", "2002", R.drawable.attack_of_the_clones),
    StarWarsMovie("Revenge of the Sith", "2005", R.drawable.revenge_of_the_sith),
    StarWarsMovie("The Force Awakens", "2015", R.drawable.force_awakens),
    StarWarsMovie("The Last Jedi", "2017", R.drawable.last_jedi),
    StarWarsMovie("The Rise of Skywalker", "2019", R.drawable.rise_of_skywalker)
)