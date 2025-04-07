
package com.tiendaRep.repository;

import com.tiendaRep.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    
}
