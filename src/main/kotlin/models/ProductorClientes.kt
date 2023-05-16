package models

class ProductorClientes(
    private var cola:ColaClientes,
    private var id:String,
    private var limiteClientes:Int
):Thread(){

    override fun run() {

         var i :Int = 0
        while (i<limiteClientes){
            i++

            val cliente=Cliente(id+i)
            println("Productor con id : "+ id +i + "-> Produzco Cliente::" + cliente.id)

            cola.put(cliente)


        }


    }




}