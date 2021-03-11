package abhiandroid.com.recyclerviewexample.adapter

import abhiandroid.com.recyclerviewexample.adapter.CustomAdapter.MyViewHolder
import abhiandroid.com.recyclerviewexample.model.Row
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(@LayoutRes var layoutId: Int, private var infoRows: List<Row>?) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return MyViewHolder(binding)
    }

    private fun getLayoutIdForPosition(): Int {
        return layoutId
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        infoRows?.run {
            val currentRow = this[position]
            holder.bind(currentRow)
            holder.binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return infoRows!!.size
    }

    inner class MyViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(row: Row) {
            binding.setVariable(BR.row, row)
            binding.executePendingBindings()
        }
    }

}