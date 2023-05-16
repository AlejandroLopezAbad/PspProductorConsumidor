package models

import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class ColaClientes(private var maxClientes:Int) {

    val cola: MutableList<Cliente> = mutableListOf()

    //condiciones para el lock
    private val lock: ReentrantLock = ReentrantLock()

    //condiciones de que la cola de clientes este vacia
    private val colaEmptyCondition: Condition = lock.newCondition()

    //que la cola este llena
    private val colaFullCondition: Condition = lock.newCondition()


    private var clienteDisponible = false


    fun get(): Cliente =
        lock.withLock {
            while (cola.size == 0) {
                try {
                    println("EMPTY")
                    colaEmptyCondition.await()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            val cliente = cola.removeFirst() // sacamos el primero de la cola
            println("La cola de clientes despues de sacar uno es de : ${cola.size}")
            clienteDisponible = false

            colaFullCondition.signalAll()//Como acabamos de sacar un cliente avisamos de que puede llegar otro cliente a la cola
            return cliente


        }


    fun put(item: Cliente) =

        lock.withLock {

            while (cola.size == maxClientes) {
                try {

                    println("PUT ESPERANDO")
                    colaFullCondition.await()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
                cola.add(item)
                println("La cola de clientes es de : ${cola.size}")
                clienteDisponible = true
                // Ya hay cantidad a consumir, activamos
                colaEmptyCondition.signalAll()
            }




    fun size(): Int {
        lock.withLock {

            return cola.size


        }




    }


}





