package Queries;

public class UserQueries {

    public static final String saveUserQueries = "INSERT INTO user(userId,firstName,lastName,birthOfDate,userName) VALUES(?,?,?,?,?)";

    public static final String saveUser_ProductQueries = "INSERT INTO user_product(userId,productId) VALUES(?,?)";

    public static final String updateUserQueries = "UPDATE user SET firstName =?,lastName =?,birthOfDate =?,userName =? WHERE userId =?";

    public static final String deleteUser_ProductQueries ="DELETE FROM user_product WHERE userId =?";

    public static final String deleteUserByIdQueries = "DELETE FROM user WHERE userId =?";

    public static final String findUserByIdQueries = "SELECT * FROM user WHERE userId";

    public static final String findUsersQueries = "SELECT * FROM user";

    public static final String findUserProductQueries =
            "SELECT * FROM user u LEFT OUTER JOIN user_product up ON(u.userId = up.userId) " +
            "LEFT JOIN product p ON(up.productId = p.productId) " +
                    "LEFT JOIN category c ON(p.categoryId = c.categoryId) " +
                    "LEFT JOIN brand b ON(p.breadId = b.breadId) " +
                    "WHERE u.userId =?";

}
