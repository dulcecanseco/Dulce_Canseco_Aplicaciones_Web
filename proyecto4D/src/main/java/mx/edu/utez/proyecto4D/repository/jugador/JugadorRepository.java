package mx.edu.utez.proyecto4D.repository.jugador;

import mx.edu.utez.proyecto4D.model.jugador.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

}
