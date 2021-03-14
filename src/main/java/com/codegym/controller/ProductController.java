package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.ICategoryService;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;


    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }



    @GetMapping
    public ModelAndView showListProducts() {

        ModelAndView modelAndView = new ModelAndView("/infor");
        modelAndView.addObject("products",productService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Product product) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        Date date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(formatter.format(date));
//        product.setDate(date1);
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/create", "product", new Product());
        modelAndView.addObject("mess", "Tao moi thanh cong product ten la " + product.getName());
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView updateCustomer(@PathVariable Long id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView updateCustomer(@ModelAttribute("product") Product product) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(formatter.format(product.getDate()));
//        product.setDate(date);
        ModelAndView modelAndView = new ModelAndView("/update");

            productService.save(product);
            modelAndView.addObject("message","Updated " + product.getName() +" success!");
            return modelAndView;


    }
    @GetMapping("/delete/{id}")
    public ModelAndView showCutomerDelete(@PathVariable Long id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("product",product);
        return modelAndView;
    }
    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return  "redirect:/products";
    }

        @GetMapping("/seachtopprice")
    public ModelAndView showformPrice() {
        ModelAndView modelAndView = new ModelAndView("/listtopprice");
        modelAndView.addObject("top5ProductsByPrice",productService.findTopByPriceIsGreaterThan());
        return modelAndView;
    }

    @GetMapping("/topproductslastest")
    public ModelAndView showformDate() {

        ModelAndView modelAndView = new ModelAndView("/toplastest");
        modelAndView.addObject("top",productService.findTopByLastest());
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(@ModelAttribute Category category) {
        ModelAndView modelAndView = new ModelAndView("/listProductsByCategory");
        modelAndView.addObject("products", productService.findAllProductByCategory(category));
        return modelAndView;
    }

}
