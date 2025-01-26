package models;

/**
 * Clase que representa un producto.
 * Un producto tiene un nombre, valor, peso y la capacidad de ser dividido.
 */
public class Product {
    
    private String name; // Nombre del producto
    private double value; // Valor del producto
    private double weight; // Peso del producto
    private boolean canBeSplit; // Indica si el producto puede ser dividido

    /**
     * Constructor que inicializa los atributos del producto.
     * 
     * @param name El nombre del producto.
     * @param value El valor del producto.
     * @param weight El peso del producto.
     * @param canBeSplit Indica si el producto puede ser dividido.
     */
    public Product(String name, double value, double weight, boolean canBeSplit) {
        this.name = name; // Inicializa el nombre del producto
        this.value = value; // Inicializa el valor del producto
        this.weight = weight; // Inicializa el peso del producto
        this.canBeSplit = canBeSplit; // Inicializa si el producto puede ser dividido
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getName() { 
        return name; 
    }

    /**
     * Obtiene el valor del producto.
     * 
     * @return El valor del producto.
     */
    public double getValue() { 
        return value; 
    }

    /**
     * Obtiene el peso del producto.
     * 
     * @return El peso del producto.
     */
    public double getWeight() { 
        return weight; 
    }

    /**
     * Verifica si el producto puede ser dividido.
     * 
     * @return true si el producto puede ser dividido, false en caso contrario.
     */
    public boolean isCanBeSplit() { 
        return canBeSplit; 
    }
}
