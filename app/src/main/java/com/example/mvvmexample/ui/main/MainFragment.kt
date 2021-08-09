package com.example.mvvmexample.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmexample.R
import com.example.mvvmexample.data.MainRepository
import com.example.mvvmexample.data.remote.AppService
import com.example.mvvmexample.databinding.FragmentMainBinding
import com.example.mvvmexample.ui.MyViewModelFactory

class MainFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private var adapter = MainRVAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("TEST", "Before viewmodel provider")

        val retrofitService = AppService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        mainViewModel =
            ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)
        Log.e("TEST", "After viewmodel provider")


        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerView
        recyclerView.adapter = this.adapter
//        val textView: TextView = binding.textHome
//        mainViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        lifecycle.addObserver(mainViewModel)


        mainViewModel.trashList.observe(viewLifecycleOwner, {

            adapter.setTrashes(it)
        })

        mainViewModel.errorMessage.observe(viewLifecycleOwner, {
            Log.e("TEST Error", it)
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        mainViewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        mainViewModel.getTrashes()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}