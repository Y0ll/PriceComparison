package main;

import main.model.Product;
import main.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DefaultController
{
    @Autowired
    ProductRepository productRepository;
    @RequestMapping(value = "/")
    public String index(Model model)
    {
        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        for(Product product : products)
        {
            productList.add(product);

        }
        model.addAttribute("products", productList);

        return "index";
    }




}
