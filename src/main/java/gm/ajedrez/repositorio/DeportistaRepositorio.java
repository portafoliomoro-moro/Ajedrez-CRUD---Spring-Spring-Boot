package gm.ajedrez.repositorio;

import gm.ajedrez.modelo.Deportista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeportistaRepositorio extends JpaRepository<Deportista, Integer> {
}
