package com.cubrelechos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubrelechos.entities.User;
import com.cubrelechos.service.user.ServiceUser;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/user")
public class ControllerUser {
	
	@Autowired
	private ServiceUser serviceuser;
	
	
	/////////////////// USER REGISTRAR http://localhost:8080/user/save
	/////////////////// ////////////////
	@PostMapping("/save")
	public ResponseEntity<?> createuser(@RequestBody User user) {
	
		User newuser = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
		
		
			newuser = serviceuser.save(user);
		
		
		} catch (DataAccessException e) {
			
			response.put("Mensaje", "Error al hacer insert en la base de datos");
			response.put("Error", e.getMessage().concat(": ")
					.concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			response.put("menssaje", "El user ha sido creado con exito");
			response.put("Barberia: ", newuser);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	/////////////////// CONSULT USER
	/////////////////// http://localhost:8080/user/consult/id ////////////////
	@GetMapping("/consult/{id}")
	public ResponseEntity<?> consultuserId(@PathVariable(value = "id") Long id) {
	
		Optional<User> user = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
		
			user = serviceuser.findById(id);
		
		} catch (DataAccessException e) {
		
			response.put("Mensaje", "Error al hacer consulta en la base de datos");
			response.put("Error", e.getMessage().concat(": ")
					.concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (!user.isPresent()) {
		
		response.put("Mensaje",
				"La barberia con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(user);
	}
	
		
	/////////////////// UPDATE USER   http://localhost:8080/user/update/ID ////////////////
		
	@PutMapping("/update/{id}")
	public ResponseEntity<?>update(@RequestBody User newUser,@PathVariable (value = "id")Long idUser ){
	
		Optional<User> currentUser = serviceuser.findById(idUser);
		Optional<User> UserUpdate = null;
	
		Map<String, Object> response = new HashMap<>();
	
	if(!currentUser.isPresent()) {
		response.put("Mensaje", "No se pudo editar el barbero con el ID ".concat(idUser.toString().concat(" no existe en la base de datos")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	try {
	
		
		currentUser.get().setName(newUser.getName());
		currentUser.get().setPhone(newUser.getPhone());
		currentUser.get().setProduct(newUser.getProduct());
		currentUser.get().setPrice(newUser.getPrice());
		currentUser.get().setShare(newUser.getShare());
		currentUser.get().setFertilizer(newUser.getFertilizer());
		currentUser.get().setFertilizer_worth(newUser.getFertilizer_worth());
		
		
		UserUpdate = Optional.ofNullable(serviceuser.save(currentUser.get()));
	
	} catch (DataAccessException e) {
	
		response.put("Mensaje", "Error al actualizar el barbero en la base de datos");
		response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
		response.put("Mensaje", "El barbero ha sido actualizado");
		response.put("Barbero: ", UserUpdate);
	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	/////////////////// CONSULT ALL USER   http://localhost:8080/user/consultall ///////////////	
	@GetMapping("/consultall")
	public  List<User> consultAllUsers(){
		
		
		List<User> users = StreamSupport
				.stream(serviceuser.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return  users;
	}
	
	/////////////////// DELETE USER   http://localhost:8080/user/delete/ID ////////////////
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id")  Long id){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			serviceuser.deletById(id);
			
		} catch (DataAccessException e) {

			response.put("Mensaje", "Error al eliminar el barbero en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El barbero se ha eliminado con exito! ");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	

	

}
