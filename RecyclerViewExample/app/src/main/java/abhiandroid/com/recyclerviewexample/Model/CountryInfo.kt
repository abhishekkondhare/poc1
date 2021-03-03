package abhiandroid.com.recyclerviewexample.Model

data class Country (
        val title: String,
        val rows: List<Row>
) {
    companion object {

    }
}

data class Row (
        val title: String? = null,
        val description: String? = null,
        val imageHref: String? = null
)
