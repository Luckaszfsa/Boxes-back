	package com.fullstackduck.boxes.resources;
	
	import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.services.UsuarioService;
	
	//Controlador Rest
	@RestController
	@RequestMapping(value = "/usuarios")
	public class UsuarioResource {
	
		@Autowired
		private UsuarioService service;
		@Transactional
		@GetMapping
		public ResponseEntity<List<Usuario>> findAll(){
			List<Usuario> list = service.findAll();
			return ResponseEntity.ok().body(list);
		}
		@Transactional
		@GetMapping(value = "/{id}")
		public ResponseEntity<Usuario> findById(@PathVariable Long id){
			Usuario obj = service.findById(id);
			return ResponseEntity.ok().body(obj);
		}
		@Transactional
		@PostMapping
		public ResponseEntity<Usuario> inserirUsuario(@RequestBody Usuario obj) {
			obj = service.inserirUsuario(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).body(obj);
		}
		@Transactional
		@PutMapping(value = "/{id}/attStatus")
		public ResponseEntity<Usuario> atualizarStatusUsuario(@PathVariable Long id, @RequestBody Usuario obj){
			obj = service.atualizarStatusUsuario(id, obj);
			return ResponseEntity.ok().body(obj);
		}
		
		@Transactional
		@PutMapping(value = "/{id}/attUsuario")
		public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario obj){
			obj = service.atualizarUsuario(id, obj);
			return ResponseEntity.ok().body(obj);
		}
	}
