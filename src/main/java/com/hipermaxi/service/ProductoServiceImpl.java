package com.hipermaxi.service;

import com.hipermaxi.dtos.ProductoCreateDTO;
import com.hipermaxi.dtos.ProductoDTO;
import com.hipermaxi.dtos.ProductoUpdateDTO;
import com.hipermaxi.mappers.ProductoMapper;
import com.hipermaxi.model.Producto;
import com.hipermaxi.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> listarProductos() {
        return ProductoMapper.instancia.listaProductoAListaProductoDTO(productoRepository.findAll());
    }

    @Override
    public ProductoDTO obtenerProductoPorID(long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        ProductoDTO productoDTO;
        if (producto.isPresent()){
            productoDTO = ProductoMapper.instancia.productoAProductoDTO(producto.get());
        }else {
            productoDTO = null;
        }
        return productoDTO;
    }

    @Override
    public ProductoDTO registrarProducto(ProductoCreateDTO productoCreateDTO) {
        Producto producto = ProductoMapper.instancia.productoCreateDTOAProducto(productoCreateDTO);
        Producto respuestaEntity = productoRepository.save(producto);
        ProductoDTO respuestaDTO =  ProductoMapper.instancia.productoAProductoDTO(respuestaEntity);
        return respuestaDTO;
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoUpdateDTO productoUpdateDTO) {
        Producto producto = ProductoMapper.instancia.productoUpdateAProducto(productoUpdateDTO);
        Producto respuestaEntity = productoRepository.save(producto);
        ProductoDTO respuestaDTO =  ProductoMapper.instancia.productoAProductoDTO(respuestaEntity);
        return respuestaDTO;
    }

    @Override
    public String eliminarProducto(long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        String resultado;
        if (productoOptional.isPresent()){
            productoRepository.delete(productoOptional.get());
            resultado = "Registro Eliminado";
        }else {
            resultado = "No se pudo realizar la eliminacion";
        }
        return resultado;
    }
}
