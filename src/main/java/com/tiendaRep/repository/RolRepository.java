
package com.tiendaRep.repository;

import com.tiendaRep.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository 
        extends JpaRepository<Rol,Long> {
    
}
