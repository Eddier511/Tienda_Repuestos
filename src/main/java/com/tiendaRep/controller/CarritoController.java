package com.tiendaRep.controller;

import com.tiendaRep.domain.Item;
import com.tiendaRep.domain.Producto;
import com.tiendaRep.service.ItemService;
import com.tiendaRep.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
    
    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("/agregar/{idProducto}")
    public ModelAndView agregar(Model model, Item item) {
        //Se vslida si el idProducto ya esta en el carrito
        Item item2 = itemService.getItem(item);
        
        //Se valida si se encontro...
        if(item2==null) {
        //Se debe crear el item con la informacion del producto
        Producto p = productoService.getProducto(item);
        item2= new Item(p);
    }
        //Finalmente se agrega el item al carrito
        itemService.save(item2);
        
        var lista = itemService.getItems();
        var totalCompra= itemService.getTotal();
        model.addAttribute("listaItems",lista);
        model.addAttribute("totalProductos",lista.size());
        model.addAttribute("totalCompra", totalCompra);
        
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }
    
    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = itemService.getItems();
        var totalCompra= itemService.getTotal();
        model.addAttribute("listaItems", lista);
        model.addAttribute("totalCompra", totalCompra);
        return "/carrito/listado";
    }
    
    @GetMapping("/eliminar/{idProducto}")
    public String eliminar(Model model, Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }
    
    @GetMapping("/modificar/{idProducto}")
    public String modificar(Model model, Item item) {
        item = itemService.getItem(item);
        model.addAttribute("item", item);
        return "/carrito/modifica";
    }
    
    @PostMapping("/guardar")
    public String guardar(Item item) {
        itemService.update(item);
        return "redirect:/carrito/listado";
    }
}
