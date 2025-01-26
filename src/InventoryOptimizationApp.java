import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import controllers.InventoryOptimizationController;
import models.Product;

/**
 * Aplicación principal para la optimización de inventario con entrada de usuario.
 * Permite al usuario ingresar los productos, la capacidad del inventario y el presupuesto.
 */
public class InventoryOptimizationApp {

    /**
     * Método principal que ejecuta la optimización de inventario con entrada de usuario.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        // Instancia de escaner para entrada de datos usando punto decimal (Por ej. 1.5)
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        ArrayList<Product> products = new ArrayList<>();

        // Solicitar entrada de productos
        while (true) {
            System.out.println("Ingrese un nuevo producto (o 'fin' para terminar):");
            
            System.out.print("Nombre del producto: ");
            String name = scanner.nextLine();
            
            // Condición de salida
            if (name.equalsIgnoreCase("fin")) {
                break;
            }

            // Validación de valor del producto
            double value;
            while (true) {
                System.out.print("Valor del producto: ");
                try {
                    value = scanner.nextDouble();
                    if (value < 0) {
                        System.out.println("El valor debe ser un número no negativo.");
                        scanner.nextLine(); // Limpiar buffer
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Por favor, ingrese un número entero válido.");
                    scanner.nextLine(); // Limpiar buffer
                }
            }

            // Validación de peso del producto
            double weight;
            while (true) {
                System.out.print("Peso del producto: ");
                try {
                    weight = scanner.nextDouble();
                    if (weight <= 0) {
                        System.out.println("El peso debe ser un número positivo.");
                        scanner.nextLine(); // Limpiar buffer
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Por favor, ingrese un número entero válido.");
                    scanner.nextLine(); // Limpiar buffer
                }
            }

            // Validación de divisibilidad del producto
            boolean divisible;
            while (true) {
                System.out.print("¿El producto es divisible? (SI/NO): ");
                String divResponse = scanner.next();
                if (divResponse.equalsIgnoreCase("SI") || divResponse.equalsIgnoreCase("NO")) {
                    divisible = divResponse.equalsIgnoreCase("SI");
                    break;
                } else {
                    System.out.println("Por favor, responda con 'SI' o 'NO'.");
                }
            }
            scanner.nextLine(); // Limpiar buffer después de nextInt/next

            // Agregar producto a la lista
            products.add(new Product(name, value, weight, divisible));
            System.out.println("======================================");
            System.out.println("Producto agregado correctamente.");
            System.out.println("======================================");
        }

        // Validación de capacidad de inventario
        double capacity;
        while (true) {
            System.out.print("Ingrese la capacidad máxima de inventario: ");
            try {
                capacity = scanner.nextDouble();
                if (capacity <= 0) {
                    System.out.println("La capacidad debe ser un número positivo.");
                    scanner.nextLine(); // Limpiar buffer
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Por favor, ingrese un número decimal válido.");
                scanner.nextLine(); // Limpiar buffer
            }
        }

        // Validación de presupuesto
        double budget;
        while (true) {
            System.out.print("Ingrese el presupuesto disponible: ");
            try {
                budget = scanner.nextDouble();
                if (budget < 0) {
                    System.out.println("El presupuesto no puede ser negativo.");
                    scanner.nextLine(); // Limpiar buffer
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Por favor, ingrese un número decimal válido.");
                scanner.nextLine(); // Limpiar buffer
            }
        }

        // Crear una instancia del controlador
        InventoryOptimizationController controller = new InventoryOptimizationController();
        
        // Convertir ArrayList a array para compatibilidad
        Product[] productArray = products.toArray(new Product[0]);
        
        // Llamar al método para realizar el análisis de optimización
        controller.performOptimizationAnalysis(productArray, capacity, budget);

        // Cerrar el scanner
        scanner.close();
    }
}