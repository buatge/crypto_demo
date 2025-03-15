package com.buge.crypto.home.features

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buge.crypto.home.R
import com.buge.crypto.home.features.adapter.AssetsAdapter
import com.buge.crypto.home.features.viewmodel.WalletBalanceViewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: WalletBalanceViewModel by viewModels()

    private lateinit var tvTotalBalance: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var assetsAdapter: AssetsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_home)
        initView()
        initData()
        observeLiveData()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycler_assets)
        tvTotalBalance = findViewById(R.id.tv_total_balance)
    }

    private fun initData() {
        viewModel.initData()
        assetsAdapter = AssetsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = assetsAdapter
    }

    private fun observeLiveData() {
        viewModel.combineBalances.observe(this) {
            assetsAdapter.updateAssets(it)
        }
        viewModel.totalBalance.observe(this) {
            tvTotalBalance.text = it
        }
    }
}