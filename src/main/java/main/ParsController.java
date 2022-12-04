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

    public static List<Price> priceStatic;



    @RequestMapping(value = "/setProduct/", method = RequestMethod.POST) // установить setProduct
    public List<String[]> SetProduct(String nameProduct) throws IOException, InterruptedException {
        ParsingShop parsingShop = new ParsingShop(nameProduct);
        List<Price> prices = new ArrayList<>();

         Thread mvideo = new Thread(() ->
        {
            try {
                prices.add(saveInDataBase(parsingShop.parsingMVIDEO(), 1));


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

       Thread dns = new Thread(() ->
       {
           try {
               prices.add(saveInDataBase(parsingShop.parsingDNS(), 2));

           } catch (IOException | InterruptedException e) {
               e.printStackTrace();
           }


       });
        mvideo.start();
        mvideo.join();
        dns.start();
        dns.join();

        List<String[]> priceList = new ArrayList<>();

        for(Price price : prices)
        {
            String[] str = new String[7];
            str[0] = price.getProduct().getNameProduct();
            str[1] = String.valueOf(price.getPrice());
            str[2] = price.getCount();
            str[3] = price.getShop().getShop();
            str[4] = price.getProduct().getMonth().getMonth();
            str[5] = price.getProduct().getHrefProducts();
            str[6] = price.getProduct().getImage();
            priceList.add(str) ;
        }
        return priceList;
    }


    @GetMapping("/priceSort/")
    public List<String[]> listPriceSort()
    {
        Iterable<Price> prices = priceRepository.findAllByOrderByPriceAsc();

        List<String[]> priceList = new ArrayList<>();

        for(Price price : prices)
        {
            String[] str = new String[7];
            str[0] = price.getProduct().getNameProduct();
            str[1] = String.valueOf(price.getPrice());
            str[2] = price.getCount();
            str[3] = price.getShop().getShop();
            str[4] = price.getProduct().getMonth().getMonth();
            str[5] = price.getProduct().getHrefProducts();
            str[6] = price.getProduct().getImage();
            priceList.add(str) ;
        }
        return priceList;
    }


    @GetMapping("/price/")
    public  List<String[]> listPrice()
    {


        Iterable<Price> prices = priceRepository.findAll();
        List<String[]> priceList = new ArrayList<>();

        for(Price price : prices)
        {
            String[] str = new String[7];
            str[0] = price.getProduct().getNameProduct();
            str[1] = String.valueOf(price.getPrice());
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
                str[1] = String.valueOf(price.getPrice());
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
                str[1] = String.valueOf(price.getPrice());
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
                str[1] = String.valueOf(price.getPrice());
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
                str[1] = String.valueOf(price.getPrice());
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
                str[1] = String.valueOf(price.getPrice());
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
                str[1] = String.valueOf(price.getPrice());
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
            if((price.getProduct().getIdProduct() == idProd) && (price.getPrice() == (Integer.valueOf(properties.get(1).split("₽")[0].replaceAll("\\s+", "")))) &&
                    ((price.getShop().getId() == newShop.getId())))
            {
                exist = true;
            }
        }
        if(exist == false)
        {

            newPrice.setPrice(Integer.valueOf(properties.get(1).split("₽")[0].replaceAll("\\s+", "")));
            newPrice.setProduct(newProduct);
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
