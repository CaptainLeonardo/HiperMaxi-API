package com.hipermaxi.controller;

import com.hipermaxi.dtos.ProductoCreateDTO;
import com.hipermaxi.dtos.ProductoDTO;
import com.hipermaxi.dtos.ProductoUpdateDTO;
import com.hipermaxi.model.Producto;
import com.hipermaxi.repository.ProductoRepository;
import com.hipermaxi.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("productos")
    public ResponseEntity<List<ProductoDTO>> listarProductos(){
        return new ResponseEntity<>(productoService.listarProductos(), HttpStatus.OK);
    }

    @GetMapping("/productos/{productoId}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable("productoId") long productoId){
        return new ResponseEntity<>(productoService.obtenerProductoPorID(productoId),HttpStatus.OK);
    }

    @PostMapping("productos")
    public ResponseEntity<ProductoDTO> registrarProducto(@RequestBody ProductoCreateDTO productoCreateDTO){
        return new ResponseEntity<>(productoService.registrarProducto(productoCreateDTO),HttpStatus.OK);
    }

    @PutMapping("productos")
    public ResponseEntity<ProductoDTO> actualizarProducto(@RequestBody ProductoUpdateDTO productoUpdateDTO){
        return new ResponseEntity<>(productoService.actualizarProducto(productoUpdateDTO),HttpStatus.OK);
    }

    @DeleteMapping("/productos/{productoId}")
    public ResponseEntity<String> eliminarProducto(@PathVariable("productoId") long productoId){
        return new ResponseEntity<>(productoService.eliminarProducto(productoId),HttpStatus.OK);
    }
}
