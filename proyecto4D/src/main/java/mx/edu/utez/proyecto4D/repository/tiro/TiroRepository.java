package mx.edu.utez.proyecto4D.repository.tiro;

import mx.edu.utez.proyecto4D.model.tiro.Tiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiroRepository extends JpaRepository<Tiro, Long> {

}
