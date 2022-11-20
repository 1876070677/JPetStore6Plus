/*
 *    Copyright 2010-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.jpetstore.web.actions;

import java.math.BigDecimal;
import java.util.List;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;

import net.sourceforge.stripes.validation.Validate;
import org.mybatis.jpetstore.domain.Category;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.service.CatalogService;

import javax.servlet.http.HttpSession;

/**
 * The Class CatalogActionBean.
 *
 * @author Eduardo Macarron
 */
@SessionScope
public class CatalogActionBean extends AbstractActionBean {

  private static final long serialVersionUID = 5849523372175050635L;

  private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
  private static final String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";
  private static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";
  private static final String VIEW_ADMIN_PRODUCT = "/WEB-INF/jsp/admin/Product.jsp";
  private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";
  private static final String SEARCH_PRODUCTS = "/WEB-INF/jsp/catalog/SearchProducts.jsp";
  private static final String ADMIN_DASHBOARD = "/WEB-INF/jsp/admin/Dashboard.jsp";

  private static final String VIEW_EDIT_ITEM_FORM = "/WEB-INF/jsp/admin/EditItem.jsp";
  private static final String VIEW_ADD_ITEM_FORM = "/WEB-INF/jsp/admin/AddItem.jsp";

  @SpringBean
  private transient CatalogService catalogService;

  private String keyword;

  private String categoryId;
  private Category category;
  private List<Category> categoryList;

  private String productId;
  private Product product;
  private List<Product> productList;

  private String itemId;
  private Item item;
  private List<Item> itemList;

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getItemId() {
    return itemId;
  }

  @Validate(required = true, on = {"addItem"})
  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public List<Category> getCategoryList() {
    return categoryList;
  }

  public void setCategoryList(List<Category> categoryList) {
    this.categoryList = categoryList;
  }

  public List<Product> getProductList() {
    return productList;
  }

  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }

  public List<Item> getItemList() {
    return itemList;
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }

  @Validate(required = true, on = {"updateItem", "addItem"})
  public void setAttribute1(String attribute1) {item.setAttribute1(attribute1);}

  public String getAttribute1() { return item.getAttribute1(); }
  @Validate(required = true, on = {"updateItem", "addItem"})
  public void setListPrice(BigDecimal listPrice) {item.setListPrice(listPrice);}

  public BigDecimal getListPrice() { return item.getListPrice(); }
  @Validate(required = true, on = {"updateItem", "addItem"})
  public void setQuantity(int quantity) {item.setQuantity(quantity);}

  public int getQuantity() { return item.getQuantity(); }

  @DefaultHandler
  public ForwardResolution viewMain() {
    return new ForwardResolution(MAIN);
  }

  /**
   * View category.
   *
   * @return the forward resolution
   */
  public ForwardResolution viewCategory() {
    if (categoryId != null) {
      productList = catalogService.getProductListByCategory(categoryId);
      category = catalogService.getCategory(categoryId);
    }
    return new ForwardResolution(VIEW_CATEGORY);
  }

  public Resolution viewAllCategory() {
    HttpSession session = context.getRequest().getSession();
    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
    clear();

    if (accountBean == null || !accountBean.isAuthenticated()) {
      setMessage("Please sign on and try checking out again.");
      return new RedirectResolution(CatalogActionBean.class);
    } else if (accountBean.getAccount().getAuth() == 0) {
      setMessage("You are not Admin!!");
      return new RedirectResolution(CatalogActionBean.class);
    } else {
      productList = catalogService.getProductListByCategory();
      category = catalogService.getCategory(categoryId);
      return new ForwardResolution(ADMIN_DASHBOARD);
    }
  }

  /**
   * View product.
   *
   * @return the forward resolution
   */
  public ForwardResolution viewProduct() {
    if (productId != null) {
      itemList = catalogService.getItemListByProduct(productId);
      product = catalogService.getProduct(productId);
    }
    return new ForwardResolution(VIEW_PRODUCT);
  }

  public Resolution manageProduct() {
    HttpSession session = context.getRequest().getSession();
    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");

    if (accountBean == null || !accountBean.isAuthenticated()) {
      setMessage("Please sign on and try checking out again.");
      return new RedirectResolution(CatalogActionBean.class);
    } else if (accountBean.getAccount().getAuth() == 0) {
      setMessage("You are not Admin!!");
      return new RedirectResolution(CatalogActionBean.class);
    }else if (productId != null) {
      itemList = catalogService.getItemListByProduct(productId);
      product = catalogService.getProduct(productId);
    }
    return new ForwardResolution(VIEW_ADMIN_PRODUCT);
  }

  public Resolution addItemForm() {
    item = new Item();
    return new ForwardResolution(VIEW_ADD_ITEM_FORM);
  }

  public Resolution addItem() {
    item.setItemId(itemId);
    item.setProduct(product);
    catalogService.insertItem(item);
    itemList = catalogService.getItemListByProduct(productId);
    clearItem();
    return new ForwardResolution(VIEW_ADMIN_PRODUCT);
  }

  public Resolution updateItemForm() {
    item = catalogService.getItem(itemId);
    product = item.getProduct();
    System.out.println(item.getAttribute1());
    return new ForwardResolution(VIEW_EDIT_ITEM_FORM);
  }
  public Resolution updateItem() {
    catalogService.updateItem(item);
    itemList = catalogService.getItemListByProduct(productId);
    clearItem();
    //product = catalogService.getProduct(productId);
    return new ForwardResolution(VIEW_ADMIN_PRODUCT);
  }
  /**
   * View item.
   *
   * @return the forward resolution
   */
  public ForwardResolution viewItem() {
    item = catalogService.getItem(itemId);
    product = item.getProduct();
    return new ForwardResolution(VIEW_ITEM);
  }

  /**
   * Search products.
   *
   * @return the forward resolution
   */
  public ForwardResolution searchProducts() {
    if (keyword == null || keyword.length() < 1) {
      setMessage("Please enter a keyword to search for, then press the search button.");
      return new ForwardResolution(ERROR);
    } else {
      productList = catalogService.searchProductList(keyword.toLowerCase());
      return new ForwardResolution(SEARCH_PRODUCTS);
    }
  }

  /**
   * Clear.
   */
  public void clear() {
    keyword = null;

    categoryId = null;
    category = null;
    categoryList = null;

    productId = null;
    product = null;
    productList = null;

    itemId = null;
    item = null;
    itemList = null;
  }

  public void clearItem() {
    item = null;
    itemId = null;
  }

}
