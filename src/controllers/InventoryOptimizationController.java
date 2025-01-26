package controllers;

import models.Product;
import models.InventoryOptimizationResult;
import services.InventoryOptimizationService;
import views.InventoryOptimizationView;

/**
 * Controlador para gestionar el análisis de optimización de inventario.
 * Este controlador utiliza los servicios de optimización para realizar análisis de rendimiento y mostrar los resultados de la optimización.
 */
public class InventoryOptimizationController {

    private InventoryOptimizationService service; // Servicio de optimización
    private InventoryOptimizationView view; // Vista para mostrar los resultados

    /**
     * Constructor del controlador.
     * Inicializa el servicio y la vista necesarios para realizar la optimización.
     */
    public InventoryOptimizationController() {
        this.service = new InventoryOptimizationService(); // Inicializa el servicio de optimización
        this.view = new InventoryOptimizationView(); // Inicializa la vista de optimización
    }

    /**
     * Realiza un análisis de optimización sobre los productos dados, la capacidad y el presupuesto.
     * Calcula el rendimiento de tres enfoques: recursivo, Bottom-Up (Programación Dinámica) y Top-Down (Memoización).
     * Luego, muestra el análisis de rendimiento y los resultados de la optimización.
     * 
     * @param products Array de productos disponibles para la optimización.
     * @param capacity Capacidad máxima disponible en el inventario.
     * @param budget Presupuesto disponible para la compra de productos.
     */
    public void performOptimizationAnalysis(Product[] products, double capacity, double budget) {
        // Medición de rendimiento
        long startTime, endTime;

        // Enfoque Recursivo
        startTime = System.nanoTime();
        double recursiveResult = service.recursiveOptimization(products, capacity, budget, 0);
        endTime = System.nanoTime();
        long recursiveTime = endTime - startTime;

        // Enfoque Bottom-Up (Programación Dinámica)
        startTime = System.nanoTime();
        InventoryOptimizationResult bottomUpResult = service.bottomUpOptimization(products, capacity, budget);
        endTime = System.nanoTime();
        long bottomUpTime = endTime - startTime;

        // Enfoque Top-Down (Memoización)
        startTime = System.nanoTime();
        double topDownResult = service.topDownOptimization(products, capacity, budget);
        endTime = System.nanoTime();
        long topDownTime = endTime - startTime;

        // Mostrar análisis de rendimiento
        view.displayPerformanceAnalysis(
            recursiveResult, recursiveTime,
            bottomUpResult, bottomUpTime,
            topDownResult, topDownTime
        );

        // Mostrar los resultados de la optimización
        view.displayOptimizationResults(bottomUpResult);
    }
}
