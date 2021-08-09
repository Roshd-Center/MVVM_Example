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
    private var trashes = ArrayList<Trash>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRVViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return MainRVViewHolder(layoutInflater.inflate(R.layout.trash_viewholder, parent, false))
    }

    override fun onBindViewHolder(holder: MainRVViewHolder, position: Int) {
        val trash = trashes.get(position)
        holder.render(trash)
    }

    override fun getItemCount(): Int {
        return trashes.size
    }

    public fun setTrashes(trashes:List<Trash>){
        this.trashes = trashes as ArrayList<Trash>
        Log.e("TEST setTrashes", trashes.toString())
        this.notifyDataSetChanged()
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