package ru.aidar.weather_impl.presentation

import WeatherScreen
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.aidar.common.base.BaseFragment
import ru.aidar.common.compose.JadroPermissionAlertDialog
import ru.aidar.common.di.FeatureUtils
import ru.aidar.common.utils.Dimens.commonSpanStyle
import ru.aidar.common.utils.Dimens.corner35
import ru.aidar.common.utils.Dimens.fontSize12
import ru.aidar.common.utils.Dimens.fontSize15
import ru.aidar.common.utils.Dimens.fontSize18
import ru.aidar.common.utils.Dimens.fontSize20
import ru.aidar.common.utils.Dimens.fontSize22
import ru.aidar.common.utils.Dimens.fontSize28
import ru.aidar.common.utils.Dimens.fontSize52
import ru.aidar.common.utils.Dimens.height105
import ru.aidar.common.utils.Dimens.height150
import ru.aidar.common.utils.Dimens.letterSpacing2
import ru.aidar.common.utils.Dimens.padding10
import ru.aidar.common.utils.Dimens.padding14
import ru.aidar.common.utils.Dimens.padding18
import ru.aidar.common.utils.Dimens.padding25
import ru.aidar.common.utils.Dimens.padding28
import ru.aidar.common.utils.Dimens.padding30
import ru.aidar.common.utils.Dimens.padding40
import ru.aidar.common.utils.Dimens.padding5
import ru.aidar.common.utils.Dimens.padding7
import ru.aidar.common.utils.Dimens.size70
import ru.aidar.common.utils.Dimens.whiteBlueCommonSpanStyle
import ru.aidar.common.utils.Dimens.width2
import ru.aidar.common.utils.JadroAppStyle.JadroFontFamily
import ru.aidar.common.utils.JadroAppStyle.WhiteBlue
import ru.aidar.common.utils.JadroAppStyle.backGroundGradient
import ru.aidar.common.utils.JadroAppStyle.cardColor
import ru.aidar.common.utils.showToast
import ru.aidar.weather_api.di.WeatherFeatureApi
import ru.aidar.weather_impl.R
import ru.aidar.weather_impl.data.utils.forecastPlaceholder
import ru.aidar.weather_impl.data.utils.getImage
import ru.aidar.weather_impl.databinding.WeatherFragmentBinding
import ru.aidar.weather_impl.di.WeatherFeatureComponent


class WeatherFragment : BaseFragment<WeatherViewModel>(), ActivityResultCallback<Boolean> {

    private lateinit var binding: WeatherFragmentBinding

    override fun onActivityResult(result: Boolean) {
        if(result) {
            viewModel.updateHasLocationPermission(true)
        } else if(
            !ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                LOCATION_PERMISSION
            )
        ) {
            viewModel.showSettingDialog(true)
        } else {
            viewModel.showRationaleDialog(true)
        }
    }

    companion object {
        private const val LOCATION_PERMISSION = Manifest.permission.ACCESS_COARSE_LOCATION
        private const val PERMISSION_REQ_CODE = 100
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
                requestRuntimePermission()
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                WeatherScreen(
                    viewModel = viewModel,
                    onRationaleConfirmClick = {
                        ActivityCompat.requestPermissions(
                            requireActivity(), arrayOf(
                                LOCATION_PERMISSION
                            ), PERMISSION_REQ_CODE
                        )
                        viewModel.showRationaleDialog(false)
                    },
                    onSettingsConfirmClick = {
                        val intent =
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts(
                                    "package",
                                    requireActivity().packageName,
                                    null
                                )
                            }
                        startActivity(intent)
                        viewModel.showSettingDialog(false)
                    },
                    requestRuntimePermission = { requestRuntimePermission() }
                )
            }
        }
        return view
    }


    private val permissionResultLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted: Boolean ->
        onActivityResult(granted)
    }


    private fun requestRuntimePermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                LOCATION_PERMISSION,
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.updateHasLocationPermission(true)
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                LOCATION_PERMISSION,
            ) -> {
                viewModel.showRationaleDialog(true)
            }

            else -> {
                permissionResultLauncher.launch(LOCATION_PERMISSION)
            }
        }
    }
}




