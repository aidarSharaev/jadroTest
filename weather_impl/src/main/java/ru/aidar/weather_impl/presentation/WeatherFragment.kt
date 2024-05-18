package ru.aidar.weather_impl.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.aidar.common.base.BaseFragment
import ru.aidar.common.di.FeatureUtils
import ru.aidar.common.utils.JadroAppStyle.CardColor2
import ru.aidar.common.utils.JadroAppStyle.JadroFontFamily
import ru.aidar.common.utils.JadroAppStyle.WhiteBlue
import ru.aidar.common.utils.JadroAppStyle.backGroundGradient
import ru.aidar.weather_api.di.WeatherFeatureApi
import ru.aidar.weather_api.model.ForecastLocalModel
import ru.aidar.weather_api.model.IconResult
import ru.aidar.weather_api.model.NextDay
import ru.aidar.weather_impl.databinding.WeatherFragmentBinding
import ru.aidar.weather_impl.di.WeatherFeatureComponent

fun IconResult.getImage(): Int {
    return if(this is IconResult.Cold) {
        ru.aidar.common.R.drawable.ic_cold
    } else {
        ru.aidar.common.R.drawable.ic_sun
    }
}

class WeatherFragment() : BaseFragment<WeatherViewModel>(), ActivityResultCallback<Boolean> {

