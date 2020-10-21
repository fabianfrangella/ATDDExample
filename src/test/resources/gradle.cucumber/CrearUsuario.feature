Feature: Registro de usuario

  ## User Story
  ## Como persona
  ## Quiero registrarme
  ## Para poder acceder a la aplicacion

  ## Criterios de aceptación
  ## El nickname debe ser único
  ## La contraseña debe tener al menos 8 caracteres
  ## El campo nombre debe estar completo
  ## El campo apellido debe estar completo

  Scenario: Una persona crea un nuevo usuario
    Given Una persona con nombre "Fabian" y apellido "Frangella"
    When Completa el formulario de registro con nickname "Fabicaster" y password "SuperPassword1234"
    Then Se crea un nuevo usuario con Nickname "Fabicaster" nombre "Fabian" y apellido "Frangella"

  Scenario: Una persona intenta crear un usuario con un nickname ya existente
    Given Una persona con nombre "Fabian" y apellido "Perez"
    When Completa el formulario de registro con nickname "Fabicaster"
    Then Se lanza un error con mensaje "El usuario con nickname Fabicaster ya existe"

  Scenario: Una persona intenta crear un usuario con una password invalida
    Given Una persona con nombre "Jose" y apellido "Perez"
    When Completa el formulario de registro con password "123"
    Then Se lanza un error con mensaje "El password debe tener al menos 8 caracteres"

  Scenario: Una persona intenta crear un usuario sin indicar su nombre
    Given Una persona de apellido "Diaz"
    When Completa el formulario de registro sin indicar su nombre
    Then Se lanza un error con mensaje "Nombre es un campo obligatorio"

  Scenario: Una persona intenta crear un usuario sin indicar su apellido
    Given Una persona de nombre "Leonardo"
    When Completa el formulario de registro sin indicar su apellido
    Then Se lanza un error con mensaje "Apellido es un campo obligatorio"