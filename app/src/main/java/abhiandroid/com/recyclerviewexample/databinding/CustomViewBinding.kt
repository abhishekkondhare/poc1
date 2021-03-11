package abhiandroid.com.recyclerviewexample.databinding

import abhiandroid.com.recyclerviewexample.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>?) {
    this.run {
        this.setHasFixedSize(true)
        this.layoutManager = LinearLayoutManager(this.context)
        this.adapter = adapter
    }
}

@BindingAdapter("bind:setImageUrl")
fun ImageView.bindImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {

        Glide.with(context).load(url).error(R.drawable.no_image_found)
                .fallback(R.drawable.no_image_found).into(this)
    }
}
