package com.example;

import java.util.*;
import org.hibernate.*;
import org.hibernate.query.Query;

import com.example.entity.Product;
import com.example.util.HibernateUtil;

public class MainApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int ch;
        do {
            System.out.println("\n=== PRODUCT HQL MENU ===");
            System.out.println("1. Insert Sample Products");
            System.out.println("2. Show All Products");
            System.out.println("3. Sort by Price (ASC)");
            System.out.println("4. Sort by Price (DESC)");
            System.out.println("5. Sort by Quantity (High to Low)");
            System.out.println("6. Pagination");
            System.out.println("7. Aggregate Functions");
            System.out.println("8. Filter by Price Range");
            System.out.println("9. LIKE Operations");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1: insertProducts(); break;
                case 2: showProducts(); break;
                case 3: sortPriceAsc(); break;
                case 4: sortPriceDesc(); break;
                case 5: sortByQuantity(); break;
                case 6: pagination(); break;
                case 7: aggregate(); break;
                case 8: priceRange(); break;
                case 9: likeQueries(); break;
            }
        } while (ch != 0);
    }

    // 1️⃣ INSERT SAMPLE DATA
    static void insertProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(new Product("Laptop", "Electronics", 55000.0, 10));
        session.persist(new Product("Mouse", "Electronics", 500.0, 50));
        session.persist(new Product("Keyboard", "Electronics", 1500.0, 30));
        session.persist(new Product("Chair", "Furniture", 3000.0, 15));
        session.persist(new Product("Table", "Furniture", 7000.0, 5));
        session.persist(new Product("Book", "Education", 400.0, 100));
        tx.commit();
        session.close();
        System.out.println("Sample products inserted!");
    }

    // 2️⃣ SELECT ALL
    static void showProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery("from Product", Product.class);

        System.out.println("\nID | NAME | PRICE | QTY | DESC");
        for (Product p : q.list()) {
            System.out.println(p.getProductId() + " | " + p.getName() + " | " +
                               p.getPrice() + " | " + p.getQuantity() + " | " +
                               p.getDescription());
        }
        session.close();
    }

    // 3️⃣ SORT PRICE ASC
    static void sortPriceAsc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q =
            session.createQuery("from Product order by price asc", Product.class);

        for (Product p : q.list())
            System.out.println(p.getName() + " -> " + p.getPrice());

        session.close();
    }

    // 4️⃣ SORT PRICE DESC
    static void sortPriceDesc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q =
            session.createQuery("from Product order by price desc", Product.class);

        for (Product p : q.list())
            System.out.println(p.getName() + " -> " + p.getPrice());

        session.close();
    }

    // 5️⃣ SORT BY QUANTITY
    static void sortByQuantity() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q =
            session.createQuery("from Product order by quantity desc", Product.class);

        for (Product p : q.list())
            System.out.println(p.getName() + " -> Qty: " + p.getQuantity());

        session.close();
    }

    // 6️⃣ PAGINATION
    static void pagination() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Product> q = session.createQuery("from Product", Product.class);

        q.setFirstResult(0);
        q.setMaxResults(3);
        System.out.println("\n--- First 3 Products ---");
        for (Product p : q.list())
            System.out.println(p.getName());

        q.setFirstResult(3);
        q.setMaxResults(3);
        System.out.println("\n--- Next 3 Products ---");
        for (Product p : q.list())
            System.out.println(p.getName());

        session.close();
    }

    // 7️⃣ AGGREGATE FUNCTIONS
    static void aggregate() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query countAll = session.createQuery("select count(*) from Product");
        Query countAvail =
            session.createQuery("select count(*) from Product where quantity > 0");
        Query minPrice = session.createQuery("select min(price) from Product");
        Query maxPrice = session.createQuery("select max(price) from Product");

        System.out.println("Total Products: " + countAll.uniqueResult());
        System.out.println("Available Products: " + countAvail.uniqueResult());
        System.out.println("Min Price: " + minPrice.uniqueResult());
        System.out.println("Max Price: " + maxPrice.uniqueResult());

        session.close();
    }

    // 8️⃣ WHERE – PRICE RANGE
    static void priceRange() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q =
            session.createQuery(
                "from Product where price between :min and :max",
                Product.class);

        q.setParameter("min", 1000.0);
        q.setParameter("max", 10000.0);

        for (Product p : q.list())
            System.out.println(p.getName() + " -> " + p.getPrice());

        session.close();
    }

    // 9️⃣ LIKE QUERIES
    static void likeQueries() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q1 =
            session.createQuery("from Product where name like 'L%'", Product.class);
        System.out.println("\nNames starting with L:");
        for (Product p : q1.list())
            System.out.println(p.getName());

        Query<Product> q2 =
            session.createQuery("from Product where name like '%k'", Product.class);
        System.out.println("\nNames ending with k:");
        for (Product p : q2.list())
            System.out.println(p.getName());

        Query<Product> q3 =
            session.createQuery("from Product where name like '%top%'", Product.class);
        System.out.println("\nNames containing 'top':");
        for (Product p : q3.list())
            System.out.println(p.getName());

        Query<Product> q4 =
            session.createQuery("from Product where length(name)=4", Product.class);
        System.out.println("\nNames with 4 characters:");
        for (Product p : q4.list())
            System.out.println(p.getName());

        session.close();
    }
}

