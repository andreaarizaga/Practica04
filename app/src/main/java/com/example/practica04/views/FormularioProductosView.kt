package com.example.practica04.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.practica04.Dialog.SimpleDialog
import com.example.practica04.model.Producto
import com.example.practica04.navigation.ListaProductos
import com.example.practica04.viewmodels.ProductoViewModel
import com.example.practica04.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.widget.Toast // Importar Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.practica04.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.practica04.ui.theme.inversePrimaryDark
import com.example.practica04.ui.theme.onPrimaryLight
import com.example.practica04.ui.theme.onSurfaceVariantDark
import com.example.practica04.ui.theme.onTertiaryLight
import com.example.practica04.ui.theme.outlineDarkMediumContrast
import com.example.practica04.ui.theme.outlineVariantDarkMediumContrast
import com.example.practica04.ui.theme.primaryContainerLightMediumContrast
import com.example.practica04.ui.theme.primaryLight
import com.example.practica04.ui.theme.primaryLightMediumContrast
import com.example.practica04.ui.theme.secondaryDark
import com.example.practica04.ui.theme.surfaceBrightLight
import com.example.practica04.ui.theme.surfaceBrightLightHighContrast
import com.example.practica04.ui.theme.surfaceBrightLightMediumContrast
import com.example.practica04.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.practica04.ui.theme.surfaceContainerLowLight
import com.example.practica04.ui.theme.surfaceContainerLowLightHighContrast
import com.example.practica04.ui.theme.surfaceContainerLowLightMediumContrast


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioProductosView(navController: NavController, viewModel: ProductoViewModel, modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .height(95.dp), // Ajusta la altura del encabezado aquí
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryLight,
                    titleContentColor = colorTextPrimary
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxHeight(), // Rellenar toda la altura del TopAppBar
                        contentAlignment = Alignment.Center // Centrar el contenido verticalmente
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = "Agregar Producto",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = onTertiaryLight
                        )
                        }
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(56.dp)
                            //.background(primaryContainerLightMediumContrast, shape = RoundedCornerShape(8.dp))
                            .clickable { navController.popBackStack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = onTertiaryLight
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(surfaceBrightLight)
        ) {
            Formulario(viewModel, navController)
        }
    }
}
val colorTextPrimary = Color(0xFF944E63)

@Composable
fun CampoTexto(label: String, value: String, onValueChange: (String) -> Unit, keyboardOptions: KeyboardOptions = KeyboardOptions.Default, textArea: Boolean = false, icono: Int, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, fontWeight = FontWeight.SemiBold) },
        keyboardOptions = keyboardOptions,
        modifier = if (textArea) Modifier.height(200.dp).fillMaxWidth() else Modifier.height(64.dp).width(350.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = primaryLightMediumContrast,
            unfocusedBorderColor = primaryContainerLightMediumContrast,
            focusedLabelColor = primaryLightMediumContrast,
            unfocusedLabelColor = primaryContainerLightMediumContrast,
            unfocusedContainerColor = surfaceContainerLowLightHighContrast,
            focusedContainerColor = inverseOnSurfaceLightMediumContrast
        ),
        textStyle = TextStyle(Color.Black),
        shape = RoundedCornerShape(12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorFecha(datePickerState: DatePickerState, selectedDate: String) {
    var showDatePicker by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = selectedDate,
        onValueChange = { },
        label = {
            Text(
                text = "Fecha de registro",
                fontWeight = FontWeight.SemiBold
            )
        },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { showDatePicker = !showDatePicker }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar), // Reemplaza con tu recurso de icono de calendario
                    contentDescription = "Seleccionar fecha",
                    tint = primaryLight
                )
            }
        },
        modifier = Modifier
            .height(64.dp) // Asegúrate de que la altura coincida
            .width(350.dp), // Mantiene el mismo ancho
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = primaryLightMediumContrast,
            unfocusedBorderColor = primaryContainerLightMediumContrast,
            focusedLabelColor = primaryLightMediumContrast,
            unfocusedLabelColor = primaryContainerLightMediumContrast,
            unfocusedContainerColor = surfaceContainerLowLightHighContrast,
            focusedContainerColor = inverseOnSurfaceLightMediumContrast
        ),
        textStyle = TextStyle(Color.Black),
        shape = RoundedCornerShape(12.dp)
    )

    if (showDatePicker) {
        Popup(
            onDismissRequest = { showDatePicker = false },
            alignment = Alignment.TopStart
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false,
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp)) // Espacio entre campos, ajusta según necesites


    Button(
        onClick = { showDatePicker = !showDatePicker },
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryLight,
            contentColor = onPrimaryLight,
            disabledContentColor = Color.LightGray,
            disabledContainerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(30.dp), // Cambia este valor para ajustar la redondez (4.dp menos redondeado)
        modifier = Modifier.size(width = 170.dp, height = 50.dp) // Cambia el tamaño aquí
    ) {
        Text(text = "Insertar Fecha")
    }



}
fun convertirMillisAFecha(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}


