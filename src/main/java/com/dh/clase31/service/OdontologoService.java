package com.dh.clase31.service;

import com.dh.clase31.entity.Odontologo;
import com.dh.clase31.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
        //se usa el .save tanto para registrar como para actualizar
    }

    public Optional<Odontologo> buscarOdontologo(Integer id){
        return odontologoRepository.findById(id);
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public void eliminarOdontologo(Integer id){
        odontologoRepository.deleteById(id);
    }

    public List<Odontologo> listarOdontologos(){
        return odontologoRepository.findAll() ;
    }
    /*
    alternativa 2- no la recomiendo
    public Movimiento buscarMovimiento(Integer id){
        return movimientoRepository.findById(id).get();
    }
     */

}
