package com.educative.ecommerce.service;
import com.educative.ecommerce.dto.CategoryDto;
import com.educative.ecommerce.dto.ProductDto;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.repository.Categoryrepository;
import com.educative.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private Categoryrepository categoryrepository;
    private ProductRepository productRepository;
    public List<Category> listCategories()
    {
        return categoryrepository.findAll();
    }
    public void createCategory(Category category)
    {

        categoryrepository.save(category);
    }
   public Category readCategory(String categoryName)
   {
        return categoryrepository.findByCategoryName(categoryName);
    }
    public Optional<Category> readCategory(Integer categoryId)
    {

        return categoryrepository.findById(categoryId);
    }
    public void updateCategory(Integer categoryID, Category newCategory)
    {
        Category category = categoryrepository.findById(categoryID).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        categoryrepository.save(category);
    }

    public CategoryDto getCategoriesInfo(Integer Id)
    {
        CategoryDto categoryResponse = new CategoryDto();
        Category category = categoryrepository.findById(Id).get();
        categoryResponse.setId(category.getId());
        categoryResponse.setCategoryName(category.getCategoryName());
        categoryResponse.setDescription(category.getDescription());
        categoryResponse.setImageUrl(category.getImageUrl());
        List<Product> product = category.getProduct();
        List<ProductDto> productResponses = new ArrayList<>();
        for (int i = 0; i < product.size(); i++)
        {
            Product productEntity = product.get(i);
            ProductDto productResponse = new ProductDto();
            productResponse.setId(productEntity.getId());
            productResponse.setDescription(productEntity.getDescription());
            productResponse.setName(productEntity.getName());
            productResponse.setPrice(productEntity.getPrice());
            productResponse.setImageURL(productEntity.getImageURL());
     //      productResponse.setCategoryId(productEntity.getCategoryId());
            productResponses.add(productResponse);
        }
        categoryResponse.setProduct(productResponses);
        return categoryResponse;
    }
}