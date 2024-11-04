package com.example.practica04.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica04.R
import com.example.practica04.ui.theme.surfaceBrightLight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.practica04.ui.theme.backgroundLight
import com.example.practica04.ui.theme.displayFontFamily
import com.example.practica04.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.practica04.ui.theme.inversePrimaryLight
import com.example.practica04.ui.theme.inversePrimaryLightMediumContrast
import com.example.practica04.ui.theme.onBackgroundDark
import com.example.practica04.ui.theme.onPrimaryContainerDark
import com.example.practica04.ui.theme.onSecondaryContainerDark
import com.example.practica04.ui.theme.onTertiaryLight
import com.example.practica04.ui.theme.primaryContainerDarkMediumContrast
import com.example.practica04.ui.theme.primaryContainerLight
import com.example.practica04.ui.theme.primaryContainerLightHighContrast
import com.example.practica04.ui.theme.primaryContainerLightMediumContrast
import com.example.practica04.ui.theme.primaryLight
import com.example.practica04.ui.theme.secondaryDark
import com.example.practica04.ui.theme.surfaceContainerHighestLight
import com.example.practica04.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.practica04.ui.theme.surfaceContainerLight
import com.example.practica04.ui.theme.surfaceContainerLightMediumContrast
import com.example.practica04.ui.theme.surfaceContainerLowLight
import com.example.practica04.ui.theme.surfaceDimLight
import com.example.practica04.ui.theme.surfaceDimLightMediumContrast
import com.example.practica04.ui.theme.surfaceLightHighContrast
import com.example.practica04.ui.theme.surfaceVariantLight
import com.example.practica04.ui.theme.tertiaryContainerLight
import com.example.practica04.ui.theme.tertiaryLight

@Composable
fun PresentacionView(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = surfaceBrightLight // Fondo claro
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ListHeader(onBackClick = { navController.popBackStack() }) // Flecha para volver

            Spacer(modifier = Modifier.height(40.dp))

            ProfessionalCard()

            Spacer(modifier = Modifier.weight(1f)) // Empuja el FooterSection hacia el fondo

            FooterSection() // Mueve el FooterSection aquí
        }
    }
}

@Composable
fun ListHeader(onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(95.dp) // Ajusta la altura del encabezado
            .background(primaryLight) // Fondo del encabezado
            .padding(horizontal = 15.dp), // Ajuste de padding horizontal más pequeño
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Asegura que los elementos se distribuyan equitativamente
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
              //  .background(primaryContainerLightMediumContrast, shape = RoundedCornerShape(8.dp))
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Regresar",
                tint = onTertiaryLight
            )
        }

        Spacer(modifier = Modifier.width(16.dp)) // Ajuste del espaciado para centrar mejor

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f), // Permite que el contenido tome el espacio disponible
            contentAlignment = Alignment.Center // Centra el texto dentro del Box
        ) {
            Text(
                text = "Presentación",
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = onTertiaryLight,
                fontFamily = displayFontFamily
            )
        }
    }
}


@Composable
fun ProfessionalCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .border(
                width = 4.dp,
                color = secondaryDark,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp), // Forma redondeada
        elevation = CardDefaults.cardElevation(1.dp) // Elevación de la tarjeta
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFF5E4E7), // Rosa claro pastel
                            Color(0xFFF5E4E7), // Rosa mediano pastel
                            Color(0xFFF5E4E7)  // Rosa fuerte pastel
                        )
                    )
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp) // Espaciado interno
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.mi_foto), // Reemplaza con tu recurso de imagen
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(200.dp) // Tamaño de imagen ajustado
                        .clip(CircleShape)
                        .border(
                            width = 4.dp,
                            color = primaryContainerLightMediumContrast,
                            shape = CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(30.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Andrea Estefania",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryLight,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Arizaga Hernández",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryLight,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Frontend Developer",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = primaryContainerLightHighContrast,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(45.dp)) // Ajusta el espacio antes de la información de contacto

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    ContactInfo(icon = R.drawable.ic_phone, info = "+52 662 114 6520", iconColor = Color(0xFF8C4A5E))
                    ContactInfo(icon = R.drawable.ic_linkedin, info = "@andreearizaga", iconColor = Color(0xFF8C4A5E))
                    ContactInfo(icon = R.drawable.ic_email, info = "a220210658@unison.mx", iconColor = Color(0xFF8C4A5E))
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun ContactInfo(icon: Int, info: String, iconColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center, // Cambia a Center para alinear horizontalmente
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = iconColor, // Usar iconColor como especificaste en la función
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = info,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = primaryContainerDarkMediumContrast, // Ajusta el color aquí si es necesario
            textAlign = TextAlign.Center // Centrar el texto dentro de su propio espacio
        )
    }
}



@Preview(showBackground = true)
@Composable
fun PresentacionViewPreview() {
    val navController = rememberNavController() // Controlador de navegación de prueba
    PresentacionView(navController = navController)
}
