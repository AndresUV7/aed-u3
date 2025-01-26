package services;

import models.InventoryOptimizationResult;
import models.Product;
import models.ProductSelection;
import java.util.*;

/**
 * Servicio que proporciona métodos para la optimización de inventarios.
 * Utiliza diferentes enfoques como recursivo, programación dinámica (Bottom-Up)
 * y memoización (Top-Down) para resolver el problema de optimización de productos
 * en función de la capacidad y el presupuesto disponible.
 */
public class InventoryOptimizationService {

    /**
     * Enfoque recursivo para la optimización del inventario.
     * Resuelve el problema utilizando recursión para incluir o excluir productos.
     * 
     * @param products Array de productos disponibles.
     * @param capacity Capacidad restante del inventario.
     * @param budget Presupuesto restante disponible.
     * @param index Índice del producto actual que se está evaluando.
     * @return El valor máximo obtenido con la optimización recursiva.
     */
    public double recursiveOptimization(Product[] products, double capacity, double budget, int index) {
        // Caso base: si ya no hay productos o los límites de capacidad o presupuesto se alcanzan
        if (index >= products.length || capacity <= 0 || budget <= 0) {
            return 0;
        }

        Product currentProduct = products[index];
        
        // Si el producto no se puede incluir por exceder capacidad o presupuesto
        if (currentProduct.getWeight() > capacity || currentProduct.getValue() > budget) {
            return recursiveOptimization(products, capacity, budget, index + 1);
        }

        // Opción 1: Incluir el producto
        double includeProduct = currentProduct.getValue() + 
            recursiveOptimization(
                products, 
                capacity - currentProduct.getWeight(), 
                budget - currentProduct.getValue(), 
                index + 1
            );
        
        // Opción 2: Excluir el producto
        double excludeProduct = recursiveOptimization(products, capacity, budget, index + 1);

        // Retornar el mejor valor entre incluir o excluir el producto
        return Math.max(includeProduct, excludeProduct);
    }

    /**
     * Enfoque Bottom-Up (Programación Dinámica) para la optimización del inventario.
     * Resuelve el problema iterando sobre todos los productos y capacidades posibles.
     * 
     * @param products Array de productos disponibles.
     * @param capacity Capacidad máxima del inventario.
     * @param budget Presupuesto disponible.
     * @return El resultado de la optimización, incluyendo el valor total y los productos seleccionados.
     */
    public InventoryOptimizationResult bottomUpOptimization(Product[] products, double capacity, double budget) {
        int n = products.length;
        double[][] dp = new double[n + 1][(int)capacity + 1];
        
        // Iteración para llenar la tabla DP
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                Product currentProduct = products[i-1];
                
                // Si el producto no cabe en la capacidad o excede el presupuesto
                if (currentProduct.getWeight() > w || currentProduct.getValue() > budget) {
                    dp[i][w] = dp[i-1][w];
                } else {
                    dp[i][w] = Math.max(
                        dp[i-1][w], 
                        currentProduct.getValue() + dp[i-1][(int)(w - currentProduct.getWeight())]
                    );
                }
            }
        }

        // Reconstrucción de la solución (productos seleccionados)
        List<ProductSelection> selectedProducts = new ArrayList<>();
        double remainingCapacity = capacity;
        double totalValue = dp[n][(int)capacity];
        
        for (int i = n; i > 0 && totalValue > 0; i--) {
            if (dp[i][(int)remainingCapacity] != dp[i-1][(int)remainingCapacity]) {
                Product selectedProduct = products[i-1];
                double quantity = 1.0;
                
                // Manejo de productos divisibles
                if (selectedProduct.isCanBeSplit() && selectedProduct.getWeight() > remainingCapacity) {
                    quantity = remainingCapacity / selectedProduct.getWeight();
                }
                
                selectedProducts.add(new ProductSelection(selectedProduct, quantity));
                totalValue -= selectedProduct.getValue() * quantity;
                remainingCapacity -= selectedProduct.getWeight() * quantity;
            }
        }

        return new InventoryOptimizationResult(dp[n][(int)capacity], selectedProducts, remainingCapacity);
    }

    /**
     * Enfoque Top-Down (Memoización) para la optimización del inventario.
     * Resuelve el problema usando memoización para almacenar resultados parciales y evitar cálculos repetidos.
     * 
     * @param products Array de productos disponibles.
     * @param capacity Capacidad máxima del inventario.
     * @param budget Presupuesto disponible.
     * @return El valor máximo obtenido con la optimización Top-Down.
     */
    public double topDownOptimization(Product[] products, double capacity, double budget) {
        double[][] memo = new double[products.length][(int)capacity + 1];
        return topDownHelper(products, capacity, budget, 0, memo);
    }

    /**
     * Función auxiliar para la optimización Top-Down con memoización.
     * 
     * @param products Array de productos disponibles.
     * @param capacity Capacidad máxima del inventario.
     * @param budget Presupuesto disponible.
     * @param index Índice del producto actual.
     * @param memo Tabla de memoización para almacenar resultados parciales.
     * @return El valor máximo de la optimización Top-Down.
     */
    private double topDownHelper(Product[] products, double capacity, double budget, 
                                 int index, double[][] memo) {
        // Caso base: si no hay más productos o se alcanza el límite de capacidad o presupuesto
        if (index >= products.length || capacity <= 0 || budget <= 0) {
            return 0;
        }

        // Verificar si ya se ha calculado el valor para este estado
        if (memo[index][(int)capacity] != 0) {
            return memo[index][(int)capacity];
        }

        Product currentProduct = products[index];
        
        // Si el producto no se puede incluir por exceder capacidad o presupuesto
        if (currentProduct.getWeight() > capacity || currentProduct.getValue() > budget) {
            return topDownHelper(products, capacity, budget, index + 1, memo);
        }

        // Calcular el valor máximo considerando incluir o excluir el producto
        double includeProduct = currentProduct.getValue() + 
            topDownHelper(
                products, 
                capacity - currentProduct.getWeight(), 
                budget - currentProduct.getValue(), 
                index + 1, 
                memo
            );
        
        double excludeProduct = topDownHelper(products, capacity, budget, index + 1, memo);

        // Almacenar el valor máximo en la tabla de memoización
        memo[index][(int)capacity] = Math.max(includeProduct, excludeProduct);
        return memo[index][(int)capacity];
    }
}
