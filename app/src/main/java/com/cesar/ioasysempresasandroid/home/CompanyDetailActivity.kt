package com.cesar.ioasysempresasandroid.home

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cesar.ioasysempresasandroid.R
import kotlinx.android.synthetic.main.company_detail_activity.*

class CompanyDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.company_detail_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.extras?.getParcelable<Company>(Company.flag)?.let {
            company_detail_txv_label.text = "E${it.id}"
            company_detail_txv_info.text = it.description
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}