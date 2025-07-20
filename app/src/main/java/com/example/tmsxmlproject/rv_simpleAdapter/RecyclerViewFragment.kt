package com.example.tmsxmlproject.rv_simpleAdapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmsxmlproject.R
import com.example.tmsxmlproject.rv_listAdapter.RVDiffUtilsFragment

class RecyclerViewFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StringAdapter
    private lateinit var goToRecyclerViewDiffUtilsBtn: Button
    private val itemList = mutableListOf(
        "Cat",
        "Dog",
        "Elephant",
        "Lion",
        "Tiger",
        "Zebra",
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goToRecyclerViewDiffUtilsBtn = view.findViewById(R.id.goToRecyclerViewDiffUtils)
        goToRecyclerViewDiffUtilsBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RVDiffUtilsFragment())
                .addToBackStack(null)
                .commit()
        }

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        adapter = StringAdapter(itemList, object : RecyclerViewClickListener {
            override fun onClick(name: String) {
                Toast.makeText(requireContext(), "clicked: $name", Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                adapter.removeAt(position)
                Toast.makeText(requireContext(), "Item removed", Toast.LENGTH_SHORT).show()
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}