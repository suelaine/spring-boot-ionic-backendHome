package com.suelaine.cursomc2.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.suelaine.cursomc2.domain.Pedido;
import com.suelaine.cursomc2.services.PedidoService;

import javassist.tools.rmi.ObjectNotFoundException;

//Conversa com o objeto de Serviço capaz de entregar uma categoria
@RestController
@RequestMapping(value="/pedidos")//endpoint
public class PedidoResource {
	
	//instanciando automaticamente
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)//endpoint
	//(@PathVariable serve para entender que o id de cima correesponde ao debaixo
	
	public ResponseEntity<Pedido> find(@PathVariable Integer id) throws ObjectNotFoundException {
		//tipo especial que armazena várias informações de uma resposta htttp para um serviço REST
		Pedido obj =  service.find(id);		
		return ResponseEntity.ok().body(obj);
		//metodo ok diz que a operação ocorreu com sucesso
		//objeto complexo do protocola http
		
	}

}
