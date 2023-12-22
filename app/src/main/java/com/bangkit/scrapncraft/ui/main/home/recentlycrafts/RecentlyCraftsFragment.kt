package com.bangkit.scrapncraft.ui.main.home.recentlycrafts

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.scrapncraft.databinding.FragmentRecentlyCraftsBinding
import com.bangkit.scrapncraft.ui.addcrafts.AddCraftsActivity
import com.bangkit.scrapncraft.ui.main.MainViewModelFactory
import com.bangkit.scrapncraft.ui.main.home.listcrafts.ListCraftsAdapter
import com.bangkit.scrapncraft.ui.main.home.listcrafts.ListCraftsViewModel

class RecentlyCraftsFragment : Fragment() {
    private var _binding: FragmentRecentlyCraftsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecentlyCraftsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val factory: MainViewModelFactory = MainViewModelFactory.getInstance(requireActivity())
        val recentlyCraftsViewModel: RecentlyCraftsViewModel by viewModels {
            factory
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), AddCraftsActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}