package dev.mosesadewale.klashaandroiddemo

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.klasha.android.KlashaSDK
import com.klasha.android.model.*
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
            activityViewModel.initializeKlasha(requireActivity())

            val email = activityViewModel.email
            val name = activityViewModel.name
            val phone = activityViewModel.phone

            binding.btnCard.setOnClickListener {
                val cardNumber = binding.cardNumber.text.toString()
                val cardCvv = binding.cardCvv.text.toString()
                val cardMonth = binding.tvCardMonth.text.toString()
                val cardYear = binding.tvCardYear.text.toString()

                val amount = activityViewModel.getCartPrice()

                if (cardNumber.isEmpty() or cardCvv.isEmpty() or cardMonth.isEmpty() or cardYear.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Some required card fields are empty",
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }

                toggleProgressBar()

                val card = Card(cardNumber, cardMonth.toInt(), cardYear.toInt(), cardCvv.toInt())
                val charge = Charge(amount.toDouble(), email, name, card, null, phone)

                KlashaSDK.chargeCard(charge, object : KlashaSDK.TransactionCallback{
                    override fun error(ctx: Activity, message: String) {
                        ctx.runOnUiThread {
                            toggleProgressBar(false)
                            Snackbar.make(binding.root,"Transaction Failed $message", Snackbar.LENGTH_LONG).show()
                        }
                    }

                    override fun success(ctx: Activity, transactionReference: String) {
                        ctx.runOnUiThread {
                            toggleProgressBar(false)
                            Snackbar.make(binding.root,"Transaction Successful $transactionReference", Snackbar.LENGTH_LONG).show()
                        }
                    }

                    override fun transactionInitiated(transactionReference: String) {
                        Snackbar.make(binding.root,"Transaction Initiated $transactionReference", Snackbar.LENGTH_LONG).show()
                    }
                })
            }

            binding.btnBank.setOnClickListener {
                val amount = activityViewModel.getCartPrice()

                val charge = Charge(amount, email, name, null, null, phone)

                toggleProgressBar()
                KlashaSDK.bankTransfer(charge, object : KlashaSDK.BankTransferTransactionCallback{
                    override fun error(ctx: Activity, message: String) {
                        ctx.runOnUiThread {
                            toggleProgressBar(false)
                            Snackbar.make(binding.root,"Transaction Failed $message", Snackbar.LENGTH_INDEFINITE).show()
                        }
                    }

                    override fun success(ctx: Activity, bankTransferResponse: BankTransferResp) {
                        ctx.runOnUiThread {
                            toggleProgressBar(false)
                            Snackbar.make(binding.root,"Transaction Successful $bankTransferResponse", Snackbar.LENGTH_LONG).show()
                        }
                    }

                    override fun transactionInitiated(transactionReference: String) {
                        Snackbar.make(binding.root,"Transaction Initiated $transactionReference", Snackbar.LENGTH_LONG).show()
                    }
                })
            }

            binding.btnWallet.setOnClickListener {
                val amount = activityViewModel.getCartPrice()

                val charge = Charge(amount, email, name, null, null, phone)

                toggleProgressBar()

                KlashaSDK.wallet(charge, object : KlashaSDK.TransactionCallback{
                    override fun error(ctx: Activity, message: String) {
                        ctx.runOnUiThread {
                            toggleProgressBar(false)
                            Snackbar.make(binding.root,"Transaction Failed $message", Snackbar.LENGTH_LONG).show()
                        }
                    }

                    override fun success(ctx: Activity, transactionReference: String) {
                        ctx.runOnUiThread {
                            toggleProgressBar(false)
                            Snackbar.make(binding.root,"Transaction Successful $transactionReference", Snackbar.LENGTH_LONG).show()
                        }
                    }


                    override fun transactionInitiated(transactionReference: String) {
                        Snackbar.make(binding.root,"Transaction Initiated $transactionReference", Snackbar.LENGTH_LONG).show()
                    }
                })

            }

            binding.btnMMoney.setOnClickListener {
                val amount = activityViewModel.getCartPrice()

                val network = binding.network.toString()
                val voucher = binding.voucher.text.toString()

                val mobileMoney = MobileMoney(voucher, Network.valueOf(network))
                val charge = Charge(amount, email, name, null, mobileMoney, phone)

                toggleProgressBar()

                KlashaSDK.mobileMoney(charge, object : KlashaSDK.TransactionCallback{
                    override fun error(ctx: Activity, message: String) {
                        ctx.runOnUiThread {
                            toggleProgressBar(false)
                            Snackbar.make(binding.root,"Transaction Failed $message", Snackbar.LENGTH_LONG).show()
                        }
                    }

                    override fun success(ctx: Activity, transactionReference: String) {
                        ctx.runOnUiThread {
                            toggleProgressBar(false)
                            Snackbar.make(binding.root,"Transaction Successful $transactionReference", Snackbar.LENGTH_LONG).show()
                        }
                    }

                    override fun transactionInitiated(transactionReference: String) {
                        Snackbar.make(binding.root,"Transaction Initiated $transactionReference", Snackbar.LENGTH_LONG).show()
                    }
                })
            }

            binding.btnMpesa.setOnClickListener {
                val amount = activityViewModel.getCartPrice()

                val charge = Charge(amount, email, name, null, null, phone)

                toggleProgressBar()

                KlashaSDK.mpesa(charge, object : KlashaSDK.TransactionCallback{
                    override fun error(ctx: Activity, message: String) {
                        ctx.runOnUiThread {
                            toggleProgressBar(false)
                            Snackbar.make(binding.root,"Transaction Failed $message", Snackbar.LENGTH_LONG).show()
                        }
                    }

                    override fun success(ctx: Activity, transactionReference: String) {
                        ctx.runOnUiThread {
                            toggleProgressBar(false)
                            Snackbar.make(binding.root,"Transaction Successful $transactionReference", Snackbar.LENGTH_LONG).show()
                        }
                    }

                    override fun transactionInitiated(transactionReference: String) {
                        Snackbar.make(binding.root,"Transaction Initiated $transactionReference", Snackbar.LENGTH_LONG).show()
                    }
                })

            }
        }

    fun toggleProgressBar(flag: Boolean = true){
        var visibility = View.GONE
        if (flag) visibility = View.VISIBLE
        binding.progressBar.visibility = visibility
    }

        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }
    }
