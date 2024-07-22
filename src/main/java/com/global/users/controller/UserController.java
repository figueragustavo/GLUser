package com.global.users.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import com.global.users.DTO.UserRequestDTO;
import com.global.users.exception.EmailExistsException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface UserController {

	@ApiOperation(value = "Crear usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario creado"),
            @ApiResponse(code = 301, message = "Email ya existe"),
            @ApiResponse(code = 400, message = "Datos invalidos"),
            @ApiResponse(code = 500, message = "Error generico")
    })
	public ResponseEntity<?> create(@Valid @RequestBody UserRequestDTO userRequestDTO) throws EmailExistsException, Exception;
	
	@ApiOperation(value = "Obtener usuario por token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario obtenido exitosamente"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 404, message = "Usuario no encontrado"),
            @ApiResponse(code = 500, message = "Error generico")
    })
	public ResponseEntity<?> getUserByToken(@RequestAttribute("id") UUID id) throws Exception;
}
