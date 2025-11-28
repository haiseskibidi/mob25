package ru.fefu.heeello

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.fefu.heeello.db.AppDatabase
import java.text.SimpleDateFormat
import java.util.*
import android.location.Location

class MyActivityFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var adapter: ActivitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_activity, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        fab = view.findViewById(R.id.fab)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ActivitiesAdapter(emptyList()) {
            // Click listener
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val activityDao = AppDatabase.getDatabase(requireContext()).activityDao()
        activityDao.getAllActivities().observe(viewLifecycleOwner, Observer { activities ->
            val items = activities.map {
                val durationMillis = it.endTime - it.startTime
                val durationString = if (durationMillis < 60000) {
                    "${durationMillis / 1000} секунд"
                } else {
                    "${durationMillis / 1000 / 60} минут"
                }

                val distanceString = if (it.distance >= 1000) {
                    String.format(Locale.US, "%.2f км", it.distance / 1000)
                } else {
                    String.format(Locale.US, "%.0f м", it.distance)
                }

                val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                val activityName = when (it.activityType) {
                    ru.fefu.heeello.model.ActivityTypeEnum.BICYCLE -> "Велосипед"
                    ru.fefu.heeello.model.ActivityTypeEnum.RUNNING -> "Бег"
                    ru.fefu.heeello.model.ActivityTypeEnum.WALKING -> "Ходьба"
                }

                ActivityListItem.Activity(
                    id = it.id.toString(),
                    distance = distanceString,
                    duration = durationString,
                    activityType = activityName,
                    timestamp = sdf.format(Date(it.startTime))
                )
            }
            adapter.updateData(items)
        })

        fab.setOnClickListener {
            val intent = Intent(requireActivity(), NewActivity::class.java)
            startActivity(intent)
        }
    }
} 