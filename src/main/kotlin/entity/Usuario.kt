package entity

class Usuario() {
    lateinit var nombre: String
    lateinit var apellido: String
    lateinit var nickname: String
    lateinit var password: String

    constructor (persona: Persona, nickname: String, password: String) : this() {
        this.nombre = persona.nombre
        this.apellido = persona.apellido
        this.password = password
        this.nickname = nickname
    }
}