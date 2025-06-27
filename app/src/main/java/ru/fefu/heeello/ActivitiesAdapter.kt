package ru.fefu.heeello

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private const val TYPE_DATE = 0
private const val TYPE_ACTIVITY = 1

class ActivitiesAdapter(
    private val activities: List<ActivityListItem>,
    private val onActivityClick: (ActivityListItem.Activity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (activities[position]) {
            is ActivityListItem.Activity -> TYPE_ACTIVITY
            is ActivityListItem.DateSeparator -> TYPE_DATE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_DATE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_date_header, parent, false)
            DateViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_activity, parent, false)
            ActivityViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_DATE) {
            (holder as DateViewHolder).bind(activities[position] as ActivityListItem.DateSeparator)
        } else {
            (holder as ActivityViewHolder).bind(activities[position] as ActivityListItem.Activity, onActivityClick)
        }
    }

    override fun getItemCount(): Int = activities.size

    class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.date_header_text)

        fun bind(dateItem: ActivityListItem.DateSeparator) {
            dateTextView.text = dateItem.date
        }
    }

    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val distanceTextView: TextView = itemView.findViewById(R.id.distance_text)
        private val durationTextView: TextView = itemView.findViewById(R.id.duration_text)
        private val activityTypeTextView: TextView = itemView.findViewById(R.id.activity_type_text)
        private val timestampTextView: TextView = itemView.findViewById(R.id.timestamp_text)
        private val userTagTextView: TextView = itemView.findViewById(R.id.user_tag_text)

        fun bind(activityItem: ActivityListItem.Activity, onActivityClick: (ActivityListItem.Activity) -> Unit) {
            distanceTextView.text = activityItem.distance
            durationTextView.text = activityItem.duration
            activityTypeTextView.text = activityItem.activityType
            timestampTextView.text = activityItem.timestamp

            if (activityItem.userTag != null) {
                userTagTextView.visibility = View.VISIBLE
                userTagTextView.text = activityItem.userTag
            } else {
                userTagTextView.visibility = View.GONE
            }
            
            itemView.setOnClickListener {
                onActivityClick(activityItem)
            }
        }
    }
} 