package entity

class Persona() {
    lateinit var nombre: String
    lateinit var apellido: String

    constructor(nombre: String, apellido: String) : this() {
        this.nombre = nombre
        this.apellido = apellido
    }
}