// Otras importaciones...

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario(viewModel: ProductoViewModel, navController: NavController, modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertirMillisAFecha(it)
    } ?: ""

    var errorMsg by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        CampoTexto(label = "Nombre", value = name, icono = R.color.pastel, onValueChange = { name = it })
        CampoTexto(label = "Precio", value = price, icono = R.color.pastel, onValueChange = { price = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        CampoTexto(label = "Descripcion", value = description, icono = R.color.pastel, onValueChange = { description = it })
        SelectorFecha(datePickerState, selectedDate)
    }

    val colorRosaPalito = Color(0xFFB47B84) // Color rosa palito

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Espaciado de 16dp entre los botones
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp) // Espaciado horizontal alrededor del Row
    ) {
        Button(
            onClick = {
                try {
                    if (name.isBlank() || description.isBlank() || selectedDate.isBlank()) {
                        errorMsg = "Favor de llenar los campos"
                        showErrorDialog = true
                    } else if (price.toIntOrNull() == null) {
                        errorMsg = "Favor de ingresar un precio válido"
                        showErrorDialog = true
                    } else {
                        viewModel.addProduct(Producto(nombre = name, descripcion = description, precio = price.toInt(), fecha = selectedDate))
                        Toast.makeText(navController.context, "Producto agregado correctamente", Toast.LENGTH_SHORT).show() // Agregar el Toast
                        navController.navigate(ListaProductos)
                    }
                } catch (e: Exception) {
                    errorMsg = "Error"
                }

            },
            modifier = modifier.size(170.dp, 50.dp), // Tamaño del botón
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryContainerLightMediumContrast, // Se usa el colorTextPrimary
                contentColor = onPrimaryLight // Color del texto del botón
            ),
            shape = RoundedCornerShape(5.dp) // Bordes cuadrados
        ) {
            Text(text = "Registrar")
        }

        Button(
            onClick = { navController.navigate(ListaProductos) },
            colors = ButtonDefaults.buttonColors(
                containerColor = outlineVariantDarkMediumContrast,
                contentColor = onPrimaryLight,
                disabledContentColor = outlineDarkMediumContrast,
                disabledContainerColor = outlineDarkMediumContrast
            ),
            modifier = Modifier.size(width = 150.dp, height = 50.dp), // Tamaño del botón "Cancelar"
            shape = RoundedCornerShape(5.dp) // Bordes cuadrados
        ) {
            Text(text = "Cancelar")
        }

    }

    Spacer(modifier = Modifier.width(16.dp))
    SimpleDialog(
        dialogTitle = "Error",
        dialogText = errorMsg,
        onDismissRequest = {
            showErrorDialog = false
        },
        onConfirmation = {
            showErrorDialog = false
        },
        show = showErrorDialog
    )
}




