package dev.mosesadewale.klashaandroiddemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.mosesadewale.klashaandroiddemo.databinding.BottomsheetPaymentBinding

class PaymentBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomsheetPaymentBinding? = null
    private val binding get() = _binding!!
    private val activityViewModel: ActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomsheetPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

        companion object {
        const val TAG = "PaymentBottomSheet"
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.tvTotal.text = activityViewModel.getCartPrice().toString()


        }

        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }
    }
