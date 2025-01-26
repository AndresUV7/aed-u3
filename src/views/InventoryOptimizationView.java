package views;

import models.InventoryOptimizationResult;

/**
 * Vista que muestra los resultados de la optimización del inventario y el análisis de rendimiento.
 * Esta clase es responsable de presentar los resultados generados por los enfoques de optimización.
 */
public class InventoryOptimizationView {

    /**
     * Muestra el análisis de rendimiento de las distintas técnicas de optimización (Recursivo, Bottom-Up, Top-Down).
     * Imprime los resultados obtenidos junto con el tiempo que tardaron en ejecutarse cada uno de los enfoques.
     * 
     * @param recursiveResult Resultado obtenido por el enfoque recursivo.
     * @param recursiveTime Tiempo que tardó el enfoque recursivo en ejecutarse (en nanosegundos).
     * @param bottomUpResult Resultado obtenido por el enfoque Bottom-Up (Programación Dinámica).
     * @param bottomUpTime Tiempo que tardó el enfoque Bottom-Up en ejecutarse (en nanosegundos).
     * @param topDownResult Resultado obtenido por el enfoque Top-Down (Memoización).
     * @param topDownTime Tiempo que tardó el enfoque Top-Down en ejecutarse (en nanosegundos).
     */
    public void displayPerformanceAnalysis(
        double recursiveResult, long recursiveTime,
        InventoryOptimizationResult bottomUpResult, long bottomUpTime,
        double topDownResult, long topDownTime
    ) {
        System.out.println("Análisis de Rendimiento de Optimización de Inventario");
        System.out.println("---------------------------------------------------");
        
        // Muestra los resultados del análisis de rendimiento
        System.out.printf("Recursivo: Valor = %.2f, Tiempo = %d ns%n", recursiveResult, recursiveTime);
        System.out.printf("Bottom-Up: Valor = %.2f, Tiempo = %d ns%n", bottomUpResult.getTotalValue(), bottomUpTime);
        System.out.printf("Top-Down: Valor = %.2f, Tiempo = %d ns%n%n", topDownResult, topDownTime);
    }

    /**
     * Muestra los resultados de la optimización de inventario, incluyendo el valor total, la capacidad restante y los productos seleccionados.
     * 
     * @param result El resultado de la optimización, que incluye el valor total, los productos seleccionados y la capacidad restante.
     */
    public void displayOptimizationResults(InventoryOptimizationResult result) {
        System.out.println("Resultados de Optimización de Inventario");
        System.out.println("--------------------------------------");
        
        // Muestra el valor total y la capacidad restante
        System.out.printf("Valor Total: %.2f%n", result.getTotalValue());
        System.out.printf("Capacidad Restante: %.2f%n", result.getRemainingCapacity());
        System.out.println("Productos Seleccionados:");
        
        // Muestra los productos seleccionados y su cantidad
        result.getSelectedProducts().forEach(selection -> 
            System.out.printf("- %s (Cantidad: %.2f, Valor: %.2f)%n", 
                selection.getProduct().getName(), 
                selection.getQuantity(), 
                selection.getProduct().getValue() * selection.getQuantity())
        );
    }
}
