package io.bibuti.samplemvi.android

import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.bibuti.core.components.ThreadInfo
import io.bibuti.samplemvi.news.NewsViewModel

class ViewModelFactory(private val threadInfo: ThreadInfo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(threadInfo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

object ThreadInfoImpl : ThreadInfo {
    override fun isMainThread(): Boolean {
        return Looper.getMainLooper() == Looper.myLooper()
    }

}