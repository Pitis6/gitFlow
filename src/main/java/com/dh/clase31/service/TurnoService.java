package com.dh.clase31.service;

import com.dh.clase31.entity.Paciente;
import com.dh.clase31.entity.Turno;
import com.dh.clase31.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private TurnoRepository turnoRepository;
    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno guardarTurno(Turno turno){
        return turnoRepository.save(turno);
        //se usa el .save tanto para registrar como para actualizar
    }

    public Optional<Turno> buscarTurno(Long id){
        return turnoRepository.findById(id);
    }

    public Turno actualizarTurno(Turno turno){
        return turnoRepository.save(turno);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public List<Turno> buscarTodosLosTurnos(){
        return turnoRepository.findAll();
    }
}
