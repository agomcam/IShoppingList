package com.example.ishoppinglist.dataBase;

import android.view.View;
import android.widget.Toast;

import com.example.ishoppinglist.activities.AddNewProductActivity;
import com.example.ishoppinglist.models.Product;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static List<Product> productList;

    public static void inicializeList() {
        if (productList == null) {
            productList = new ArrayList<>();
            // Añadiendo 20 productos inventados al ArrayList
            productList.add(new Product(1, "Smartphone X200", "Modelo con pantalla OLED", true));
            productList.add(new Product(2, "Laptop Pro 15", "Procesador Intel i7 de 10ª generación", true));
            productList.add(new Product(3, "Auriculares Wave", "Cancelación activa de ruido", false));
            productList.add(new Product(4, "Reloj Fitness Tracker", "Monitoreo de ritmo cardíaco", true));
            productList.add(new Product(5, "Cámara ProShot", "Resolución 4K con zoom óptico", false));
            productList.add(new Product(6, "Tablet Maxi", "Pantalla de 12 pulgadas, ideal para productividad", true));
            productList.add(new Product(7, "Teclado Mecánico Pro", "Teclas retroiluminadas con switches azules", true));
            productList.add(new Product(8, "Monitor UltraWide", "Pantalla curva de 34 pulgadas", true));
            productList.add(new Product(9, "Impresora ColorJet", "Impresión rápida en color y blanco y negro", false));
            productList.add(new Product(10, "Router MaxSpeed", "WiFi 6 con amplio rango de cobertura", true));
            productList.add(new Product(11, "Disco SSD 1TB", "Almacenamiento rápido y silencioso", true));
            productList.add(new Product(12, "Altavoz Bluetooth", "Sonido envolvente con graves potentes", false));
            productList.add(new Product(13, "Mouse Inalámbrico Ergo", "Diseño ergonómico para largas horas de uso", true));
            productList.add(new Product(14, "Microondas FastHeat", "Tecnología de calentamiento rápido", false));
            productList.add(new Product(15, "Refrigerador EcoCool", "Ahorro de energía con sistema de enfriamiento rápido", true));
            productList.add(new Product(16, "Cafetera Express", "Café espresso con sistema de auto-limpieza", false));
            productList.add(new Product(17, "Smart TV 55 UltraHD", "Resolución 4K con soporte HDR", true));
            productList.add(new Product(18, "Consola de Videojuegos NX", "Con gráficos de última generación", true));
            productList.add(new Product(19, "Lámpara LED Recargable", "Batería de larga duración y luz ajustable", true));
            productList.add(new Product(20, "Ventilador Turbo", "Potente ventilador con control remoto", false));
        }
    }

    /**
     * Método para obtener la lista de productos pendientes de compra
     *
     * @return - Lista de productos pendientes de compra
     */
    public static ArrayList<Product> getProductListPending() {
        ArrayList<Product> productsPending = new ArrayList<>();
        for (Product product : productList) {
            if (product.isState()) {
                productsPending.add(product);
            }
        }
        return productsPending;
    }

    /**
     * Método para obtener la lista de productos no pendientes de compra
     *
     * @return - Lista de productos no pendientes de compra
     */
    public static ArrayList<Product> getProductListNotPending() {
        ArrayList<Product> productsNotPending = new ArrayList<>();
        for (Product product : productList) {
            if (!product.isState()) {
                productsNotPending.add(product);
            }
        }
        return productsNotPending;
    }

    /**
     * Método para obtener un producto por su id
     *
     * @param id - Id del producto
     * @return - Objeto Product
     */
    public static Product getProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return new Product();
    }

    /**
     * Método para obtener el último id de la lista de productos
     * @return
     */
    public static int getLastIdByProductList() {
        int id = 1;
        for (Product product : productList) {
            if (product.getId() == id) {
                id++;
            } else {
                return id;
            }
        }
        return id;
    }

    /**
     * Funcion que sirve para añadir un producto a la lista de productos
     *
     * @param product - Producto que vamos a añadir
     * @param view    - Vista actual
     */
    public static void addProduct(Product product, AddNewProductActivity view) {

        for (Product p : productList) {
            if (p.getId() == product.getId()) {
                Toast toas = new Toast(view);
                toas.setText("Ya existe un producto con ese id");
                toas.show();
                return;
            }
            if (product.getName().equalsIgnoreCase(p.getName())) {
                Toast toas = new Toast(view);
                toas.setText("Ya existe un producto con ese nombre");
                toas.show();
                return;
            }
        }

        Toast toast = new Toast(view);
        toast.setText("Producto añadido correctamente");
        toast.show();
        productList.add(product);
    }
}
