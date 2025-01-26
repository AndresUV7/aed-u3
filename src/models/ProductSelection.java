package models;

/**
 * Clase que representa la selección de un producto en el inventario.
 * Contiene un producto y su cantidad seleccionada.
 */
public class ProductSelection {

    private Product product; // El producto seleccionado
    private double quantity; // La cantidad del producto seleccionado

    /**
     * Constructor que inicializa los atributos de la selección de producto.
     * 
     * @param product El producto seleccionado.
     * @param quantity La cantidad del producto seleccionado.
     */
    public ProductSelection(Product product, double quantity) {
        this.product = product; // Inicializa el producto
        this.quantity = quantity; // Inicializa la cantidad
    }

    /**
     * Obtiene el producto seleccionado.
     * 
     * @return El producto seleccionado.
     */
    public Product getProduct() { 
        return product; 
    }

    /**
     * Obtiene la cantidad del producto seleccionado.
     * 
     * @return La cantidad del producto seleccionado.
     */
    public double getQuantity() { 
        return quantity; 
    }
}
