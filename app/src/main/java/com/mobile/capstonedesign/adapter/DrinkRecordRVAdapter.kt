package com.mobile.capstonedesign.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.dto.response.RecordResponseDTO
import kotlinx.android.synthetic.main.drink_record_form.view.*

class DrinkRecordRVAdapter(var context: Context?) : RecyclerView.Adapter<DrinkRecordRVAdapter.mViewHolder>() {

    private val records: ArrayList<RecordResponseDTO> = ArrayList()
//    var boardClick: BoardClick? = null

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var figure: TextView = itemView.tvDrinkRecordAmount
        var glass: TextView = itemView.tvDrinkRecordGlass
        var date: TextView = itemView.tvDrinkRecordDate
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): mViewHolder =
        mViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.drink_record_form, p0, false))

    override fun onBindViewHolder(p0: mViewHolder, position: Int) {
        p0.figure.text = records[position].figure.toString()
        p0.glass.text = records[position].glass.toString()
        p0.date.text = records[position].drink_date

//        if(boardClick != null)
//        {
//            p0.itemView.setOnClickListener { v ->
//                boardClick?.onClick(v, position, writings[position].board_no)
//            }
//        }
    }

    override fun getItemCount(): Int = records.size

    fun update(records: ArrayList<RecordResponseDTO>) {
        this.records.clear()
        this.records.addAll(records)
        notifyDataSetChanged()
    }
}