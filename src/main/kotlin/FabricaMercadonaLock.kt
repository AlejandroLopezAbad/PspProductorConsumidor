import models.CajaConsumidor
import models.ColaClientes
import models.ProductorClientes
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


object FabricaMercadonaLock {

    val MAXCLIENTES= 200
    val LIMITECLIENTESPRODUCTOR=1000
    val IDPRODUCTOR="Teco"
    val IDPRODUCTOR2="ADRIANA"
    val IDCONSUMIDOR="Xitto"
    val IDCONSUMIDOR2="PACO"
    val IDCONSUMIDOR3="NIKO"


    fun runFabricaLock(){


        println("----Lanzando el protocolo Mercadona----")
        val cola=ColaClientes(MAXCLIENTES)

        val prod=ProductorClientes(cola, IDPRODUCTOR,LIMITECLIENTESPRODUCTOR)

        val prod2=ProductorClientes(cola, IDPRODUCTOR2,LIMITECLIENTESPRODUCTOR)

        val consu=CajaConsumidor(cola, IDCONSUMIDOR)
        val consu2=CajaConsumidor(cola, IDCONSUMIDOR2)
        val consu3=CajaConsumidor(cola, IDCONSUMIDOR3)

        val pool= Executors.newFixedThreadPool(6)
        pool.execute(prod)
        pool.execute(prod2)

        pool.execute(consu)
        pool.execute(consu2)
        pool.execute(consu3)

        pool.awaitTermination(10L, TimeUnit.SECONDS)

        pool.shutdown()







    }







}