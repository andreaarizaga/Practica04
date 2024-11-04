package com.example.practica04.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practica04.Dialog.SimpleDialog
import com.example.practica04.model.Producto
import com.example.practica04.navigation.ListaProductos
import com.example.practica04.viewmodels.ProductoViewModel
import com.example.practica04.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarProductoView(productId: Int, navController: NavController, viewModel: ProductoViewModel, modifier: Modifier = Modifier) {
    val colorTextPrimary = Color(0xFF944E63)
    val colorTextSecondary = Color(0xFFC5705D)
    val colorBackground = Color(0xFFF8EDE3)
    val colorHeader = Color(0xFF944E63)
    val colorTextPrimary1 = Color(0xFFF8EDE3)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorBackground,
                    titleContentColor = colorTextPrimary1
                ),
                title = {
                    Text(text = "Editar producto", style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = colorTextPrimary))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {

                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = colorBackground)
        ) {
            FormularioEditar(producto = viewModel.getProductById(productId), viewModel, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioEditar(producto: Producto?, viewModel: ProductoViewModel, navController: NavController, modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf(producto?.nombre ?: "") }
    var price by remember { mutableStateOf(producto?.precio.toString() ?: "") }
    var description by remember { mutableStateOf(producto?.descripcion ?: "") }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertirMillisAFecha(it)
    } ?: ""

    var errorMsg by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CampoTexto(label = "Nombre", value = name, icono = R.color.pastel, onValueChange = { name = it })
        CampoTexto(label = "Precio", value = price, icono = R.color.pastel, onValueChange = { price = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        CampoTexto(label = "Descripcion", value = description, icono = R.color.pastel, onValueChange = { description = it })
        SelectorFecha(datePickerState, selectedDate)
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
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
                        viewModel.updateProduct(Producto(id = producto?.id!!, nombre = name, descripcion = description, precio = price.toInt(), fecha = selectedDate))
                        navController.navigate(ListaProductos)
                    }
                } catch (e: Exception) {
                    errorMsg = "Error"
                }
            },
            modifier = modifier.size(200.dp, 50.dp), // Tamaño del botón cuadrado
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD6A197),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(0.dp) // Bordes cuadrados
        ) {
            Text(text = "Actualizar")
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = modifier.size(200.dp, 50.dp), // Tamaño del botón cuadrado
            colors = ButtonDefaults.buttonColors(
                containerColor = colorTextSecondary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(0.dp) // Bordes cuadrados
        ) {
            Text(text = "Cancelar")
        }
    }

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
