package org.example

import org.example.CoffeeMachineState.*

object CoffeeMachine {
    var currentState: CoffeeMachineState = Idle()

    fun gestorEstados(
        monedero: Int = 0,
        bebida: Bebida = Bebida.CAFE_SOLO, // Se usa el Enum Bebida en lugar de String
        extras: String = "Sin extras",
        pagoExitoso: Boolean = true
    ): CoffeeMachineState {

        println("\n--> [Estado actual: ${currentState::class.simpleName}]")

        when (val state = currentState) {

            // 1. Reposo
            is Idle -> {
                if (monedero > 0) {
                    println("1. IDLE: Dinero recibido ($monedero€). Pasando a elegir bebida...")
                    Thread.sleep(1000)
                    currentState = Choose
                } else {
                    println("1. IDLE: La máquina está en reposo. Por favor, introduce dinero.")
                }
            }

            // 2. Selección
            is Choose -> {
                println("2. CHOOSE: Has seleccionado '${bebida.nombre}' (Precio: ${bebida.precio}€, Cantidad: ${bebida.cantidad}ml)")
                Thread.sleep(1000)
                currentState = Adds(extras) // Usa la variable extras pasada por parámetro
            }

            // 3. Añadidos / Sin añadidos
            is Adds -> {
                println("3. ADDS: Añadidos elegidos: '${state.extra}'. Pasando al pago...")
                Thread.sleep(1000)
                currentState = Paying
            }

            // 4. Pago
            is Paying -> {
                if (pagoExitoso) {
                    println("4. PAYING: Pago correcto. Empezando preparación...")
                    Thread.sleep(1000)
                    currentState = MakingCoffee
                } else {
                    lanzarError("Pago rechazado. Fondos insuficientes o fallo de tarjeta.")
                }
            }

            // 5. Preparación
            is MakingCoffee -> {
                println("5. MAKING: Preparando tu bebida... ¡Por favor, espera!")
                Thread.sleep(2000)
                currentState = ServingCoffee(bebida) // Pasa el objeto Bebida correcto
            }

            // 6. Servido
            is ServingCoffee -> {
                println("6. SERVING: Servido '${state.bebida.nombre}'. ¡Toma tu café y disfruta!") // Acceso correcto a state.bebida.nombre
                Thread.sleep(1000)
                currentState = Idle() // Regresa a reposo tras entregar la bebida
            }

            // Manejo de Error: Muestra mensaje y vuelve automáticamente a IDLE
            is Error -> {
                println("⚠️ Error: ${state.message}")
                println("🔄 Volviendo al estado de reposo...")
                Thread.sleep(1000)
                currentState = Idle()
            }
        }

        return currentState
    }

    private fun lanzarError(mensaje: String) {
        println("❌ Ha ocurrido un error...")
        currentState = Error(mensaje)
    }

    fun clean() {
        println("\nLimpiando la máquina...")
        currentState = Idle()
        println("Máquina limpia. Estado: $currentState")
    }
}