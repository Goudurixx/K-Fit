package com.example.k_fit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.lifecycle.lifecycleScope
import com.example.k_fit.data.storage.UserStorage
import com.example.k_fit.ui.theme.KFitTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            KFitTheme {
                Surface {
                    Router(this)
                }
            }
        }
    }
}