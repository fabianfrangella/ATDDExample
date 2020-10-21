package repository

import entity.Persona
import entity.Usuario
import validator.UsuarioValidator

class UsuarioRepository {
    val usuarios: MutableList<Usuario> = mutableListOf()
    var validator: UsuarioValidator

    init {
        this.validator = UsuarioValidator(this)
    }

    fun crearUsuario(persona: Persona, nickname: String, password: String) {
        validator.validate(Usuario(persona, nickname, password))
        usuarios.add(Usuario(persona, nickname, password))
    }

    fun findUsuarioByNickname(nickname: String): Usuario = usuarios.find { it.nickname == nickname }!!
}