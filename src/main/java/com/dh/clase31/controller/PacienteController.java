package com.dh.clase31.controller;

import com.dh.clase31.entity.Odontologo;
import com.dh.clase31.entity.Paciente;
import com.dh.clase31.exception.ResourceNotFoundException;
import com.dh.clase31.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
@CrossOrigin(origins = "*")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @GetMapping("{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);

        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(paciente.getId());
        if (pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizo el paciente"+ paciente.getNombre()+
                    " "+paciente.getApellido()+" con id: "+ paciente.getId());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarPaciente(@PathVariable Long id) throws  ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Se elimino el odontologo con id: " + id);
        }
        throw new ResourceNotFoundException("No se encuentra un paciente con el id= " + id + " verificar el id");
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("")
    public List<Paciente> todosLosPacientes(){
        return pacienteService.buscarTodosLosPacientes();
    }
}



