package ru.fefu.heeello

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyActivityFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_activity, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val mockActivities = listOf(
            ActivityListItem.DateSeparator("Вчера"),
            ActivityListItem.Activity("1", "14.32 км", "2 часа 46 минут", "Серфинг", "14 часов назад"),
            ActivityListItem.DateSeparator("Май 2022 года"),
            ActivityListItem.Activity("2", "1 000 м", "60 минут", "Велосипед", "29.05.2022")
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ActivitiesAdapter(mockActivities) {
            // Переход на экран детализации
            val intent = Intent(requireActivity(), ActivityDetailsActivity::class.java)
            // Сюда можно будет добавить передачу данных об активности
            // intent.putExtra("ACTIVITY_ID", it.id)
            startActivity(intent)
        }
    }
} 