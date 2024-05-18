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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.aidar.common.R
import ru.aidar.common.compose.JadroPermissionAlertDialog
import ru.aidar.common.utils.Dimens
import ru.aidar.common.utils.Dimens.size34
import ru.aidar.common.utils.Dimens.width4
import ru.aidar.common.utils.JadroAppStyle
import ru.aidar.common.utils.JadroAppStyle.WhiteBlue
import ru.aidar.common.utils.showToast
import ru.aidar.weather_impl.data.utils.forecastPlaceholder
import ru.aidar.weather_impl.data.utils.getImage
import ru.aidar.weather_impl.presentation.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel,
    onRationaleConfirmClick: () -> Unit,
    onSettingsConfirmClick: () -> Unit,
    requestRuntimePermission: () -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val noPermission by remember {
        mutableIntStateOf(ru.aidar.weather_impl.R.string.provide_permission_for_geopositioning)
    }

    if(!state.hasLocationPermission) {
        LaunchedEffect(true) {
            snackbarHostState.showSnackbar(
                message = context.getString(noPermission),
                duration = SnackbarDuration.Indefinite,
            )
        }
    }

    if(state.toastText.isNotBlank()) {
        LaunchedEffect(true) {
            context.showToast(state.toastText)
            viewModel.resetToast()
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { if(!state.hasLocationPermission) requestRuntimePermission() else viewModel.getForecast() },
                containerColor = Color.Blue,
                contentColor = WhiteBlue,
            ) {
                Icon(painterResource(id = R.drawable.ic_update), "")
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {

        if(state.showRationaleAlert) {
            JadroPermissionAlertDialog(
                confirmButtonText = R.string.ok,
                text = ru.aidar.weather_impl.R.string.you_must_provide_access_to_the_geoposition_for_the_application_to_work_correctly,
                onConfirmClick = onRationaleConfirmClick,
                onDismissClick = { viewModel.showRationaleDialog(false) }
            )
        }
        if(state.showSettingAlert) {
            JadroPermissionAlertDialog(
                confirmButtonText = ru.aidar.weather_impl.R.string.settings,
                text = ru.aidar.weather_impl.R.string.provide_permission_for_geopositioning_through_settings,
                onConfirmClick = onSettingsConfirmClick,
                onDismissClick = { viewModel.showSettingDialog(false) }
            )
        }
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(brush = JadroAppStyle.backGroundGradient)
                .padding(horizontal = Dimens.padding25.dp)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.jadroweather),
                    color = WhiteBlue,
                    modifier = Modifier.padding(
                        top = Dimens.padding18.dp,
                        start = Dimens.padding14.dp
                    ),
                    fontSize = Dimens.fontSize28.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = Dimens.letterSpacing2.sp,
                    fontFamily = JadroAppStyle.JadroFontFamily
                )
                if(!state.floatingButtonEnabled) {
                    CircularProgressIndicator(
                        color = WhiteBlue,
                        modifier = Modifier.size(size34.dp),
                        strokeWidth = width4.dp
                    )
                }
            }
            Card(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = Dimens.padding40.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = WhiteBlue)
            ) {
                Text(
                    text = "${state.forecastLocalModel.city}: ${state.forecastLocalModel.lat}, ${state.forecastLocalModel.lon}\n${state.forecastLocalModel.nextDays[0].date}",
                    modifier = Modifier.padding(Dimens.padding7.dp),
                    fontFamily = JadroAppStyle.JadroFontFamily
                )
            }
            Card(
                colors = CardDefaults.elevatedCardColors(
                    containerColor = JadroAppStyle.cardColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.padding5.dp)
                    .height(Dimens.height150.dp),
                border = BorderStroke(width = Dimens.width2.dp, color = WhiteBlue),
                shape = RoundedCornerShape(Dimens.corner35.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_cold),
                        contentDescription = null,
                        modifier = Modifier
                    )
                    Text(
                        text = buildAnnotatedString {
                            pushStyle(Dimens.whiteBlueCommonSpanStyle)
                            pushStyle(SpanStyle(fontSize = Dimens.fontSize52.sp))
                            append("${state.forecastLocalModel.currentTempC}℃\n")
                            pop()
                            pushStyle(SpanStyle(fontSize = Dimens.fontSize15.sp))
                            append("Data as of ${state.forecastLocalModel.lastUpdateTime}")
                        }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = Dimens.padding30.dp)
                    .clip(RoundedCornerShape(Dimens.corner35.dp))
                    .background(WhiteBlue)
            ) {
                Row(
                    modifier = Modifier.padding(
                        top = Dimens.padding28.dp,
                        start = Dimens.padding25.dp,
                        bottom = Dimens.padding10.dp
                    )
                ) {
                    Text(
                        text = stringResource(ru.aidar.weather_impl.R.string.future_weather),
                        fontFamily = JadroAppStyle.JadroFontFamily,
                        fontSize = Dimens.fontSize22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                LazyColumn {
                    items(state.forecastLocalModel.nextDays.size) { item ->
                        if(item != 0) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(Dimens.height105.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Image(
                                    painterResource(id = state.forecastLocalModel.nextDays[item].icon.getImage()),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(Dimens.size70.dp)
                                )
                                Text(
                                    text = buildAnnotatedString {
                                        pushStyle(Dimens.commonSpanStyle)
                                        pushStyle(SpanStyle(fontSize = Dimens.fontSize28.sp))
                                        append("${state.forecastLocalModel.nextDays[item].maxTemp}℃\n")
                                        pop()
                                        pushStyle(SpanStyle(fontSize = Dimens.fontSize18.sp))
                                        append("${state.forecastLocalModel.nextDays[item].minTemp}℃")
                                    }
                                )

                                Text(
                                    text = buildAnnotatedString {
                                        pushStyle(Dimens.commonSpanStyle)
                                        pushStyle(SpanStyle(fontSize = Dimens.fontSize20.sp))
                                        append("${state.forecastLocalModel.nextDays[item].weekDay}\n")
                                        pop()
                                        pushStyle(SpanStyle(fontSize = Dimens.fontSize12.sp))
                                        append(state.forecastLocalModel.nextDays[item].date)
                                    }
                                )
                            }
                            if(item != (forecastPlaceholder.nextDays.size - 1))
                                HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}