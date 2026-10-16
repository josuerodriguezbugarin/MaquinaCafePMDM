// 1. Definimos los 6 estados de la máquina usando un enum
enum class Estado {
    IDLE,
    CHOOSE,
    ADDS,
    PAYING,
    MAKING,
    SERVING
}

// 2. Clase para gestionar los estados de la máquina
class GestorEstados {

    // Guardamos el estado actual. Empieza siempre en IDLE (reposo).
    var estadoActual: Estado = Estado.IDLE

    // Función auxiliar para mostrar un error y volver a IDLE
    fun mostrarError(mensaje: String) {
        println("⚠️ ERROR: $mensaje")
        println("🔄 Regresando al estado de reposo (IDLE)...")
        estadoActual = Estado.IDLE
        println("Estado actual: $estadoActual\n")
    }

    // Paso 1: Iniciar interacción (IDLE -> CHOOSE)
    fun iniciarInteraccion() {
        if (estadoActual == Estado.IDLE) {
            estadoActual = Estado.CHOOSE
            println("Estado actual: $estadoActual (Selecciona tu bebida)")
        } else {
            mostrarError("No puedes iniciar desde el estado $estadoActual")
        }
    }

    // Paso 2: Elegir bebida (CHOOSE -> ADDS)
    fun elegirBebida() {
        if (estadoActual == Estado.CHOOSE) {
            estadoActual = Estado.ADDS
            println("Estado actual: $estadoActual (Añade extras o continúa)")
        } else {
            mostrarError("No puedes elegir bebida desde el estado $estadoActual")
        }
    }

    // Paso 3: Confirmar extras (ADDS -> PAYING)
    fun confirmarExtras() {
        if (estadoActual == Estado.ADDS) {
            estadoActual = Estado.PAYING
            println("Estado actual: $estadoActual (Procesando pago)")
        } else {
            mostrarError("No puedes confirmar extras desde el estado $estadoActual")
        }
    }

    // Paso 4: Realizar pago (PAYING -> MAKING)
    fun realizarPago(pagoExitoso: Boolean) {
        if (estadoActual == Estado.PAYING) {
            if (pagoExitoso) {
                estadoActual = Estado.MAKING
                println("Estado actual: $estadoActual (Preparando el café)")
            } else {
                mostrarError("El pago ha sido rechazado")
            }
        } else {
            mostrarError("No puedes pagar desde el estado $estadoActual")
        }
    }

    // Paso 5: Finalizar preparación (MAKING -> SERVING)
    fun terminarPreparacion() {
        if (estadoActual == Estado.MAKING) {
            estadoActual = Estado.SERVING
            println("Estado actual: $estadoActual (Sirviendo bebida)")
        } else {
            mostrarError("Error al preparar la bebida")
        }
    }

    // Paso 6: Entregar bebida (SERVING -> IDLE)
    fun entregarBebida() {
        if (estadoActual == Estado.SERVING) {
            println("☕ ¡Aquí tienes tu café! ¡Que lo disfrutes!")
            estadoActual = Estado.IDLE
            println("Estado actual: $estadoActual\n")
        } else {
            mostrarError("No hay bebida lista para entregar")
        }
    }
}

// 3. Función principal para probar en IntelliJ
fun main() {
    val gestor = GestorEstados()

    println("=== PRUEBA 1: FLUJO COMPLETO CORRECTO ===")
    gestor.iniciarInteraccion()
    gestor.elegirBebida()
    gestor.confirmarExtras()
    gestor.realizarPago(pagoExitoso = true)
    gestor.terminarPreparacion()
    gestor.entregarBebida()

    println("=== PRUEBA 2: ERROR EN EL PAGO ===")
    gestor.iniciarInteraccion()
    gestor.elegirBebida()
    gestor.confirmarExtras()
    gestor.realizarPago(pagoExitoso = false) // Falla el pago, salta mensaje y vuelve a IDLE

    println("=== PRUEBA 3: ACCIÓN EN UN ESTADO INCORRECTO ===")
    gestor.confirmarExtras() // Intenta añadir extras estando en IDLE
}