package com.example.dbsqllite.Adapter

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.getSystemService
import com.example.dbsqllite.Model.Employee
import com.example.dbsqllite.R

class ListItemAdapter(internal var activity:Activity,
                      internal var lstEmployee: List<Employee>,
                      internal var edt_Id: EditText,
                      internal var edi_Name: EditText,
                      internal var edt_Email: EditText) : BaseAdapter() {
    internal var inflater: LayoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return lstEmployee.size
    }

    override fun getItem(position: Int): Any {
        return lstEmployee[position]
    }

    override fun getItemId(position: Int): Long {
        return lstEmployee[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView:View
        rowView = inflater.inflate(R.layout.row_layout, null)
        rowView.findViewById<TextView>(R.id.rowId).text =lstEmployee[position].id.toString()
        rowView.findViewById<TextView>(R.id.name).text =lstEmployee[position].name.toString()
        rowView.findViewById<TextView>(R.id.email).text =lstEmployee[position].email.toString()
        rowView.setOnClickListener{
            edt_Id.setText(lstEmployee[position].id.toString())
            edi_Name.setText(lstEmployee[position].name.toString())
            edt_Email.setText(lstEmployee[position].email.toString())
        }
        return rowView
    }
}