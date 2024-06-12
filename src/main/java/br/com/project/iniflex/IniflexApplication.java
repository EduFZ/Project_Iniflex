package br.com.project.iniflex;

import br.com.project.iniflex.entity.Funcionario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

//@SpringBootApplication
public class IniflexApplication {

	public static void main(String[] args) {
		//SpringApplication.run(IniflexApplication.class, args);

		List<Funcionario> funcionarios = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

		// Adicionar todos os elementos na ordem conforme anexado no enunciado
		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

		// Remover funcionario João
		funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

		// Imprimir todos os funcionários com a formatação em data e salário
		for (Funcionario func : funcionarios) {
			System.out.println("Funcionarios: ");
			System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %s, Função: %s%n",
					func.getNome(), func.getDataNasc().format(formatter), decimalFormat.format(func.getSalario()), func.getFuncao());
		}

		// Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
		funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1.1))));

		// Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
		Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

		// Imprimir os funcionários, agrupados por função.
		System.out.println("\nFuncionários por Função");
		funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
			System.out.println("Função: " + funcao);
			listaFuncionarios.forEach(func -> System.out.printf("Nome: %s%n", func.getNome()));
		});

		// Imprimir os funcionários que fazem aniversário no mês 10 e 12.
		System.out.println("\nFuncionários com aniversário no mês 10 e 12:");
		funcionarios.stream().filter(f -> f.getDataNasc().getMonthValue() == 10 || f.getDataNasc().getMonthValue() == 12)
				.forEach(f -> System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s%n", f.getNome(), f.getDataNasc().format(formatter), decimalFormat.format(f.getSalario()), f.getFuncao()));

		// Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
		Funcionario funcMaiorIdade = funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNasc))
				.orElseThrow(NoSuchElementException::new);
		Integer idade = Period.between(funcMaiorIdade.getDataNasc(), LocalDate.now()).getYears();
		System.out.printf("\nFuncionário com maior idade: \n" +
				"Nome: %s, Idade: %s%n", funcMaiorIdade.getNome(), idade);


	}

}
