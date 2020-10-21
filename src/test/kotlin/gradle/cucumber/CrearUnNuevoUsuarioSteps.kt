package gradle.cucumber

import entity.Persona
import exception.UsuarioException
import repository.UsuarioRepository
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.Assert
import org.junit.Assert.assertThrows
import org.junit.runner.RunWith
import java.lang.Exception

@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources"])
class CrearUnNuevoUsuarioSteps {

    lateinit var persona: Persona
    private val repository = UsuarioRepository()
    lateinit var error: String
    @Given("Una persona con nombre {string} y apellido {string}")
    fun una_persona_con_nombre_y_apellido(nombre: String, apellido: String){
        persona = Persona(nombre, apellido)
    }

    @When("Completa el formulario de registro con nickname {string} y password {string}")
    fun completa_el_formulario_de_registro_con_nickname_y_paswword(nickname: String, password: String) {
        repository.crearUsuario(persona, nickname, password)
    }

    @Then("Se crea un nuevo usuario con Nickname {string} nombre {string} y apellido {string}")
    fun se_crea_un_nuevo_usuario_con_nickname_nombre_y_apellido(nickname: String, nombre: String, apellido: String) {
        val usuario = repository.findUsuarioByNickname(nickname)
        Assert.assertEquals(nickname ,usuario.nickname)
        Assert.assertEquals(nombre, usuario.nombre)
        Assert.assertEquals(apellido, usuario.apellido)
    }

    @When("Completa el formulario de registro con nickname {string}")
    fun completa_el_formulario_de_registro_con_nickname(nickname: String) {
        repository.crearUsuario(persona, nickname, "1234567801")
        error = assertThrows(UsuarioException::class.java) {
            repository.crearUsuario(Persona("Jorge", "Ibisdev"), nickname, "1234567801")
        }.message!!
    }

    @Then("Se lanza un error con mensaje {string}")
    fun se_lanza_un_error_con_mensaje(mensaje: String) {
        Assert.assertEquals(mensaje, error)
    }

    @When("Completa el formulario de registro con password {}")
    fun completa_el_formulario_de_registro_con_password(password: String) {
        error = assertThrows(UsuarioException::class.java) {
            repository.crearUsuario(persona, "pepe123", password)
        }.message!!
    }

    @Given("Una persona de apellido {string}")
    fun una_persona_de_apellido(apellido: String) {
        persona = Persona("", apellido)
    }

    @When("Completa el formulario de registro sin indicar su apellido")
    fun completa_el_formulario_de_registro_sin_indicar_su_apellido() {
      error = assertThrows(UsuarioException::class.java) {
            repository.crearUsuario(persona, "pepe123", "12345461412")
        }.message!!
    }

    @Given("Una persona de nombre {string}")
    fun una_persona_de_nombre(nombre: String) {
        persona = Persona(nombre, "")
    }

    @When("Completa el formulario de registro sin indicar su nombre")
    fun completa_el_formulario_de_registro_sin_indicar_su_nombre() {
        error = assertThrows(UsuarioException::class.java) {
            repository.crearUsuario(persona, "pepe123", "12345461412")
        }.message!!
    }
}