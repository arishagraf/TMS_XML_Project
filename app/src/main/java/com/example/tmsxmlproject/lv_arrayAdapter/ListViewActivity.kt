package com.example.tmsxmlproject.lv_arrayAdapter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tmsxmlproject.RecyclerViewHostActivity
import com.example.tmsxmlproject.R

class ListViewActivity : AppCompatActivity() {
    private val catNames = mutableListOf(
        "Барсик",
        "Мурзик",
        "Рыжик",
        "Мурка",
        "Дымка",
        "Кузя",
        "Симба",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.listView)
        val goToBtn = findViewById<Button>(R.id.goTo)

        goToBtn.setOnClickListener {
            val intent = Intent(this, RecyclerViewHostActivity::class.java)
            startActivity(intent)
        }

        val arrayAdaper = object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            catNames
        ) {
            // android.R.layout.simple_list_item_1 views access
            // for that we need "object:"
            override fun getView(
                position: Int, convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getView(position, convertView, parent)
                val item = getItem(position)
                val title = view.findViewById<TextView>(android.R.id.text1)
                title.text = item
                return view
            }
        }
        listView.adapter = arrayAdaper
        listView.setOnItemClickListener { parent, item, position, id ->
            catNames[position] = "Updated Cat" // possible because mutable list
            arrayAdaper.notifyDataSetChanged() // to refresh the data
        }
        listView.setOnItemLongClickListener { parent, view, position, id ->
             val selectedCatName = catNames[position]
             Toast.makeText(this, selectedCatName, Toast.LENGTH_SHORT).show()
            true
        }
    }
}