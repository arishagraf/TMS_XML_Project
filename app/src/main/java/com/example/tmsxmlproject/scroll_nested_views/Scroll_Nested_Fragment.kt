package com.example.tmsxmlproject.scroll_nested_views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmsxmlproject.databinding.FragmentScrollNestedBinding

class Scroll_Nested_Fragment : Fragment() {

    private var _binding: FragmentScrollNestedBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HorizontalScrollNestedStringAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScrollNestedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HorizontalScrollNestedStringAdapter {
            Toast.makeText(requireContext(), "Clicked: $it", Toast.LENGTH_SHORT).show()
        }

        binding.horizontalRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.horizontalRecycler.adapter = adapter

        val items = listOf(
            "One", "Two",
            "Three", "Four",
            "Five", "Six",
            "Seven", "Eight",
            "Nine", "Ten",
        )
        adapter.submitList(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
