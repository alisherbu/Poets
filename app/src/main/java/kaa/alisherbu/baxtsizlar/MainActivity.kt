package kaa.alisherbu.baxtsizlar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.content_container, rootFragment())
                .commit()
        }
    }

    private fun rootFragment() = RootFragment(
        storeFactoryInstance,
        DefaultDispatchers
    )
}
