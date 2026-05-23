package com.example.mobileprogramminglabs.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

//private val LightColorScheme = lightColorScheme(

//)
//private val DarkColorScheme = darkColorScheme(
//)

/*private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)*/

private val LightColorScheme = lightColorScheme(
    primary = DeepTeal,
    onPrimary = Color.White,

    primaryContainer = AliceBlue,
    onPrimaryContainer = DeepTealDark,

    secondary = DustyOlive,
    onSecondary = Color.White,

    secondaryContainer = Thistle,
    onSecondaryContainer = DeepTealDark,

    background = AliceBlueLight,
    onBackground = DeepTealDark,

    surface = AliceBlueLight,
    onSurface = DeepTealDark,

    error = Color(0xFFB3261E),
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF9FD8CB),
    onPrimary = Color(0xFF003D36),

    primaryContainer = DeepTeal,
    onPrimaryContainer = Color.White,

    secondary = Thistle,
    onSecondary = Color(0xFF3A2430),

    secondaryContainer = RosyTaupeBeige,
    onSecondaryContainer = Color.White,

    background = Color(0xFF0B1210),
    onBackground = Color(0xFFEAF3EF),

    surface = Color(0xFF12201D),
    onSurface = Color(0xFFEAF3EF),

    surfaceVariant = Color(0xFF243A35),
    onSurfaceVariant = Color(0xFFC7D8D2),

    outline = Color(0xFF8FA59E),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

/*@Composable
fun MobileProgrammingLabsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}*/

@Composable
fun MobileProgrammingLabsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}