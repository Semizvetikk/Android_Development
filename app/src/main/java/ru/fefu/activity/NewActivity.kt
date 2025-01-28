package ru.fefu.activity

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class NewActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var btnRun: Button
    private lateinit var btnBike: Button
    private lateinit var btnWalk: Button
    private lateinit var btnStart: Button
    private lateinit var btnStopRecording: Button
    private lateinit var mapViewRecording: MapView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private lateinit var recordingLayout: LinearLayout
    private lateinit var tvSelectedActivity: TextView

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey("0e1542d0-2634-4fca-8d42-ff27a2a12953")
        MapKitFactory.initialize(this)

        setContentView(R.layout.activity_new)

        mapView = findViewById(R.id.mapview)

        // Настройка карты
        mapView.map.move(
            CameraPosition(Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),  // Москва
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )

        val bottomSheet = findViewById<LinearLayout>(R.id.bottomSheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        bottomSheetBehavior.halfExpandedRatio = 0.5f

        bottomSheetBehavior.isHideable = false

        bottomSheetBehavior.peekHeight = 250

        btnRun = findViewById(R.id.btnRun)
        btnBike = findViewById(R.id.btnBike)
        btnWalk = findViewById(R.id.btnWalk)
        btnStart = findViewById(R.id.btnStart)
        btnStopRecording = findViewById(R.id.btnStopRecording)

        // Инициализация второго состояния
        recordingLayout = findViewById(R.id.recordingLayout)
        tvSelectedActivity = findViewById(R.id.tvSelectedActivity)

        // Обработка выбора активности
        btnRun.setOnClickListener {
            Toast.makeText(this, "Выбран Бег", Toast.LENGTH_SHORT).show()
            btnRun.isSelected = true
            btnBike.isSelected = false
            btnWalk.isSelected = false
        }

        btnBike.setOnClickListener {
            Toast.makeText(this, "Выбран Велосипед", Toast.LENGTH_SHORT).show()
            btnRun.isSelected = false
            btnBike.isSelected = true
            btnWalk.isSelected = false
        }

        btnWalk.setOnClickListener {
            Toast.makeText(this, "Выбрана Ходьба", Toast.LENGTH_SHORT).show()
            btnRun.isSelected = false
            btnBike.isSelected = false
            btnWalk.isSelected = true
        }

        btnStart.setOnClickListener {
            val selectedActivity = when {
                btnRun.isSelected -> "Бег"
                btnBike.isSelected -> "Велосипед"
                btnWalk.isSelected -> "Ходьба"
                else -> "Не выбрано"
            }

            // Переход на второе состояние
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            recordingLayout.visibility = LinearLayout.VISIBLE
            tvSelectedActivity.text = "Выбрано: $selectedActivity"

            mapViewRecording.map.move(
                CameraPosition(Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),  // Москва
                Animation(Animation.Type.SMOOTH, 0f),
                null
            )

        }
        btnStopRecording.setOnClickListener {
            recordingLayout.visibility = LinearLayout.GONE
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
        mapViewRecording.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        mapViewRecording.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}