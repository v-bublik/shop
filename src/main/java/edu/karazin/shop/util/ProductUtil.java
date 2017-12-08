package edu.karazin.shop.util;

import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.model.PurchaseItem;
import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductUtil {

    private  ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    public ProductService getProductService() {
        return productService;
    }

    // conversion

    public BasketItem convertEntityToBasketItem(Product product) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(productService.getProduct(product.getId()));
        return basketItem;
    }

    public List<PurchaseItem> convertBasketItemsToPurchaseItems(List<BasketItem> basketItems) {
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        PurchaseItem purchaseItem;
        for (BasketItem basketItem : basketItems) {
            purchaseItem = new PurchaseItem();
            purchaseItem.setCost(basketItem.getProduct().getCost());
            purchaseItem.setDescription(basketItem.getProduct().getDescription());
            purchaseItem.setImageName(basketItem.getProduct().getImageName());
            purchaseItem.setTitle(basketItem.getProduct().getTitle());
            purchaseItem.setPurchaseItemAmount(basketItem.getCountOfProducts());
            purchaseItems.add(purchaseItem);
        }
        return purchaseItems;
    }

    public PurchaseItem convertProductToPurchaseItems(Product product) {
        PurchaseItem purchaseItem = new PurchaseItem();
        purchaseItem.setTitle(product.getTitle());
        purchaseItem.setDescription(product.getDescription());
        purchaseItem.setCost(product.getCost());
        purchaseItem.setImageName(product.getImageName());
        purchaseItem.setPurchaseItemAmount(1L);
        return purchaseItem;
    }


    public void addTheSameProductToCart(BasketItem prod, List<BasketItem> basketItems) {
        for (BasketItem basketItem : basketItems) {
            if (basketItem.equals(prod)) {
                basketItem.setCountOfCost(basketItem.getCountOfCost() + basketItem.getProduct().getCost());
                basketItem.setCountOfProducts(basketItem.getCountOfProducts() + 1);
            }
        }
    }

    public static String imgPersist(MultipartFile img) throws IOException {
        if (img.isEmpty()) return null;
        String imgName = img.getOriginalFilename();
        File upl = new File("src/main/resources/static/".concat(imgName));
        upl.createNewFile();
        try (FileOutputStream fout = new FileOutputStream(upl)) {
            fout.write(img.getBytes());
        }
        return imgName;
    }

    // order

    public boolean checkForExistanceAndDecrement(Long prodId) {
        if (productService.getProduct(prodId).getBalance() != 0) {
            Product product = productService.getProduct(prodId);
            product.setBalance(product.getBalance() - 1);
            productService.updateProduct(product);
            return true;
        }
        return false;
    }

    public boolean checkForExistanceForCartAndDecrement(List<BasketItem> products, CartStore cartStore) {
        for (BasketItem basketItem : cartStore.getProducts()) {
            if (!(basketItem.getCountOfProducts() <= basketItem.getProduct().getBalance()))
                return false;
        }
        for (BasketItem basketItem : products) {
            basketItem.getProduct().setBalance(basketItem.getProduct().getBalance() - basketItem.getCountOfProducts());
            productService.updateProduct(basketItem.getProduct());
        }
        return true;
    }


    // validation

    public static boolean validate(Product product) {
        String title = product.getTitle();
        String description = product.getDescription();
        double cost = product.getCost();
        long balance = product.getBalance();
        if (org.h2.util.StringUtils.isNumber(title) || title.toCharArray().length > 10
                || title.isEmpty()) return false;
        if(description.toCharArray().length > 1000) return false;
        return true;
    }


}