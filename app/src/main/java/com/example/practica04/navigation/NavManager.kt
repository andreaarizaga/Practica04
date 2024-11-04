package com.example.practica04.navigation

import ListaProductosView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.practica04.views.FormularioProductosView
import com.example.practica04.views.inicio
import com.example.practica04.views.PresentacionView
import com.example.practica04.viewmodels.ProductoViewModel
import com.example.practica04.views.EditarProductoView

@Composable
fun NavManager(viewModel: ProductoViewModel, modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            inicio(navController)
        }
        composable<ListaProductos> {
            ListaProductosView(viewModel, navController)
        }
        composable<FormularioProductos> {
            FormularioProductosView(navController, viewModel)
        }
        composable<EditarProducto> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<EditarProducto>()
            EditarProductoView(args.productId, navController, viewModel)
        }
        composable<Presentacion> {
            PresentacionView(navController)
        }
    }
}