package ru.fefu.heeello

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.heeello.adapter.ActivityTypeAdapter
import ru.fefu.heeello.databinding.ActivityNewBinding
import ru.fefu.heeello.model.ActivityType
import ru.fefu.heeello.db.AppDatabase
import ru.fefu.heeello.db.ActivityEntity
import ru.fefu.heeello.model.ActivityTypeEnum
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random

class NewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewBinding
    private lateinit var activityTypeAdapter: ActivityTypeAdapter
    private val activityTypes = listOf(
        ActivityType("Велосипед", "🚲", 7f),
        ActivityType("Бег", "🏃", 2f),
        ActivityType("Шаг", "🚶", 1f)
    )
    private var selectedActivityType: ActivityType = activityTypes[0]

    private var seconds = 0
    private var distanceMeters = 0f
    private var isRunning = false
    private val handler = Handler(Looper.getMainLooper())
    private val timerRunnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                seconds++
                distanceMeters += selectedActivityType.speedMetersPerSecond
                updateUi()
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.startButton.setOnClickListener {
            startActivity()
        }

        binding.pauseButton.setOnClickListener {
            pauseActivity()
        }

        binding.finishButton.setOnClickListener {
            finishActivity(true)
        }
    }

    private fun setupRecyclerView() {
        activityTypeAdapter = ActivityTypeAdapter(activityTypes) { activityType ->
            selectedActivityType = activityType
        }
        binding.startLayout.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.activityTypeRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@NewActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = activityTypeAdapter
        }
    }

    private fun startActivity() {
        isRunning = true
        binding.startLayout.visibility = View.GONE
        binding.trackingLayout.visibility = View.VISIBLE
        binding.trackingActivityTypeTextView.text = selectedActivityType.name
        handler.post(timerRunnable)
    }

    private fun pauseActivity() {
        isRunning = !isRunning
        if (isRunning) {
            handler.post(timerRunnable)
            binding.pauseButton.setImageResource(android.R.drawable.ic_media_pause)
        } else {
            handler.removeCallbacks(timerRunnable)
            binding.pauseButton.setImageResource(android.R.drawable.ic_media_play)
        }
    }

    private fun finishActivity(saveToDb: Boolean) {
        isRunning = false
        handler.removeCallbacks(timerRunnable)
        
        val finalSeconds = seconds
        val finalDistance = distanceMeters

        if (saveToDb) {
            val activityDao = AppDatabase.getDatabase(applicationContext).activityDao()
            GlobalScope.launch {
                val activityTypeEnum = when (selectedActivityType.name) {
                    "Велосипед" -> ActivityTypeEnum.BICYCLE
                    "Бег" -> ActivityTypeEnum.RUNNING
                    else -> ActivityTypeEnum.WALKING
                }
                val startTime = Date().time - finalSeconds * 1000
                val endTime = Date().time
                val coordinates = (1..10).map {
                    Pair(Random.nextDouble(50.0, 51.0), Random.nextDouble(30.0, 31.0))
                }
                activityDao.insertActivity(
                    ActivityEntity(
                        activityType = activityTypeEnum,
                        startTime = startTime,
                        endTime = endTime,
                        coordinates = coordinates,
                        distance = finalDistance
                    )
                )
            }
        }

        seconds = 0
        distanceMeters = 0f
        updateUi()
        binding.startLayout.visibility = View.VISIBLE
        binding.trackingLayout.visibility = View.GONE
        finish()
    }

    private fun updateUi() {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60
        binding.timeTextView.text = String.format("%02d:%02d:%02d", hours, minutes, secs)

        if (distanceMeters >= 1000) {
            binding.distanceTextView.text = String.format("%.2f км", distanceMeters / 1000)
        } else {
            binding.distanceTextView.text = String.format("%.0f м", distanceMeters)
        }
    }
} 