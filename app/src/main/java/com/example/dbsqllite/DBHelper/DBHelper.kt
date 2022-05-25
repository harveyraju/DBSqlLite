package com.example.dbsqllite.DBHelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import com.example.dbsqllite.Model.Employee

class DBHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object{
        private const val DATABASE_NAME = "Studentapp.db"
        private const val DATABASE_VER = 1

        private const val TABLE_NAME = "Employee"
        private const val COL_ID = "Id"
        private const val COL_NAME = "Name"
        private const val COL_EMAIL = "Email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY:String = ("CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT, $COL_EMAIL TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    val allEmployee:List<Employee>
        @SuppressLint("Range")
        get(){
        val lstEmployee = ArrayList<Employee>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db:SQLiteDatabase =  this.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        if(cursor.moveToFirst())
        {
            do{
                val employee = Employee()
                employee.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                employee.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                employee.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))
                lstEmployee.add(employee)
            }while(cursor.moveToNext())
        }
            db.close()
        return lstEmployee
    }

    fun addEmployee(emp:Employee)
    {
        val db:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, emp.id)
        values.put(COL_NAME, emp.name)
        values.put(COL_EMAIL, emp.email)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateEmployee(emp:Employee):Int
    {
        val db:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, emp.id)
        values.put(COL_NAME, emp.name)
        values.put(COL_EMAIL, emp.email)

        return db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(emp.id.toString()))

    }

    fun DeleteEmployee(emp:Employee)
    {
        val db:SQLiteDatabase = this.writableDatabase

        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(emp.id.toString()))
        db.close()
    }
}