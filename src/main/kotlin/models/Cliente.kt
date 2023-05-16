package models

class Cliente(

    var id : String,

) {


//LE CREAMOS EL METODO AL CLIENTE DE COMO SE CONSUME
    fun consumirCliente():Int{



        return (1..1000).random()


    }

    override fun toString(): String {
        return "Cliente(id='$id')"
    }


}


