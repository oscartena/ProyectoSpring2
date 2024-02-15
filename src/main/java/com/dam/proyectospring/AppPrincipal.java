package com.dam.proyectospring;

public class AppPrincipal {
    public static void main(String[] args) {
        PilotoClient pilotoClient = new PilotoClient("http://localhost:8080");
        MenuHandler menuHandler = new MenuHandler(pilotoClient);
        menuHandler.mostrarMenu();
    }
}
