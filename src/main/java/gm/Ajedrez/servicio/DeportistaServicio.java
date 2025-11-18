package gm.Ajedrez.servicio;

import gm.Ajedrez.modelo.Deportista;
import gm.Ajedrez.repositorio.DeportistaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeportistaServicio implements IDeportistaServicio{

    @Autowired
    private DeportistaRepositorio deportistaRepositorio;

    @Override
    public List<Deportista> listarDeportista() {
        List<Deportista> deportistas = deportistaRepositorio.findAll();
        return deportistas;
    }

    @Override
    public Deportista buscarDeportistaPorID(Integer idDeportista) {
        Deportista deportista = deportistaRepositorio.findById(idDeportista).orElse(null);
        return deportista;
    }

    @Override
    public void guardarDeportista(Deportista deportista) {
        deportistaRepositorio.save(deportista);
    }

    @Override
    public void eliminarDeportista(Deportista deportista) {
        deportistaRepositorio.delete(deportista);
    }
}
