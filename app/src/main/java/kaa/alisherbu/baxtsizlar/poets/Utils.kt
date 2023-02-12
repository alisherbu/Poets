package kaa.alisherbu.baxtsizlar.poets

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


fun Fragment.addFragment(fragment: Fragment, frameId: Int) {
    childFragmentManager.inTransaction { add(frameId, fragment) }
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int) {
    childFragmentManager.inTransaction { replace(frameId, fragment) }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}


