package models

class CajaConsumidor(
    private var colaClientes: ColaClientes,
    private var id :String,



):Thread() {


    override fun run() {
        println("EMPEZAMOS A CURRAR")
        Thread.sleep(1000L)

        while (true){



         var cliente=  colaClientes.get()

         var timeout= cliente.consumirCliente()
         Thread.sleep(timeout.toLong())

         println("Cajera" + id + ": atiende al cliente" +cliente.id+ "con productos durante "+timeout)



        }

    }



}