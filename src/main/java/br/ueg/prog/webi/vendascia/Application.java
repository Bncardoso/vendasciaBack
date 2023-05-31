package br.ueg.prog.webi.vendascia;

import br.ueg.prog.webi.vendascia.model.Vendas;
import br.ueg.prog.webi.vendascia.repository.VendasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class}, basePackages = "br.ueg.prog.webi.vendascia.*")

public class Application {

	public static void main(String[] args) { SpringApplication.run(Application.class, args);}
		@Bean
		public CommandLineRunner commandLineRunner (VendasRepository vendasRepository){
			return args -> {
				System.out.println("Executado");
				System.out.println(vendasRepository);
				/*Vendas v1 = new Vendas();
				v1.setNomeProduto("Pano de Prato 60x30");
				v1.setQtdVenda(2);
				v1.setValorUnidade(39.99);
				v1.setNomeCliente("Marta");
				v1.setContatoCliente(33263023L);

				try {
					vendasRepository.save(v1);
				} catch (Exception e) {
					e.printStackTrace();
				}

				v1.setId(1L);
				v1 = vendasRepository.save(v1);
				System.out.println("Venda: " + v1);*/

/*

				imprimirLista(vendasRepository);*/
//				vendasRepository.delete(v1);
			};
		}

		private static void imprimirLista(VendasRepository vendasRepository){
			List<Vendas> lista = vendasRepository.findAll();
			lista.forEach(item ->{
				System.out.println("vendasRepository " +item);
			});
		}


}
