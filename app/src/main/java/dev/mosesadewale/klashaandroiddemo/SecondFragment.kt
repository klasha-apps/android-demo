package dev.mosesadewale.klashaandroiddemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dev.mosesadewale.klashaandroiddemo.adapter.SummaryAdapter
import dev.mosesadewale.klashaandroiddemo.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val activityViewModel: ActivityViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SummaryAdapter (requireContext())
        adapter.submitList(activityViewModel.cart.value?.toList())
        binding.recyclerViewSummary.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewSummary.adapter = adapter

        val paymentModalBottomSheet = PaymentBottomSheet()
        binding.btnPayment.setOnClickListener {
            paymentModalBottomSheet.show(childFragmentManager, PaymentBottomSheet.TAG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}