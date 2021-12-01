package com.turbazik.healthera.ui.adherence

import android.app.Dialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.turbazik.healthera.R
import com.turbazik.healthera.databinding.FragmentAdherenceDetailBinding
import java.util.Locale
import java.util.Calendar

private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

class AdherenceDetailFragment : DialogFragment() {

    private var _binding: FragmentAdherenceDetailBinding? = null

    // This property is only valid between onCreateDialog and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: AdherenceDetailFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentAdherenceDetailBinding.inflate(LayoutInflater.from(context))
        initViews()
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        binding.status.text = args.adherence.action
        binding.time.text = getDate(timestamp = args.adherence.alarmTime)
        binding.description.text = binding.root.context.getString(
            R.string.adherence_description,
            args.adherence.medicineName,
            args.adherence.doseQuantity)
    }

    private fun getDate(timestamp: Int?): String {
        if (timestamp == null) return ""

        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = timestamp * 1000L
        return DateFormat.format(DATE_FORMAT, calendar).toString()
    }
}