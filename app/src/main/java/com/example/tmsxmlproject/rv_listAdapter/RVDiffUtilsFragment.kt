package com.example.tmsxmlproject.rv_listAdapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmsxmlproject.R
import com.example.tmsxmlproject.databinding.FragmentRVDiffUtilsBinding
import com.example.tmsxmlproject.scroll_nested_views.Scroll_Nested_Fragment

class RVDiffUtilsFragment : Fragment() {

    private var _binding: FragmentRVDiffUtilsBinding? = null
    private val binding get() = _binding!!

    private val adapter = HorizontalStringAdapter({
        Toast.makeText(requireContext(), "clicked: $it", Toast.LENGTH_SHORT).show()
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRVDiffUtilsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goToScrollings.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Scroll_Nested_Fragment())
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false,
        )
        binding.recyclerView.adapter = adapter

        val itemList = listOf(
            "One", "Two",
            "Three", "Four",
            "Five", "Six",
            "Seven", "Eight",
            "Nine", "Ten",
        )
        adapter.submitList(itemList)

        // here no notifyDataSetChanged will be used
        adapter.submitList(itemList.plus("Hundred"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}