package gm.Ajedrez;

import gm.Ajedrez.modelo.Deportista;
import gm.Ajedrez.servicio.IDeportistaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AjedrezApplication implements CommandLineRunner {

	@Autowired
	private IDeportistaServicio deportistaServicio;

	private static final Logger logger = LoggerFactory.getLogger(AjedrezApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la Aplicación");
		SpringApplication.run(AjedrezApplication.class, args);
		logger.info("Cerrando la Aplicación");
	}

	@Override
	public void run(String... args) throws Exception {
		ajedrezApp();
	}

	private void ajedrezApp(){
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			try{
				var opcion = mostrarMenu(consola);
				salir = ejecutarOpcion(opcion, consola);
			}catch(Exception e){
				logger.info("Error al ingresar una opción: " + e.getMessage());
			}
		}
	}

	private int mostrarMenu(Scanner consola){
		logger.info("""
				\n*** Ajedrez
				1. Listar Deportistas
				2. Buscar Deportista
				3. Agregar Deportista
				4. Modificar Deportista
				5. Elimianr Deportista
				6. Salir
				Elige una opción:\s""");
		return Integer.parseInt(consola.nextLine());
	}

	private boolean ejecutarOpcion(int opcion, Scanner consola){
		var salir = false;
		switch(opcion){
			case 1 -> {
				logger.info(nl + "--- Listado de Deportistas ---" + nl);
				var deportistas = deportistaServicio.listarDeportista();
				deportistas.forEach(deportista -> logger.info(deportista.toString()));
			}
			case 2 -> {
				logger.info(nl + "--- Buscar Deportista ---" + nl);
				logger.info("Id del deportista: ");
				var idDeportista = Integer.parseInt(consola.nextLine());
				var deportista = deportistaServicio.buscarDeportistaPorID(idDeportista);
				if(deportista != null){
					logger.info("Deportista encontrado : " + deportista + nl);
				}
				else{
					logger.info("Deportista No encontrado: " + deportista + nl);
				}
			}
			case 3 -> {
				logger.info(nl + "--- Agregar Deportista ---" + nl);
				logger.info("Nombre: ");
				var nombre = consola.nextLine();
				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				logger.info("Edad: ");
				var edad = Integer.parseInt(consola.nextLine());
				var deportista = new Deportista();
				deportista.setNombre(nombre);
				deportista.setApellido(apellido);
				deportista.setEdad(edad);
				deportistaServicio.guardarDeportista(deportista);
				logger.info("Deportista agregado: " + deportista + nl);
			}
			case 4 -> {
				logger.info(nl + "--- Modificar Deportista ---" + nl);
				logger.info("Id del deportista: ");
				var idDeportista = Integer.parseInt(consola.nextLine());
				var deportista = deportistaServicio.buscarDeportistaPorID(idDeportista);
				if(deportista != null){
					logger.info("Nombre: ");
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Edad: ");
					var edad = Integer.parseInt(consola.nextLine());
					deportista.setNombre(nombre);
					deportista.setApellido(apellido);
					deportista.setEdad(edad);
					deportistaServicio.guardarDeportista(deportista);
					logger.info("Deportista modificado: " + deportista + nl);
				}
				else{
					logger.info("Deportista No encontrado: " + deportista + nl);
				}
			}
			case 5 -> {
				logger.info(nl + "--- Eliminar Deportista ---" + nl);
				logger.info("Id del deportista: ");
				var idDeportista = Integer.parseInt(consola.nextLine());
				var deportista = deportistaServicio.buscarDeportistaPorID(idDeportista);
				if(deportista != null){
					deportistaServicio.eliminarDeportista(deportista);
					logger.info("Deportista eliminado: " + deportista + nl);
				}
				else{
					logger.info("Deportista No eliminado: " + deportista + nl);
				}
			}
			case 6 -> {
				logger.info("Hasta pronto!" + nl + nl);
				salir = true;
			}
			default -> logger.info("Opción No reconocida: " + opcion);
		}
		return salir;
	}
}
