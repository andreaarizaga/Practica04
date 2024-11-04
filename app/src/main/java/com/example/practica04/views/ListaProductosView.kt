import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica04.viewmodels.ProductoViewModel
import com.example.practica04.R
import com.example.practica04.model.Producto
import com.example.practica04.navigation.EditarProducto
import com.example.practica04.navigation.FormularioProductos
import com.example.practica04.Dialog.SimpleDialog
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import com.example.practica04.ui.theme.onBackgroundDarkMediumContrast
import com.example.practica04.ui.theme.onTertiaryLight
import com.example.practica04.ui.theme.outlineLightMediumContrast
import com.example.practica04.ui.theme.primaryContainerLight
import com.example.practica04.ui.theme.primaryContainerLightMediumContrast
import com.example.practica04.ui.theme.primaryLight
import com.example.practica04.ui.theme.secondaryContainerDarkMediumContrast
import com.example.practica04.ui.theme.surfaceBrightLight
import com.example.practica04.ui.theme.surfaceContainerLowLight
import com.example.practica04.ui.theme.tertiaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaProductosView(viewModel: ProductoViewModel, navController: NavController, modifier: Modifier = Modifier) {
    var productIdToDelete by remember { mutableStateOf(0) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    val colorTextPrimary = Color(0xFF944E63)
    val colorTextSecondary = Color(0xFFC5705D)
    val colorBackground = Color(0xFFF8EDE3)
    val colorHeader = Color(0xFF944E63)
    val context = LocalContext.current // Obtener el contexto

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
                            text = "Listado de Productos",
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
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(FormularioProductos)
                },
                containerColor = primaryLight,
                contentColor = onTertiaryLight,
            ) {
                Icon(Icons.Filled.Add, "Agregar producto")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(surfaceBrightLight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val estado = viewModel.estado

            if (estado.estaCargando) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (estado.productos.isEmpty()) {
                Box(
                    modifier = Modifier.width(300.dp).height(730.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "NO HAY PRODUCTOS AGREGADOS POR EL MOMENTO",
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        color = primaryContainerLightMediumContrast,
                    )
                }

            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(0.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(estado.productos) { producto ->
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .shadow(5.dp, shape = RoundedCornerShape(15.dp)),
                            colors = CardDefaults.cardColors(
                                containerColor = onBackgroundDarkMediumContrast
                            ),
                            shape = RoundedCornerShape(15.dp),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)
                            ) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "${producto.nombre} ($${producto.precio} pesos)",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = colorTextPrimary
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    text = "Fecha de registro: ${producto.fecha}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = outlineLightMediumContrast
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    text = producto.descripcion,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = outlineLightMediumContrast
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    SmallFloatingActionButton(
                                        onClick = {
                                            navController.navigate(EditarProducto(productId = producto.id))
                                        },
                                        containerColor = primaryLight,
                                        contentColor = onTertiaryLight,
                                        modifier = Modifier.padding(end = 8.dp)
                                    ) {
                                        Icon(Icons.Filled.Settings, "Editar producto")
                                    }

                                    SmallFloatingActionButton(
                                        onClick = {
                                            showDeleteDialog = true
                                            productIdToDelete = producto.id
                                        },
                                        containerColor = primaryLight,
                                        contentColor = onTertiaryLight
                                    ) {
                                        Icon(Icons.Filled.Close, "Eliminar producto")
                                    }
                                }
                            }
                        }
                    }
                }

                // Diálogo de confirmación para eliminar producto
                SimpleDialog(
                    dialogTitle = "Eliminar",
                    dialogText = "¿Desea continuar?",
                    onDismissRequest = {
                        showDeleteDialog = false
                    },
                    onConfirmation = {
                        viewModel.deleteProduct(Producto(productIdToDelete, "", "", 0, ""))
                        showDeleteDialog = false

                        // Mostrar el Toast al eliminar el producto
                        Toast.makeText(context, "Producto eliminado correctamente", Toast.LENGTH_SHORT).show()
                    },
                    show = showDeleteDialog
                )
            }
        }
    }
}
