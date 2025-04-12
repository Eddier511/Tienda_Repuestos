package com.tiendaRep.repository;

import com.tiendaRep.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository <Factura,Long> {
     
}

