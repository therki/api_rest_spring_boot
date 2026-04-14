package com.openwebinar.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/hello")
    public String sayHello(){
        return"Hello World";
    }

    @GetMapping("/producto")
    public Producto obtenerProducto(
            @RequestBody Producto producto
    ){
        return producto;
    }

    @PostMapping("/producto")
    public Producto nuevoProducto(
            @RequestBody Producto producto
    ){
        return producto;
    }
    record Producto (Long id, String nombre){}
}
