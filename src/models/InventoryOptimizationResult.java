package models;

import java.util.List;

/**
 * Clase que representa el resultado de la optimización del inventario.
 * Contiene el valor total de los productos seleccionados, la lista de productos seleccionados y la capacidad restante.
 */
public class InventoryOptimizationResult {

    private double totalValue; // Valor total de los productos seleccionados
    private List<ProductSelection> selectedProducts; // Lista de productos seleccionados
    private double remainingCapacity; // Capacidad restante en el inventario

    /**
     * Constructor que inicializa los atributos del resultado de la optimización del inventario.
     * 
     * @param totalValue El valor total de los productos seleccionados.
     * @param selectedProducts La lista de productos seleccionados.
     * @param remainingCapacity La capacidad restante en el inventario.
     */
    public InventoryOptimizationResult(double totalValue, List<ProductSelection> selectedProducts, double remainingCapacity) {
        this.totalValue = totalValue; // Inicializa el valor total
        this.selectedProducts = selectedProducts; // Inicializa la lista de productos seleccionados
        this.remainingCapacity = remainingCapacity; // Inicializa la capacidad restante
    }

    /**
     * Obtiene el valor total de los productos seleccionados.
     * 
     * @return El valor total de los productos seleccionados.
     */
    public double getTotalValue() { 
        return totalValue; 
    }

    /**
     * Obtiene la lista de productos seleccionados.
     * 
     * @return La lista de productos seleccionados.
     */
    public List<ProductSelection> getSelectedProducts() { 
        return selectedProducts; 
    }

    /**
     * Obtiene la capacidad restante en el inventario.
     * 
     * @return La capacidad restante en el inventario.
     */
    public double getRemainingCapacity() { 
        return remainingCapacity; 
    }
}
