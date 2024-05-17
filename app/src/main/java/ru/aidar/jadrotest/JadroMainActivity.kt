package ru.aidar.jadrotest

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.aidar.common.base.BaseActivity
import ru.aidar.jadrotest.deps.findComponentDependencies
import ru.aidar.jadrotest.main.MainComponent
import ru.aidar.jadrotest.navigation.JadroNavigator
import javax.inject.Inject

class JadroMainActivity : BaseActivity<JadroViewModel>() {

    @Inject
    lateinit var navigator: JadroNavigator

    private var navController: NavController? = null

    override fun inject() {
        MainComponent.init(this, findComponentDependencies()).inject(this)
    }

    override fun layoutResource(): Int {
        return R.layout.jadro_mainactivity
    }

    override fun initViews() {
        navController =
            (supportFragmentManager.findFragmentById(R.id.jadroFragmentContainerView) as NavHostFragment).navController
        val graph =
            navController?.navInflater?.inflate(R.navigation.jadro_nav)
        navigator.attachNavController(navController!!, graph)
    }

    override fun onDestroy() {
        super.onDestroy()
        navController?.let {
            navigator.detachNavController(it)
        }
    }
}
