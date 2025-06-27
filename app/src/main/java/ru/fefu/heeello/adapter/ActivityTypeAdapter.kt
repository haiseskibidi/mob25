package ru.fefu.heeello.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.heeello.R
import ru.fefu.heeello.model.ActivityType

class ActivityTypeAdapter(
    private val activityTypes: List<ActivityType>,
    private val onItemSelected: (ActivityType) -> Unit
) : RecyclerView.Adapter<ActivityTypeAdapter.ViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_activity_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activityType = activityTypes[position]
        holder.bind(activityType, position == selectedPosition)
        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
            onItemSelected(activityType)
        }
    }

    override fun getItemCount(): Int = activityTypes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.activityTypeName)
        private val iconTextView: TextView = itemView.findViewById(R.id.activityTypeIcon)

        fun bind(activityType: ActivityType, isSelected: Boolean) {
            nameTextView.text = activityType.name
            iconTextView.text = activityType.icon
            itemView.setBackgroundResource(if (isSelected) R.color.primary_light else android.R.color.transparent)
        }
    }
} 