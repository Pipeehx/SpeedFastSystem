# SpeedFastSystem - Sistema de Gestión de Envíos

## Descripción
SpeedFastSystem es una aplicación orientada a objetos desarrollada en Java para la gestión integral de despachos (comidas, encomiendas y pedidos express). El sistema optimiza la asignación de repartidores, el cálculo dinámico de tiempos de entrega y el control de historiales mediante buenas prácticas de desarrollo de software.

## Estructura del Proyecto
El proyecto está estructurado de manera modular utilizando paquetes para separar las responsabilidades:
- **`interfaces`**: Contiene los contratos funcionales del sistema (`Despachable`, `Cancelable`, `Rastreable`).
- **`modelo`**: Contiene la clase abstracta base `Pedido` y sus clases derivadas especializadas (`PedidoComida`, `PedidoEncomienda`, `PedidoExpress`), aplicando atributos propios y polimorfismo.
- **`principal`**: Contiene la clase `Main` encargada de inicializar la simulación del sistema.

## Conceptos Aplicados
- **Herencia y Clases Abstractas**: Reutilización de atributos y métodos comunes en la clase base `Pedido`.
- **Polimorfismo**:
    - *Sobrescritura (@Override)* en los métodos `calcularTiempoEntrega()`, `asignarRepartidor()` y `mostrarResumen()`.
    - *Sobrecarga de métodos* en `asignarRepartidor(String nombre)` para asignación manual.
- **Interfaces**: Desacoplamiento de operaciones funcionales de despacho, cancelación y control de historial.

## Instrucciones de Ejecución
1. Clona o descarga este repositorio en tu equipo.
2. Abre el proyecto en **IntelliJ IDEA**.
3. Asegúrate de configurar correctamente el JDK (OpenJDK recomendado).
4. Ejecuta la clase principal ubicada en `principal.Main`.