package abhiandroid.com.recyclerviewexample.Adapter

import abhiandroid.com.recyclerviewexample.Adapter.CustomAdapter.MyViewHolder
import abhiandroid.com.recyclerviewexample.Model.Row
import abhiandroid.com.recyclerviewexample.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter(var context: Context, var infoRows: List<Row>?) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rowlayout, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        infoRows?.run {
            holder.title.text = this[position].title
            holder.description.text = this[position].description
            holder.title.text = this[position].title
            Glide.with(context)
                    .load(this[position].imageHref)
                    .into(holder.image);
            holder.itemView.setOnClickListener {
                Toast.makeText(context, this[position].title, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return infoRows!!.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.title) as TextView
        var description : TextView = itemView.findViewById(R.id.description) as TextView
        var image: ImageView = itemView.findViewById(R.id.image) as ImageView
    }

}