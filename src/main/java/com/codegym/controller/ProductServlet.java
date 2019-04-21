package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceIpml;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceIpml();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "create":
                CreateProduct(request, response);
                break;
            case "edit":
                EditProduct(request, response);
                break;
            case "delete":
                break;
            case "search":
                break;
            default:
                break;
        }
    }

    private void EditProduct(HttpServletRequest request, HttpServletResponse response) {
   int id = Integer.parseInt(request.getParameter("id"));
   String name = request.getParameter("name");
   float price = Float.parseFloat(request.getParameter("price"));
   String product_description = request.getParameter("product_description");
   String producer = request.getParameter("producer");
   Product product = this.productService.findById(id);
   RequestDispatcher dispatcher;

   if (producer == null){
       dispatcher = request.getRequestDispatcher("error-404.jsp");
   }else {
       product.setName(name);
       product.setPrice(price);
       product.setProduct_description(product_description);
       product.setProducer(producer);
       this.productService.update(id, product);
       request.setAttribute("product", product);
       request.setAttribute("message", "product information was updated");
       dispatcher = request.getRequestDispatcher("Product/edit.jsp");
   }
   try {
       dispatcher.forward(request, response);
   } catch (ServletException e) {
       e.printStackTrace();
   } catch (IOException e) {
       e.printStackTrace();
   }
    }

    private void CreateProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
//        String price = request.getParameter("price");
//        float priceFloat = Float.parseFloat(price);
        float price = Float.parseFloat(request.getParameter("price"));
        String product_description = request.getParameter("product_description");
        String producer = request.getParameter("producer");

        int id = (int)(Math.random()*10000);

        Product product = new Product(id, name, price, product_description, producer );
        this.productService.save(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Product/create.jsp");
        request.setAttribute("message", "new product was created");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                break;
            case "search":
                break;
            case "view":
                break;
            default:
                listProducts(request, response);
                break;
        }

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
    int id = Integer.parseInt(request.getParameter("id"));
    Product product = this.productService.findById(id);
    RequestDispatcher dispatcher;
    if (product == null){
        dispatcher = request.getRequestDispatcher("error-404.jsp");
    }
    else {
        request.setAttribute("product", product);
        dispatcher = request.getRequestDispatcher("Product/edit.jsp");

    }
    try {
        dispatcher.forward(request, response);
    } catch (ServletException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Product/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = this.productService.findAll();
        request.setAttribute("products", products);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Product/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
