package com.anwar.pla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.anwar.pla.ui.theme.PLATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PLATheme {
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }
}

