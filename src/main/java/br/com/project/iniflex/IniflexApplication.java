package br.com.project.iniflex;

import br.com.project.iniflex.entity.Funcionario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

		// Atualizar salários com 10% de aumento
		funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1.1))));

		// Agrupar funcionários por função
		Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

		// Imprimir funcionários por função
		System.out.println("\nFuncionários por Função");
		funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
			System.out.println("Função: " + funcao);
			listaFuncionarios.forEach(func -> System.out.printf("Nome: %s%n", func.getNome()));
		});

	}

}
