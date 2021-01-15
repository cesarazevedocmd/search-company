package com.cesar.ioasysempresasandroid.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.cesar.ioasysempresasandroid.*
import com.cesar.ioasysempresasandroid.adapter.CompanyAdapter
import com.cesar.ioasysempresasandroid.general.IoasysError
import com.cesar.ioasysempresasandroid.util.color
import com.cesar.ioasysempresasandroid.util.goTo
import com.cesar.ioasysempresasandroid.util.hide
import com.cesar.ioasysempresasandroid.util.show
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity(),
    SearchCompanyContract.View,
    SearchView.OnQueryTextListener {

    override val presenter: SearchCompanyContract.Presenter by inject { parametersOf(this) }
    private lateinit var adapter: CompanyAdapter
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setSupportActionBar(my_toolbar)
        startAdapters()
        presenter.search("")
    }

    private fun startAdapters() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = CompanyAdapter { openDetails(it) }
        home_activity_rcv.layoutManager = layoutManager
        home_activity_rcv.adapter = adapter
    }

    private fun openDetails(company: Company) {
        val bundle = Bundle()
        bundle.putParcelable(Company.flag, company)
        goTo(CompanyDetailActivity::class.java, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem?.actionView as SearchView
        with(searchView) {
            setIconifiedByDefault(false)
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(this@HomeActivity)
            findViewById<EditText>(R.id.search_src_text).setTextColor(color(R.color.white))
            findViewById<ImageView>(R.id.search_close_btn).setOnClickListener {
                searchView.findViewById<EditText>(R.id.search_src_text).setText("")
                searchView.onActionViewCollapsed()
                searchItem.collapseActionView()
                presenter.search("")
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        presenter.search(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun showMessageItemsNotFound() {
        home_activity_txv_start.show()
        home_activity_txv_start.text = getString(R.string.label_items_not_found)
    }

    override fun showDialog() {
        home_activity_pgb_loading.show()
    }

    override fun hideDialog() {
        home_activity_pgb_loading.hide()
    }

    override fun show(companies: List<Company>) {
        home_activity_txv_start.hide()
        adapter.setItens(companies)
    }

    override fun clearList() {
        adapter.setItens(listOf())
    }

    override fun error(error: IoasysError) {
        home_activity_txv_start.show()
        home_activity_txv_start.text = error.message
    }
}