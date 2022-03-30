package Queries;

public class ProductQueries {
    public static final String saveProductQueries = "INSERT INTO product(productId,productName,unitPrice,available,addDate,updateDate,categoryId,brandId) VALUES(?,?,?,?,?,?,?,?)";

    public static final String updateProductQueries = "UPDATE product SET productName =?,unitPrice =?,available =?,updateDate=?,categoryId =?,brandId =? WHERE productId =?";

    public static final String deleteUser_productQueries = "DELETE FROM user_product WHERE productId=?";
    public static final String deleteProductQueries = "DELETE FROM product WHERE productId=?";

    public static final String findProductByIdQueries = "SELECT * FROM product p LEFT JOIN category c on(p.categoryId = c.categoryId) LEFT JOIN brand b on(p.brandId = b.brandId) WHERE productId =?";
    public static final String findProductsQueries = "SELECT * FROM product p LEFT JOIN category c on(p.categoryId = c.categoryId) LEFT JOIN brand b on(p.brandId = b.brandId)";

}
