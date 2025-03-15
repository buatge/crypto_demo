package com.buge.crypto.home.features.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.buge.crypto.home.R
import com.buge.crypto.home.features.model.AssetBalanceData

class AssetsAdapter :
    RecyclerView.Adapter<AssetsAdapter.AssetViewHolder>() {

    private var assets: List<AssetBalanceData>? = null

    class AssetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvAmount: TextView = itemView.findViewById(R.id.tv_amount)
        val tvValue: TextView = itemView.findViewById(R.id.tv_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_asset_balance, parent, false)
        return AssetViewHolder(view)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val asset = assets?.get(position)
        asset?.let {
            holder.tvName.text = it.name
            holder.tvAmount.text = it.amount
            holder.tvValue.text = it.value
        }

    }

    override fun getItemCount(): Int = assets?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun updateAssets(data: List<AssetBalanceData>? = null) {
        assets = data
        notifyDataSetChanged()
    }
}