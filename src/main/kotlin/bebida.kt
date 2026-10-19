package org.example

enum class Bebida(
    val nombre: String,
    val precio: Double,
    val cantidad: Int // Representa la cantidad en ml (o el stock disponible)
) {
    CAFE_SOLO("Café Solo", 1.00, 100),
    CAFE_CON_LECHE("Café con Leche", 1.20, 150),
    CAPUCHINO("Capuchino", 1.50, 150),
    CHOCOLATE("Chocolate Caliente", 1.30, 200),
    TE("Té Verde", 1.00, 180)
}