package ru.fefu.heeello

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UsersActivityFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users_activity, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mockActivities = listOf(
            ActivityListItem.DateSeparator("Вчера"),
            ActivityListItem.Activity("3", "14.32 км", "2 часа 46 минут", "Серфинг", "14 часов назад", "@van_darkholme"),
            ActivityListItem.Activity("4", "228 м", "14 часов 48 минут", "Качели", "14 часов назад", "@techniquepasha"),
            ActivityListItem.Activity("5", "10 км", "1 час 10 минут", "Езда на кадилак", "14 часов назад", "@morgen_shtern")
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ActivitiesAdapter(mockActivities) {
            val intent = Intent(requireActivity(), ActivityDetailsActivity::class.java)
            startActivity(intent)
        }
    }
} 