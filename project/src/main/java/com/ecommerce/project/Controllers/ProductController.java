package com.ecommerce.project.Controllers;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.Models.DataModal;
import com.ecommerce.project.Models.Product;
import com.ecommerce.project.Repositorys.ProductRepository;


@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepo;

    @GetMapping("/Product")
    public DataModal  getAllProduct(){
        return new DataModal(200, "Product get successfully", productRepo.findAll());
    }
    @PostMapping("/addProducts")
    public DataModal addProduct(@RequestBody Product product){
        productRepo.save(product);
        return new DataModal(200, "Product added successfully",product);

    }
    @PostMapping("/deleteProduct/{id}")
    public DataModal deleteProduct(@PathVariable String id){
        if(id == null || id.isEmpty()){
        return new DataModal(400,"Invalid Id",id);}
        productRepo.deleteById(id);
        return new DataModal(200, "product deleted successfully",id );
    }
    

    @PostMapping("/updateProduct/{id}")
    public DataModal updateProduct(@PathVariable String id ,@RequestBody Product product){
        if(id == null || id.isEmpty()){
        return new DataModal(400,"Invalid Id",id);}
        Product existingProduct =productRepo.findById(id).orElse(null);
        if (existingProduct == null){
            return new DataModal(404, "product not found", null);
        }
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
    productRepo.save(existingProduct);
     return new DataModal(200, "product updated successfully",existingProduct);
    
    }
}
