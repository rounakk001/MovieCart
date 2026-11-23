package com.example.ca3sem7

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun MovieScreen(viewModel: MovieViewModel) {

    val movies by viewModel.movies

    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Movie Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val qty = quantity.toIntOrNull()

                if (name.isNotBlank() && qty != null && qty > 0) {
                    viewModel.insertMovie(name.trim(), qty)

                    name = ""
                    quantity = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Movie")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(
                items = movies,
                key = { it.id }
            ) { movie ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = movie.name)
                        Text(text = "Quantity: ${movie.quantity}")
                    }

                    Row {

                        Button(
                            onClick = {
                                viewModel.updateMovie(
                                    name = movie.name,
                                    quantity = movie.quantity + 1
                                )
                            }
                        ) {
                            Text("+")
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        Button(
                            onClick = {
                                val newQuantity = movie.quantity - 1

                                if (newQuantity == 0) {
                                    viewModel.deleteMovie(movie.name)
                                } else {
                                    viewModel.updateMovie(
                                        name = movie.name,
                                        quantity = newQuantity
                                    )
                                }
                            }
                        ) {
                            Text("-")
                        }
                    }
                }

                HorizontalDivider()
            }
        }
    }
}
