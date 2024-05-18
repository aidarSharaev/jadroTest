package ru.aidar.weather_impl.presentation

import WeatherScreen
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
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.aidar.common.base.BaseFragment
import ru.aidar.common.di.FeatureUtils
import ru.aidar.weather_api.di.WeatherFeatureApi
import ru.aidar.weather_impl.databinding.WeatherFragmentBinding
import ru.aidar.weather_impl.di.WeatherFeatureComponent


class WeatherFragment : BaseFragment<WeatherViewModel>(), ActivityResultCallback<Boolean> {

    private lateinit var binding: WeatherFragmentBinding

    override fun onActivityResult(result: Boolean) {
        Log.d("FFFF", "resukt")

        if(result) {
            Log.d("FFFF", "gg")
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
                        permissionResultLauncher.launch(LOCATION_PERMISSION)
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
                Log.d("FFFF", "gffg")
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




