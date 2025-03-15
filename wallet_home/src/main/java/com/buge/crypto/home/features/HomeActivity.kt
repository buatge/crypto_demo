package com.buge.crypto.home.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buge.crypto.home.R
import com.buge.crypto.home.features.adapter.AssetsAdapter
import com.buge.crypto.home.features.model.AssetBalanceData

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var assetsAdapter: AssetsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_home)

        recyclerView = findViewById(R.id.recycler_assets)

        val assets = listOf(
            AssetBalanceData(),
            AssetBalanceData(),
            AssetBalanceData(),
            AssetBalanceData(),
            AssetBalanceData(),
            AssetBalanceData()
        )

        assetsAdapter = AssetsAdapter(assets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = assetsAdapter
    }
}