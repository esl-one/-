package job.service;

import java.util.List;

import job.bean.Products;
import job.dao.ProductsDao;

public class ProductsService {
	private ProductsDao psd = null;

	public ProductsService() {
		super();
		this.psd = new ProductsDao() ;
	}
	//添加商品
	public void addProduct(Products product){
		psd.addProduct(product);
	}
	//查询所有商品
	public List<Products> findAll(){		
		return psd.findAll();
	}
	//根据id查询商品
	public Products findById(String id){		
		return psd.findById(id);
	}
	//删除商品
	public void delProduct(String id){
		
		psd.delProducts(id);
	}
}
