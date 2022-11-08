package main;

import main.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ParsController
{
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MonthRepository monthRepository;

    @Autowired
    private PriceRepository priceRepository;



    @RequestMapping(value = "/setProduct/", method = RequestMethod.POST) // установить setProduct
    public void SetProduct(String nameProduct) throws IOException, InterruptedException {
//        Product product = new Product();
//        product.setNameProduct(nameProduct);
        ParsingShop parsingShop = new ParsingShop(nameProduct);

         Thread mvideo = new Thread(() ->
        {
            try {
                saveInDataBase(parsingShop.parsingMVIDEO(), 1);


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

       Thread dns = new Thread(() ->
       {
           try {
               saveInDataBase(parsingShop.parsingDNS(), 2);

           } catch (IOException | InterruptedException e) {
               e.printStackTrace();
           }


       });
        mvideo.start();
        mvideo.join();
        dns.start();
        dns.join();
    }


//    @RequestMapping(value = "/setProduct/", method = RequestMethod.POST) // удалить этот метод, тестил с ним POST он не нежуен
//    public void SetProductsssss(String nameProduct) {
//
//        System.out.println(nameProduct);
//    }


//    @GetMapping("/products/")
//    public List<Product> listProduct()
//    {
//        Iterable<Product> products = productRepository.findAll();
//        List<Product> productList = new ArrayList<>();
//        for(Product product : products)
//        {
//            productList.add(product);
//        }
//        return productList;
//    }
//    @GetMapping("/product/{id}")
//    public ResponseEntity<?> product(@PathVariable int id)
//    {
//        Optional<Product> products = productRepository.findById(id);
//        return ResponseEntity.ok()
//                .header("productName", products.get().getNameProduct())
//                .body(new InputStreamResource(new ByteArrayInputStream(products.get().getNameProduct().getBytes())));
//    }


//    @GetMapping("/product/{id}")
//    public String product(@PathVariable int id)
//    {
//        Optional<Price> price = priceRepository.findById(id);
//        return price.get().getShop().getShop() + " " + price.get().getProduct().getNameProduct() + " " + price.get().getPrice() + " " + price.get().getProduct().getMonth().getMonth() + " " + price.get().getCount();
//    }


    @GetMapping("/price/")
    public  List<String[]> listPrice()
    {


        Iterable<Price> prices = priceRepository.findAll();
        List<String[]> priceList = new ArrayList<>();

        for(Price price : prices)
        {
            String[] str = new String[7];
            str[0] = price.getProduct().getNameProduct();
            str[1] = price.getPrice();
            str[2] = price.getCount();
            str[3] = price.getShop().getShop();
            str[4] = price.getProduct().getMonth().getMonth();
            str[5] = price.getProduct().getHrefProducts();
            str[6] = price.getProduct().getImage();

            priceList.add(str) ;
        }
        return priceList;
    }

    @GetMapping("/priceDNS/")
    public List<String[]> listPriceDNS()
    {

        Iterable<Price> prices = priceRepository.findAll();
        List<String[]> priceList = new ArrayList<>();

        for(Price price : prices)
        {
            if(price.getShop().getShop().equals("DNS")) {

                String[] str = new String[7];
                str[0] = price.getProduct().getNameProduct();
                str[1] = price.getPrice();
                str[2] = price.getCount();
                str[3] = price.getShop().getShop();
                str[4] = price.getProduct().getMonth().getMonth();
                str[5] = price.getProduct().getHrefProducts();
                str[6] = price.getProduct().getImage();

                priceList.add(str) ;
            }
        }

        return priceList;
    }


    @GetMapping("/priceMVIDEO/")
    public List<String[]> listPriceMVIDEO()
    {

        Iterable<Price> prices = priceRepository.findAll();
        List<String[]> priceList = new ArrayList<>();

        for(Price price : prices)
        {
            if(price.getShop().getShop().equals("MVIDEO")) {
                String[] str = new String[7];
                str[0] = price.getProduct().getNameProduct();
                str[1] = price.getPrice();
                str[2] = price.getCount();
                str[3] = price.getShop().getShop();
                str[4] = price.getProduct().getMonth().getMonth();
                str[5] = price.getProduct().getHrefProducts();
                str[6] = price.getProduct().getImage();

                priceList.add(str) ;            }
        }

        return priceList;
    }

    @GetMapping("/priceInStock/")
    public List<String[]> listPriceInStock()
    {

        Iterable<Price> prices = priceRepository.findAll();
        List<String[]> priceList = new ArrayList<>();


        for(Price price : prices)
        {
            if(!price.getCount().equals("Товара нет в наличии")) {
                String[] str = new String[7];
                str[0] = price.getProduct().getNameProduct();
                str[1] = price.getPrice();
                str[2] = price.getCount();
                str[3] = price.getShop().getShop();
                str[4] = price.getProduct().getMonth().getMonth();
                str[5] = price.getProduct().getHrefProducts();
                str[6] = price.getProduct().getImage();

                priceList.add(str) ;
            }
        }

        return priceList;
    }

    @GetMapping("/priceToDay/")
    public List<String[]> listPriceToDay()
    {

        Iterable<Price> prices = priceRepository.findAll();
        List<String[]> priceList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        Calendar calendar = new GregorianCalendar();

        for(Price price : prices)
        {
            if(price.getProduct().getMonth().getMonth().equals(df.format(calendar.getTime()))){
                String[] str = new String[7];
                str[0] = price.getProduct().getNameProduct();
                str[1] = price.getPrice();
                str[2] = price.getCount();
                str[3] = price.getShop().getShop();
                str[4] = price.getProduct().getMonth().getMonth();
                str[5] = price.getProduct().getHrefProducts();
                str[6] = price.getProduct().getImage();

                priceList.add(str) ;
            }
        }

        return priceList;
    }

    @GetMapping("/priceYesterday/")
    public List<String[]> listPriceYesterday()
    {

        Iterable<Price> prices = priceRepository.findAll();
        List<String[]> priceList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);

        for(Price price : prices)
        {
            if(price.getProduct().getMonth().getMonth().equals(df.format(calendar.getTime()))){
                String[] str = new String[7];
                str[0] = price.getProduct().getNameProduct();
                str[1] = price.getPrice();
                str[2] = price.getCount();
                str[3] = price.getShop().getShop();
                str[4] = price.getProduct().getMonth().getMonth();
                str[5] = price.getProduct().getHrefProducts();
                str[6] = price.getProduct().getImage();

                priceList.add(str) ;
            }
        }

        return priceList;
    }

    @GetMapping("/priceMonth/")
    public List<String[]> listPriceMonth()
    {

        Iterable<Price> prices = priceRepository.findAll();
        List<String[]> priceList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        int idDate = 0;

        for(Price price : prices)
        {
            if(price.getProduct().getMonth().getMonth().equals(df.format(calendar.getTime()))){
                idDate = price.getProduct().getMonth().getId();
            }
        }

        for(Price price : prices)
        {
            if(price.getProduct().getMonth().getId() >= idDate){
                String[] str = new String[7];
                str[0] = price.getProduct().getNameProduct();
                str[1] = price.getPrice();
                str[2] = price.getCount();
                str[3] = price.getShop().getShop();
                str[4] = price.getProduct().getMonth().getMonth();
                str[5] = price.getProduct().getHrefProducts();
                str[6] = price.getProduct().getImage();

                priceList.add(str) ;            }
        }

        return priceList;
    }



    public Price saveInDataBase(List<String> properties, int idShop)
    {


        boolean exist = false;
        int idProd = 0;
        int currentIdData = 0;


        Iterable<Month> months = monthRepository.findAll();
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        Calendar calendar = new GregorianCalendar();
        Month newData = new Month();
        for(Month month: months)
        {
            if(month.getMonth().equals((df.format(calendar.getTime()))))
            {
                newData = month;
                exist = true;
                currentIdData = newData.getId();
            }
        }
        if(exist == false) {
            newData.setMonth((df.format(calendar.getTime())));
            monthRepository.save(newData);
            currentIdData = newData.getId();
        }
        exist = false;



        Shop newShop = new Shop();
        newShop.setShop(properties.get(2));
        newShop.setId(idShop);
        shopRepository.save(newShop);



        Product newProduct = new Product();
        Iterable<Product> products = productRepository.findAll();
        for(Product product : products)
        {
            if(product.getNameProduct().equals(properties.get(0)) && currentIdData == product.getMonth().getId())
            {
                idProd = product.getIdProduct();
                exist = true;
            }
        }
        if(exist == false)
        {
            newProduct.setNameProduct(properties.get(0));
            newProduct.setMonth(newData);
            newProduct.setHrefProducts(properties.get(4));
            newProduct.setImage(properties.get(5));
            productRepository.save(newProduct);
            idProd = newProduct.getIdProduct();
        }
        exist = false;




        Price newPrice = new Price();
        Iterable<Price> prices = priceRepository.findAll();

        for(Price price: prices)
        {
            if((price.getProduct().getIdProduct() == idProd) && (price.getPrice().equals(properties.get(1))) &&
                    ((price.getShop().getId() == newShop.getId())))
            {
                exist = true;
            }
        }
        if(exist == false)
        {
            newPrice.setPrice(properties.get(1));
            newPrice.setProduct(newProduct);
            //newPrice.setCount(1); // Доделать парсер для кол-ва товаров
            newPrice.setShop(newShop);
            try {
                newPrice.setCount(properties.get(3));
            }
            catch (Exception ex)
            {
                newPrice.setCount("Наличие неизвестно");
            }

            priceRepository.save(newPrice);
        }
        exist = false;


        return newPrice;
    }

}
