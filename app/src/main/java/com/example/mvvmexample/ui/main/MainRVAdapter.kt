package com.example.mvvmexample.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.mvvmexample.R
import com.example.mvvmexample.data.models.db.Trash
import com.example.mvvmexample.databinding.TrashViewholderBinding

class MainRVAdapter: Adapter<MainRVViewHolder>() {
    public var trashes = ArrayList<Trash>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRVViewHolder {
        Log.e("TEST Creating vh", "start")
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        Log.e("TEST Creating vh", layoutInflater.toString())
        val res= MainRVViewHolder(layoutInflater.inflate(R.layout.trash_viewholder, parent, false))
        Log.e("TEST Creating vh", res.toString())
        return res
    }

    override fun onBindViewHolder(holder: MainRVViewHolder, position: Int) {
        Log.e("TEST Rendering vh", position.toString())
        Log.e("TEST Rendering vh", holder.toString())
        val trash = trashes[position]
        Log.e("TEST Rendering vh", trash.toString())
        holder.render(trash)
    }

    override fun getItemCount(): Int {
        return trashes.size
    }

    public fun setTrashes(trashes:List<Trash>){
        this.trashes.clear()
        this.trashes.addAll(trashes)
        Log.e("TEST setTrashes", trashes.size.toString())
//        TODO("Not working!")
        notifyDataSetChanged()
//        notifyItemRangeInserted(0,trashes.size)
    }

}

class MainRVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: TrashViewholderBinding = TrashViewholderBinding.bind(itemView)

    public fun render(trash: Trash){

        binding.trashId.setText(trash.id.toString())
        binding.trashType.setText(trash.type)
        binding.trashCost.setText(trash.costUser.toString())
    }
}