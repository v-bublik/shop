package edu.karazin.shop.service;

import edu.karazin.shop.model.*;
import edu.karazin.shop.repository.OrdersRepository;
import edu.karazin.shop.repository.PurchaseItemRepository;
import edu.karazin.shop.util.ProductUtil;
import edu.karazin.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private ProductUtil productUtil;
	private final PurchaseItemRepository purchaseItemRepository;
	private final OrdersRepository ordersRepository;

	public ProductServiceImpl(@Autowired ProductRepository productRepository, @Autowired OrdersRepository ordersRepository,
                              @Autowired PurchaseItemRepository purchaseItemRepository) {
		this.productRepository = productRepository;
		this.purchaseItemRepository = purchaseItemRepository;
		this.ordersRepository = ordersRepository;
	}

    @Autowired
	public void setProductUtil(ProductUtil productUtil) {
	    this.productUtil = productUtil;
    }

	public BasketItem getBasketItems(Long id) { return productUtil.convertEntityToBasketItem(productRepository.getProductById(id)); }


    @Override
	public Product getProduct(Long id) {
		return productRepository.getProductById(id);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getBasketItems(List<BasketItem> basketItems) {
		List<Product> list = new ArrayList<>();
		for (BasketItem basketItem : basketItems) {
			list.add(productRepository.getProductById(basketItem.getProduct().getId()));
		}
		return list;
	}

	@Override
    @Transactional
	public List<Long> addPurchaseItems(List<PurchaseItem> purchaseItems) {
	    List<Long> ids = new ArrayList<>();
        for (PurchaseItem purchaseItem : purchaseItems) {
            ids.add(purchaseItemRepository.save(purchaseItem).getId());
        }
        return ids;
	}

    @Override
    public Long addPurchaseItem(PurchaseItem purchaseItem) {
        return purchaseItemRepository.save(purchaseItem).getId();
    }

    @Override
	public List<Product> searchProducts(String searchText) {
		if (searchText == null || searchText.trim().isEmpty()) {
			return productRepository.findAll();
		}
		return productRepository.findByText(searchText);
	}

	@Override
	@Transactional
	public Long addProduct(Product prod, MultipartFile img) throws IOException {
			for (Product product : getAll()) {
				if (prod.equals(product)) {
					return null;
				}
            }
			prod.setImageName(ProductUtil.imgPersist(img));
            return productRepository.save(prod).getId();
    }

    @Override
    @Transactional
	public void setDiscountForAllProducts(Long discountPercent) {
		for (Product product : getAll()) {
			product.setCost(product.getCost() - (product.getCost() / 100 * discountPercent));
            productRepository.save(product);
		}
	}


    @Override
    @Transactional
    public void updateProduct(Product prod) {
        productRepository.save(prod);
    }

	@Override
	@Transactional
	public void updateProduct(Product prod, MultipartFile img) throws IOException {
        prod.setImageName(ProductUtil.imgPersist(img));
		productRepository.save(prod);
	}


	@Override
	@Transactional
	public void removeProduct(Long id) {
		productRepository.delete(id);
	}

    @Override
    @Transactional
    public void deleteAll() {
        for (Product product : getAll()) {
            removeProduct(product.getId());
        }
    }

    @Override
    public void makeOrderForCart(List<Long> ids, User currentAuthenticatedUser) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        String date = simpleDateFormat.format(new Date().getTime());

        for (Long id : ids) {
            ordersRepository.save(new Orders(currentAuthenticatedUser.getId(), id, date));
        }

    }

    @Override
    public void makeOrder(Long id, User currentAuthenticatedUser) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        String date = simpleDateFormat.format(new Date().getTime());
        ordersRepository.save(new Orders(currentAuthenticatedUser.getId(), id,  date));
    }
}


class A {
    public static void main(String[] args) {
        String s = "asdas";
    }
}