package gm.Ajedrez.servicio;

import gm.Ajedrez.modelo.Deportista;

import java.util.List;

public interface IDeportistaServicio {
    public List<Deportista> listarDeportista();

    public Deportista buscarDeportistaPorID(Integer idDeportista);

    public void guardarDeportista(Deportista deportista);

    public void eliminarDeportista(Deportista deportista);
}
