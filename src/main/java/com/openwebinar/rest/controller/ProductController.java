package com.openwebinar.rest.controller;

import com.openwebinar.rest.model.Product;
import com.openwebinar.rest.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
//@CrossOrigin(originPatterns = "http://localhost:[*]", methods = RequestMethod.GET)
@Tag(name = "Products", description = "Gestión básica de productos")
public class ProductController {
    private final ProductService productService;

    //@CrossOrigin(origins = "http://localhost:9000")
    @GetMapping
    public List<Product> getAll(
            @RequestParam(required = false, value = "maxPrice", defaultValue = "99999") double max,
            @RequestParam(required = false, value = "sort", defaultValue = "no") String sortDirection
        ){
        return productService.query(max, sortDirection);
    }
    @Operation(
            summary = "Obtener un producto concreto ",
            description = "Permite obtener la información de un producto por su identificador",
            tags = {"params", "products", "details"}
    )
    @ApiResponse(
            description = "Información detallada del producto", responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Product.class),
                    examples = {
                            @ExampleObject("""
                                {
                                    "id":3,
                                    "name":"Headphones",
                                    "price":150.0
                                }
                            """)
                    }
            )
    )
    @GetMapping("/{id:[0-9]+}")
    public Product getById(@Parameter(description = "Identificador del producto") @PathVariable Long id){
        //return ResponseEntity.of(productRepository.get(id));
        return productService.get(id);
    }

    @PostMapping
    public ResponseEntity<Product> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Producto a crear",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Product.class),
                            examples = {
                                    @ExampleObject("""
                                {
                                    "id":3,
                                    "name":"Headphones",
                                    "price":150.0
                                }
                            """)
                            }
                    )
            )
            @RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.add(product));
    }

    @PutMapping("/{id}")
    public Product edit(
            @RequestBody Product product,
            @PathVariable Long id
    ){
        return productService.edit(id, product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
