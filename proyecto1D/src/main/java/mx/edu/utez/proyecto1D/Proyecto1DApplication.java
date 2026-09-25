package mx.edu.utez.proyecto1D;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/fizzbuzz&fibonacci")
public class Proyecto1DApplication {

	public static void main(String[] args) {
		SpringApplication.run(Proyecto1DApplication.class, args);
	}
	@GetMapping("/fizzbuzz/{n}")
	public String fizzbuzz(@PathVariable int n) {

		for (int i = 1; i <= n; i++) {

			if (i % 3 == 0 && i % 5 == 0) {
				System.out.println("FizzBuzz");
			} else if (i % 3 == 0) {
				System.out.println("Fizz");
			} else if (i % 5 == 0) {
				System.out.println("Buzz");
			} else {
				System.out.println(i);
			}

		}

		return "Dulce Yazmin Canseco Juarez";
	}
	@GetMapping("/fibonacci/{n}")
	public String fibonacci(@PathVariable int n) {
		int a = 0;
		int b = 1;

		for (int i = 1; i <= n; i++) {
			System.out.println(a);

			int siguiente = a + b;
			a = b;
			b = siguiente;
		}

		return "Dulce Yazmin Canseco Juarez";
	}
}