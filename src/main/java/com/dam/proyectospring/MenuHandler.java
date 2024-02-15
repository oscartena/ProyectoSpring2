package com.dam.proyectospring;

import com.dam.proyectospring.modelos.Piloto;
import reactor.core.publisher.Mono;

import java.util.Scanner;

public class MenuHandler {

    private final PilotoClient pilotoClient;
    private final Scanner scanner;

    public MenuHandler(PilotoClient pilotoClient) {
        this.pilotoClient = pilotoClient;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("Menú de opciones:");
            System.out.println("1. Mostrar todos los pilotos");
            System.out.println("2. Mostrar piloto por ID");
            System.out.println("3. Crear nuevo piloto");
            System.out.println("4. Actualizar piloto");
            System.out.println("5. Borrar piloto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    mostrarTodosLosPilotos();
                    break;
                case 2:
                    mostrarPilotoPorId();
                    break;
                case 3:
                    crearNuevoPiloto();
                    break;
                case 4:
                    actualizarPiloto();
                    break;
                case 5:
                    borrarPiloto();
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }

    private void mostrarTodosLosPilotos() {
        pilotoClient.getAllPilotos().subscribe(
                piloto -> System.out.println(piloto),
                error -> System.out.println("Error al recuperar los pilotos: " + error.getMessage())
        );
    }

    private void mostrarPilotoPorId() {
        System.out.print("Ingrese el ID del piloto: ");
        Long id = scanner.nextLong();
        Mono<Piloto> pilotoMono = pilotoClient.getPilotoById(id);
        pilotoMono.subscribe(
                piloto -> System.out.println(piloto),
                error -> System.out.println("Error al recuperar el piloto: " + error.getMessage())
        );
    }

    private void crearNuevoPiloto() {
        Piloto nuevoPiloto = new Piloto();
        System.out.print("Nombre del piloto: ");
        String nombre = scanner.next();
        nuevoPiloto.setNombre(nombre);
        System.out.print("Abreviatura del piloto: ");
        String abreviatura = scanner.next();
        nuevoPiloto.setAbreviatura(abreviatura);
        System.out.print("Numero del piloto: ");
        int numero = scanner.nextInt();
        nuevoPiloto.setNumero(numero);
        System.out.print("Pais del piloto: ");
        String pais = scanner.next();
        nuevoPiloto.setPais(pais);

        Mono<Piloto> pilotoCreado = pilotoClient.addPiloto(nuevoPiloto);
        pilotoCreado.subscribe(
                piloto -> System.out.println("Piloto creado: " + piloto),
                error -> System.out.println("Error al crear el piloto: " + error.getMessage())
        );
    }

    private void actualizarPiloto() {
        System.out.print("ID del piloto a actualizar: ");
        Long id = scanner.nextLong();

        Piloto pilotoActualizado = new Piloto();
        System.out.print("Nuevo nombre del piloto: ");
        pilotoActualizado.setNombre(scanner.next());
        System.out.println("Nueva abreviatura del piloto: ");
        pilotoActualizado.setAbreviatura(scanner.next());
        System.out.println("Nuevo numero del piloto: ");
        pilotoActualizado.setNumero(scanner.nextInt());
        System.out.println("Nuevo pais del piloto: ");
        pilotoActualizado.setPais(scanner.next());


        Mono<Piloto> pilotoActualizadoMono = pilotoClient.modifyPiloto(id, pilotoActualizado);
        pilotoActualizadoMono.subscribe(
                piloto -> System.out.println("Piloto actualizado: " + piloto),
                error -> System.out.println("Error al actualizar el piloto: " + error.getMessage())
        );
    }

    private void borrarPiloto() {
        System.out.print("Ingrese el ID del piloto a borrar: ");
        Long id = scanner.nextLong();
        Mono<Void> pilotoBorrado = pilotoClient.deletePiloto(id);
        pilotoBorrado.subscribe(
                nothing -> System.out.println("Piloto borrado"),
                error -> System.out.println("Error al borrar el piloto: " + error.getMessage())
        );
    }
}

