package kaa.alisherbu.baxtsizlar

import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import kaa.alisherbu.baxtsizlar.navigation.BackButtonListener
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigator: Navigator = AppNavigator(this, R.id.content_container)
    private val navigatorHolder: NavigatorHolder by inject(named("outside"))

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screen.Root())))
        }
        onBackPressedDispatcher.addCallback(this) {
            val fragment = supportFragmentManager.findFragmentById(R.id.content_container)
            val k = (fragment as? BackButtonListener)?.onBackPressed() == false
            Log.d("NavLog", "onBackPressed, MainActivity, ${fragment?.javaClass?.name}, k=$k")
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
}
