package org.example

sealed class CoffeeMachineState {
    data class Idle(val timestamp: Long = System.currentTimeMillis()) : CoffeeMachineState()
    object Choose : CoffeeMachineState()
    data class Adds(val extra: String) : CoffeeMachineState()
    object Paying : CoffeeMachineState()
    object MakingCoffee : CoffeeMachineState()
    data class ServingCoffee(val bebida: Bebida) : CoffeeMachineState() // Usa el Enum Bebida
    data class Error(val message: String) : CoffeeMachineState()
}