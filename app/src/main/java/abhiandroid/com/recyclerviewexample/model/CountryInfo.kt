package abhiandroid.com.recyclerviewexample.model

data class Country (
        val title: String,
        val rows: List<Row>
)


data class Row (
        val title: String? = null,
        val description: String? = null,
        val imageHref: String? = null
)
