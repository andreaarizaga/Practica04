package com.example.practica04.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.practica04.R
import com.example.practica04.navigation.ListaProductos
import com.example.practica04.navigation.Presentacion
import com.example.practica04.ui.theme.bodyFontFamily
import com.example.practica04.ui.theme.displayFontFamily
import com.example.practica04.ui.theme.onPrimaryContainerDark
import com.example.practica04.ui.theme.onSurfaceVariantDark
import com.example.practica04.ui.theme.onTertiaryLight
import com.example.practica04.ui.theme.outlineLight
import com.example.practica04.ui.theme.outlineVariantLight
import com.example.practica04.ui.theme.primaryContainerDarkMediumContrast
import com.example.practica04.ui.theme.primaryLight
import com.example.practica04.ui.theme.secondaryContainerDarkMediumContrast
import com.example.practica04.ui.theme.secondaryDark
import com.example.practica04.ui.theme.secondaryDarkMediumContrast
import com.example.practica04.ui.theme.secondaryLight
import com.example.practica04.ui.theme.surfaceBrightLight
import com.example.practica04.ui.theme.surfaceContainerLowLight
import com.example.practica04.ui.theme.surfaceContainerLowLightHighContrast
import com.example.practica04.ui.theme.surfaceDimLight
import com.example.practica04.ui.theme.tertiaryLight

val colorTextSecondary = Color(0xFFC5705D)
val colorBackground = Color(0xFFF8EDE3)
val colorHeader = Color(0xFF944E63)

@Composable
fun inicio(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = surfaceBrightLight // Color de fondo claro
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeaderSection()

            // Imagen de perfil
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.height(80.dp))

                Card(
                    modifier = Modifier
                        .width(300.dp)
                        .height(320.dp)
                        .border(
                            width = 4.dp,
                            color = onPrimaryContainerDark,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = surfaceContainerLowLightHighContrast
                    ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center // Centra el contenido dentro del Box
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(230.dp)
                                .clip(CircleShape)
                                .border(
                                    width = 4.dp,
                                    color = colorTextPrimary,
                                    shape = CircleShape
                                ),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "BonBon Tienda",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryLight,
                        fontFamily = displayFontFamily
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    // Botón para ir a la lista de productos
                    Button(
                        onClick = { navController.navigate(ListaProductos) }, // Navegar a ListaProductos
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryContainerDarkMediumContrast,
                            contentColor = onTertiaryLight
                        ),
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier
                            .width(160.dp)
                            .height(48.dp)
                            .shadow(2.dp, shape = RoundedCornerShape(25.dp), clip = true),

                    ) {
                        Text("Productos")
                    }

                    // Botón para ir a la presentación
                    Button(
                        onClick = { navController.navigate(Presentacion) }, // Navegar a Presentación
                        colors = ButtonDefaults.buttonColors(
                            containerColor = secondaryContainerDarkMediumContrast,
                            contentColor = onTertiaryLight
                        ),
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier
                            .width(160.dp)
                            .height(48.dp)
                            .shadow(2.dp, shape = RoundedCornerShape(25.dp), clip = true),

                    ) {
                        Text("Presentación")
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Empuja el FooterSection hacia el fondo

            FooterSection()
        }
    }
}


@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp) // Ajusta la altura del encabezado aquí
            .background(primaryLight),
        contentAlignment = Alignment.Center
    ) {
       // Text(
       //     textAlign = TextAlign.Center,
       //     text = "INICIO",
      //      fontSize = 28.sp,
       //     fontWeight = FontWeight.Bold,
         //   color = onTertiaryLight,
           // fontFamily = displayFontFamily
       // )
    }
}

@Composable
fun FooterSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
           // .background(outlineLight)
            .padding(15.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Arizaga Hernández Andrea Estefania",
            color = primaryLight,
            fontFamily = bodyFontFamily,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInicio() {
    val navController = rememberNavController() // Crea un NavController simulado
    inicio(navController = navController)
}
