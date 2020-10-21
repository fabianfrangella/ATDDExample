package validator

import entity.Usuario
import exception.UsuarioException
import repository.UsuarioRepository

class UsuarioValidator() {

    lateinit var repository: UsuarioRepository

    constructor(repository: UsuarioRepository) : this() {
        this.repository = repository
    }
    
    fun validate(usuario: Usuario) {
        if (repository.usuarios.any{it.nickname == usuario.nickname}) {
            throw UsuarioException("El usuario con nickname ${usuario.nickname} ya existe")
        }
        if (usuario.password.length < 8) {
            throw UsuarioException("El password debe tener al menos 8 caracteres")
        }
        if (usuario.nombre.isEmpty()) {
            throw UsuarioException("Nombre es un campo obligatorio")
        }
        if (usuario.apellido.isEmpty()) {
            throw UsuarioException("Apellido es un campo obligatorio")
        }
    }
}