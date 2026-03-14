package com.klu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ProductCRUD {

    static String url = "jdbc:mysql://localhost:3306/product";
    static String user = "root";
    static String password = "root";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    	createTableIfNotExists();

        int choice;

        do {
            System.out.println("\n----- PRODUCT CRUD MENU -----");
            System.out.println("1. Insert Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Price");
            System.out.println("4. Update Quantity");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    updatePrice();
                    break;
                case 4:
                    updateQuantity();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }
    static void createTableIfNotExists() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "CREATE TABLE IF NOT EXISTS product (" +
                         "product_id INT PRIMARY KEY," +
                         "product_name VARCHAR(50)," +
                         "product_descript VARCHAR(100)," +
                         "price DOUBLE," +
                         "product_quantity INT)";
            con.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // INSERT
    static void insertProduct() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String sql = "INSERT INTO product VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Product ID: ");
            ps.setInt(1, sc.nextInt());

            System.out.print("Enter Product Name: ");
            ps.setString(2, sc.next());

            System.out.print("Enter Description: ");
            ps.setString(3, sc.next());

            System.out.print("Enter Price: ");
            ps.setDouble(4, sc.nextDouble());

            System.out.print("Enter Quantity: ");
            ps.setInt(5, sc.nextInt());

            ps.executeUpdate();
            System.out.println("✅ Product Inserted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ
    static void viewProducts() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String sql = "SELECT * FROM product";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nID  Name  Description  Price  Quantity");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("product_id") + "  " +
                        rs.getString("product_name") + "  " +
                        rs.getString("product_descript") + "  " +
                        rs.getDouble("price") + "  " +
                        rs.getInt("product_quantity")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE PRICE
    static void updatePrice() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String sql = "UPDATE product SET price = ? WHERE product_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter New Price: ");
            ps.setDouble(1, sc.nextDouble());

            System.out.print("Enter Product ID: ");
            ps.setInt(2, sc.nextInt());

            ps.executeUpdate();
            System.out.println("✅ Price Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE QUANTITY
    static void updateQuantity() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String sql = "UPDATE product SET product_quantity = ? WHERE product_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter New Quantity: ");
            ps.setInt(1, sc.nextInt());

            System.out.print("Enter Product ID: ");
            ps.setInt(2, sc.nextInt());

            ps.executeUpdate();
            System.out.println("✅ Quantity Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    static void deleteProduct() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String sql = "DELETE FROM product WHERE product_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Product ID: ");
            ps.setInt(1, sc.nextInt());

            ps.executeUpdate();
            System.out.println("✅ Product Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

