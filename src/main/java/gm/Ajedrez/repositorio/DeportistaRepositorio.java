package gm.Ajedrez.repositorio;

import gm.Ajedrez.modelo.Deportista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeportistaRepositorio extends JpaRepository<Deportista, Integer> {
}
