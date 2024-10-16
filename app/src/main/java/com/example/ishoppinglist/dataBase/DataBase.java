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
            productList.add(new Product(1, "Smartphone X200", "Modelo con pantalla OLED", true, true, false));
            productList.add(new Product(2, "Laptop Pro 15", "Procesador Intel i7 de 10ª generación", true, false, true));
            productList.add(new Product(3, "Auriculares Wave", "Cancelación activa de ruido", false, true, true));
            productList.add(new Product(4, "Reloj Fitness Tracker", "Monitoreo de ritmo cardíaco", true, false, false));
            productList.add(new Product(5, "Cámara ProShot", "Resolución 4K con zoom óptico", false, false, true));
            productList.add(new Product(6, "Tablet Maxi", "Pantalla de 12 pulgadas, ideal para productividad", true, true, true));
            productList.add(new Product(7, "Teclado Mecánico Pro", "Teclas retroiluminadas con switches azules", true, true, false));
            productList.add(new Product(8, "Monitor UltraWide", "Pantalla curva de 34 pulgadas", true, false, true));
            productList.add(new Product(9, "Impresora ColorJet", "Impresión rápida en color y blanco y negro", false, true, false));
            productList.add(new Product(10, "Router MaxSpeed", "WiFi 6 con amplio rango de cobertura", true, true, false));
            productList.add(new Product(11, "Disco SSD 1TB", "Almacenamiento rápido y silencioso", true, false, false));
            productList.add(new Product(12, "Altavoz Bluetooth", "Sonido envolvente con graves potentes", false, false, false));
            productList.add(new Product(13, "Mouse Inalámbrico Ergo", "Diseño ergonómico para largas horas de uso", true, true, true));
            productList.add(new Product(14, "Microondas FastHeat", "Tecnología de calentamiento rápido", false, true, false));
            productList.add(new Product(15, "Refrigerador EcoCool", "Ahorro de energía con sistema de enfriamiento rápido", true, true, true));
            productList.add(new Product(16, "Cafetera Express", "Café espresso con sistema de auto-limpieza", false, true, false));
            productList.add(new Product(17, "Smart TV 55 UltraHD", "Resolución 4K con soporte HDR", true, false, true));
            productList.add(new Product(18, "Consola de Videojuegos NX", "Con gráficos de última generación", true, true, false));
            productList.add(new Product(19, "Lámpara LED Recargable", "Batería de larga duración y luz ajustable", true, true, false));
            productList.add(new Product(20, "Ventilador Turbo", "Potente ventilador con control remoto", false, true, true));
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
     *
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

    /**
     * Método para eliminar un producto de la lista de productos
     *
     * @return - Objeto Product
     */
    public static ArrayList<Product> getProductLactosa() {
        ArrayList<Product> productLactosa = new ArrayList<>();
        for (Product product : productList) {
            if (!product.isLactosa()) {
                productLactosa.add(product);
            }
        }
        return productLactosa;
    }

    /**
     * Método para eliminar un producto de la lista de productos
     *
     * @return
     */
    public static ArrayList<Product> getProductGluten() {
        ArrayList<Product> productGluten = new ArrayList<>();
        for (Product product : productList) {
            if (!product.isGluten()) {
                productGluten.add(product);
            }

        }
        return productGluten;
    }
}