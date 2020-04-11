package com.example.td4exo3

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing the array lists and the adapter
        var itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

   var tec : String = ""


       textView1.addTextChangedListener(object : TextWatcher {


           override fun afterTextChanged(s: Editable?) {
               adapter.filter.filter(s)
                          }

           override fun beforeTextChanged(s: CharSequence, start: Int,
                                          count: Int, after: Int) {
           }

           override fun onTextChanged(s: CharSequence, start: Int,
                                      before: Int, count: Int) {
               adapter.filter.filter(s)
           }
       })

        date.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                //textView1.setText("" + dayOfMonth + "/ " + month + " /" + year)
                tec = "" + dayOfMonth + "/ " + month + " /" + year
            }, year, month, day)
            dpd.show()
        }

        add.setOnClickListener {

            itemlist.add(tec + " : " + editText.text.toString() )
            listView.adapter =  adapter
            adapter.notifyDataSetChanged()
            // This is because every time when you add the item the input      space or the eidt text space will be cleared
            editText.text.clear()
        }

        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }

        clear.setOnClickListener {

            itemlist.clear()
            adapter.notifyDataSetChanged()

        }
        listView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(this, "You Selected the item --> "+itemlist.get(i), android.widget.Toast.LENGTH_SHORT).show()
        }
    }



}
