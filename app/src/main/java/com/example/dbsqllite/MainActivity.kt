package com.example.dbsqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.dbsqllite.Adapter.ListItemAdapter
import com.example.dbsqllite.DBHelper.DBHelper
import com.example.dbsqllite.Model.Employee

class MainActivity : AppCompatActivity() {
    internal lateinit var db:DBHelper
    internal var lstEmployee:List<Employee> = ArrayList<Employee>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DBHelper(this)

        refreshData()

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener{
            val etId = findViewById<EditText>(R.id.etId)
            val etName = findViewById<EditText>(R.id.etName)
            val etEmail = findViewById<EditText>(R.id.etEmail)
            val emp = Employee(Integer.parseInt(etId.text.toString()), etName.text.toString(), etEmail.text.toString())
            db.addEmployee(emp)
            refreshData()
        }
    }

    private fun refreshData() {
        val etId = findViewById<EditText>(R.id.etId)
        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val lvItems = findViewById<ListView>(R.id.lvItems)
        lstEmployee = db.allEmployee
        val adapter = ListItemAdapter(this@MainActivity, lstEmployee, etId, etName, etEmail )

        lvItems.adapter = adapter
    }
}