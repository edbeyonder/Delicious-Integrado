package com.Delicious.Deliciousweb.service;

import com.Delicious.Deliciousweb.model.Gasto;
import com.Delicious.Deliciousweb.repository.GastoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class GastoService {

    private final GastoRepository gastoRepository;

    public GastoService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    public Gasto guardarGasto(Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    public List<Gasto> listarProductos() {
        return gastoRepository.findAll();
    }

    public List<Gasto> listarTodos() {
        return gastoRepository.findAll();
    }

   public Gasto guardar(Gasto gasto) {
    if (gasto.getIdGasto() != null) {
        Gasto existente = gastoRepository
                .findById(gasto.getIdGasto())
                .orElse(null);
        if (existente != null) {
            gasto.setFecha(existente.getFecha());
        }
    } else {
        gasto.setFecha(new java.util.Date());
    }
    return gastoRepository.save(gasto);
}

    public Gasto buscarPorId(Integer id) {
        return gastoRepository.findById(id).orElse(null);
    }
}