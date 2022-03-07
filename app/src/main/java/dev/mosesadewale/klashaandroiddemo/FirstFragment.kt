package dev.mosesadewale.klashaandroiddemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.mosesadewale.klashaandroiddemo.adapter.CollectionsAdapter
import dev.mosesadewale.klashaandroiddemo.data.Product
import dev.mosesadewale.klashaandroiddemo.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val activityViewModel: ActivityViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CollectionsAdapter (requireContext()){
            activityViewModel.addToCart(it)
        }
        adapter.submitList(activityViewModel.products.value)
        binding.recyclerViewCollections.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCollections.adapter = adapter

        activityViewModel.cartCount.observeForever {
            binding.tvCartCount.text = it.toString()
            Log.d("lol", it.toString())
        }

        binding.mbCart.setOnClickListener {
            if (activityViewModel.cartCount.value == 0){
                Toast.makeText(requireContext(), "Cart Empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}