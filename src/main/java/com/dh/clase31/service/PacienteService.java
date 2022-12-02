package com.dh.clase31.service;

import com.dh.clase31.entity.Odontologo;
import com.dh.clase31.entity.Paciente;
import com.dh.clase31.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PacienteService {

    private PacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
        //se usa el .save tanto para registrar como para actualizar
    }

    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRepository.findById(id);
    }

    public Paciente actualizarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }

    public List<Paciente> buscarTodosLosPacientes(){
        return pacienteRepository.findAll();
    }


}
