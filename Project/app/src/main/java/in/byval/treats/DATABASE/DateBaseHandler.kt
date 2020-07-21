package `in`.byval.assist.DATABASE

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DateBaseHandler(context: Context?) : SQLiteOpenHelper(context,DBName,null, DBVersion) {
    override fun onCreate(db: SQLiteDatabase) {
        //db.execSQL(Info_master_table.CreateTableStatement);

    }

    override fun onUpgrade(db: SQLiteDatabase,oldVersion: Int,newVersion: Int) {
        //db.execSQL(Info_master_table.DropTableStatement);
    }

    companion object {
        var DBName = "TreatsDB"
        var DBVersion = 1
    }
}