    override fun onActivityResult(result: Boolean) {
        if(result) {
            //
        } else if(!ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                LOCATION_PERMISSION
            )
        ) {
            showSettingsDialogInUi = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(
                    LOCATION_PERMISSION
                ), PERMISSION_REQ_CODE
            )
        }
    }

    companion object {
        private const val LOCATION_PERMISSION = Manifest.permission.ACCESS_COARSE_LOCATION
        private const val PERMISSION_REQ_CODE = 100
    }

    private lateinit var binding: WeatherFragmentBinding
    private var showPermissionInUi by mutableStateOf(false)
    private var showSettingsDialogInUi by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestRuntimePermission()
    }


    val permissionResultLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {granted: Boolean->
        onActivityResult(granted)
    }


    private fun requestRuntimePermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                LOCATION_PERMISSION,
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.d("AIDARCHIK", "first")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                LOCATION_PERMISSION,
            ) -> {
                Log.d("AIDARCHIK", "second")
                showPermissionInUi = true
            }

            else -> {
                Log.d("AIDARCHIK", "third")
                permissionResultLauncher.launch(LOCATION_PERMISSION)
            }
        }
    }


    override fun inject() {
        FeatureUtils.getFeature<WeatherFeatureComponent>(this, WeatherFeatureApi::class.java)
            .weatherLoginComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = WeatherFragmentBinding.inflate(inflater)
        val view = binding.root

        binding.weatherComposeView.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                WeatherScreen()
            }
        }
        return view
    }
    

    @Preview
    @Composable
    fun WeatherScreen() {

        Scaffold(
            //snackbarHost =
        ) {

            if(showPermissionInUi) {
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                        Button(onClick = {
                            ActivityCompat.requestPermissions(
                                requireActivity(), arrayOf(
                                    LOCATION_PERMISSION
                                ), PERMISSION_REQ_CODE
                            )
                        }) {
                            Text("Ok")
                        }
                        showPermissionInUi = false
                    },
                    dismissButton = {
                        Button(onClick = { showPermissionInUi = false }) {
                            Text("Cancel")
                        }
                    },
                    text = { Text("AIDARCHIK loch") }
                )
            }
            if(showSettingsDialogInUi) {
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                        Button(onClick = {
                            startActivity(
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = Uri.fromParts("package", `package`, null)
                                }
                            )
                        }) {
                            Text("Ok")
                        }
                        showSettingsDialogInUi = false
                    },
                    dismissButton = {
                        Button(onClick = { showPermissionInUi = false }) {
                            Text("Cancel")
                        }
                    },
                    text = { Text("AIDARCHIK loch") }
                )
            }

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(
                        brush = backGroundGradient
                    )
                    .padding(horizontal = 25.dp)
            ) {
                Text(
                    text = stringResource(ru.aidar.common.R.string.jadroweather),
                    color = Color.White,
                    modifier = Modifier.padding(top = 18.dp, start = 14.dp),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    fontFamily = JadroFontFamily
                )
                Card(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 40.dp),
                    colors = CardDefaults.elevatedCardColors(containerColor = WhiteBlue)
                ) {
                    Text(
                        text = "Kazan: 34.34, 12.34\n18.05.2024",
                        modifier = Modifier.padding(7.dp),
                        fontFamily = JadroFontFamily
                    )
                }
                Card(
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = CardColor2
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .height(150.dp),
                    border = BorderStroke(width = 2.dp, color = WhiteBlue),
                    shape = RoundedCornerShape(35.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painterResource(id = ru.aidar.common.R.drawable.ic_cold),
                            contentDescription = null,
                            modifier = Modifier
                        )
                        Text(
                            text = buildAnnotatedString {
                                pushStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = WhiteBlue,
                                        fontFamily = JadroFontFamily
                                    )
                                )
                                pushStyle(SpanStyle(fontSize = 52.sp))
                                append("${list.currentTempC}℃\n")
                                pop()
                                pushStyle(SpanStyle(fontSize = 15.sp))
                                append("Data as of ${list.lastUpdateTime}")
                            }
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 30.dp)
                        .clip(
                            RoundedCornerShape(35.dp)
                        )
                        .background(WhiteBlue)
                ) {
                    Row(
                        modifier = Modifier.padding(top = 28.dp, start = 25.dp, bottom = 10.dp)
                    ) {
                        Text(
                            "Future Weather",
                            fontFamily = JadroFontFamily,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    LazyColumn {
                        items(list.nextDays.size) { item ->
                            if(item != 1) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(105.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Image(
                                        painterResource(id = list.nextDays[item].icon.getImage()),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(70.dp)
                                    )
                                    Text(
                                        text = buildAnnotatedString {
                                            pushStyle(
                                                SpanStyle(
                                                    fontWeight = FontWeight.Bold,
                                                    fontFamily = JadroFontFamily
                                                )
                                            )
                                            pushStyle(SpanStyle(fontSize = 28.sp))
                                            append("${list.nextDays[item].maxTemp}℃\n")
                                            pop()
                                            pushStyle(SpanStyle(fontSize = 18.sp))
                                            append("${list.nextDays[item].minTemp}℃")
                                        }
                                    )

                                    Text(
                                        text = buildAnnotatedString {
                                            pushStyle(
                                                SpanStyle(
                                                    fontWeight = FontWeight.Bold,
                                                    fontFamily = JadroFontFamily
                                                )
                                            )
                                            pushStyle(SpanStyle(fontSize = 20.sp))
                                            append("${list.nextDays[item].weekDay}\n")
                                            pop()
                                            pushStyle(SpanStyle(fontSize = 12.sp))
                                            append(list.nextDays[item].date)
                                        }
                                    )
                                }
                                if(item != (list.nextDays.size - 1))
                                    HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }


}

val list =
    ForecastLocalModel(
        city = "Kazan",
        lat = 55.75,
        lon = 49.13,
        lastUpdateTime = "08:45",
        currentTempC = 9.0,
        nextDays = listOf(
            NextDay(
                date = "18.05.2024",
                maxTemp = 12.0,
                minTemp = 8.0,
                icon = IconResult.Cold,
                weekDay = "Monday"
            ),
            NextDay(
                date = "19.05.2024",
                maxTemp = 23.0,
                minTemp = 3.0,
                icon = IconResult.Cold,
                weekDay = "Monday"
            ),
            NextDay(
                date = "20.05.2024",
                maxTemp = 34.0,
                minTemp = 13.0,
                icon = IconResult.Cold,
                weekDay = "Tuesday"
            ),
            NextDay(
                date = "21.05.2024",
                maxTemp = 27.0,
                minTemp = 25.0,
                icon = IconResult.Warm,
                weekDay = "Wednesday"
            ),
            NextDay(
                date = "22.05.2024",
                maxTemp = 10.0,
                minTemp = -1.0,
                icon = IconResult.Cold,
                weekDay = "Thursday"
            ),
            NextDay(
                date = "22.05.2024",
                maxTemp = 10.0,
                minTemp = -1.0,
                icon = IconResult.Cold,
                weekDay = "Thursday"
            ),
            NextDay(
                date = "22.05.2024",
                maxTemp = 10.0,
                minTemp = -1.0,
                icon = IconResult.Cold,
                weekDay = "Thursday"
            ),
        )
    )

