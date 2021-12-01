package com.turbazik.healthera.ui.adherence

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.turbazik.healthera.R
import com.turbazik.healthera.databinding.ItemAdherenceBinding
import com.turbazik.healthera.ui.model.AdherenceDvo
import java.util.Locale
import java.util.Calendar
import kotlin.collections.ArrayList

private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

class AdherenceAdapter : RecyclerView.Adapter<AdherenceVH>() {

    private val data: ArrayList<AdherenceDvo> = ArrayList()

    var itemClickListener: (AdherenceDvo) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdherenceVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAdherenceBinding.inflate(inflater, parent, false)

        return AdherenceVH(binding = binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AdherenceVH, position: Int) = holder.run {
        val item = data[position]
        binding.status.text = item.action
        binding.time.text = getDate(timestamp = item.alarmTime)
        binding.description.text = binding.root.context.getString(
            R.string.adherence_description,
            item.medicineName,
            item.doseQuantity)
        binding.root.setOnClickListener {
            itemClickListener.invoke(item)
        }
    }

    fun setItems(items: List<AdherenceDvo>) {
        data.clear()
        data.addAll(items)
        notifyItemRangeInserted(0, items.count())
    }

    private fun getDate(timestamp: Int?): String {
        if (timestamp == null) return ""

        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = timestamp * 1000L
        return DateFormat.format(DATE_FORMAT, calendar).toString()
    }
}

class AdherenceVH(val binding: ItemAdherenceBinding) : RecyclerView.ViewHolder(binding.root)