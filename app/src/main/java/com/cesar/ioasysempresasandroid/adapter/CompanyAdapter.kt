package com.cesar.ioasysempresasandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cesar.ioasysempresasandroid.R
import com.cesar.ioasysempresasandroid.home.Company
import kotlinx.android.synthetic.main.company_adapter.view.*
import java.util.*

class CompanyAdapter(private val click: (company: Company) -> Unit) :
    RecyclerView.Adapter<CompanyAdapter.ItemView>() {

    private val items: ArrayList<Company> = arrayListOf()

    fun setItens(companies: List<Company>) {
        items.clear()
        items.addAll(companies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        val viewItem =
            LayoutInflater.from(parent.context).inflate(R.layout.company_adapter, parent, false)
        return ItemView(viewItem)
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        val company = items[position]
        holder.bind(company)
        holder.itemView.setOnClickListener { click(company) }
    }

    override fun getItemCount() = items.size

    class ItemView(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(company: Company) {
            with(view) {
                company_adapter_txv_label.text = "E${company.id}"
                company_adapter_txv_company.text = company.enterpriseName
                company_adapter_txv_business.text = company.enterpriseType.name
                company_adapter_txv_country.text = company.city
            }
        }
    }
}