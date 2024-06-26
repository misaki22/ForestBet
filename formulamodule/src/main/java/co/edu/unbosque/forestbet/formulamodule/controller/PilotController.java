package co.edu.unbosque.forestbet.formulamodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.forestbet.formulamodule.model.Pilot;
import co.edu.unbosque.forestbet.formulamodule.service.PilotService;
import co.edu.unbosque.forestbet.formulamodule.util.HttpConnectionsAndRequests;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pilots")
@CrossOrigin(origins = "*")
@Transactional
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	public PilotController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/chargePilots")
	public ResponseEntity<String> chargeAllThePilots(){
		String request = "http://ergast.com/api/f1/current/drivers.json";
		String method = "GET";
		
		String response = HttpConnectionsAndRequests.doARequest(request, method);
		
		if(response.isBlank()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problema al hacer el request");
		
		pilotService.chargeAllPilots(response);
		return ResponseEntity.accepted().body("Cargados con exito");
	}
	
	@GetMapping("/showPilots")
	public ResponseEntity<List<Pilot>> showPilots(){
		return ResponseEntity.accepted().body(pilotService.showAllPilots());
	}
}
