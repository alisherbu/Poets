package kaa.alisherbu.baxtsizlar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kaa.alisherbu.baxtsizlar.navigation.NavigationModule

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigator: Navigator = AppNavigator(this, R.id.content_container)
    private val navigatorHolder = NavigationModule.navigatorHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(rootScreen())))
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()

    }

    private fun rootFragment() = RootFragment(
        storeFactoryInstance,
        DefaultDispatchers
    )

    private fun rootScreen() = FragmentScreen {
        rootFragment()
    }
}
