package mx.edu.utez.usuarios_tickets.repository;

import mx.edu.utez.usuarios_tickets.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}