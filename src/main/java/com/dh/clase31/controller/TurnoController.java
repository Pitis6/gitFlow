package com.dh.clase31.controller;

import com.dh.clase31.entity.Odontologo;
import com.dh.clase31.entity.Paciente;
import com.dh.clase31.entity.Turno;
import com.dh.clase31.service.OdontologoService;
import com.dh.clase31.service.PacienteService;
import com.dh.clase31.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turno")
@CrossOrigin(origins = "*")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turno.getPaciente().getId());
        Optional<Odontologo> odontoBuscado = odontologoService.buscarOdontologo(turno.getOdontologo().getId());
        if(pacienteBuscado.isPresent() && odontoBuscado.isPresent()){
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontoBuscado.get());
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return new ResponseEntity("No se encontro odontologo o paciente con los id ingresados",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id){
        Optional<Turno> pacienteBuscado = turnoService.buscarTurno(id);

        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        Optional<Turno> pacienteBuscado=turnoService.buscarTurno(turno.getId());
        if (pacienteBuscado.isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Se actualizo el turno con id: "+ turno.getId());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Long id){
        Optional<Turno> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se elimino el turno con id: "+id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("")
    public List<Turno> todosLosTurnos(){
        return turnoService.buscarTodosLosTurnos();
    }
}
