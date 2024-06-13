package br.com.project.iniflex;

import br.com.project.iniflex.entity.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class IniflexApplication {

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));

	static {
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
	}
	static DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);



	public static void main(String[] args) {

		// Adicionar todos os elementos na ordem conforme anexado no enunciado
		addFunc();

		// Remover funcionario João
		removeFuncJoao("João");

		// Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
		agruparByFuncMap();

		// Imprimir todos os funcionários com a formatação em data e salário
		listFuncDataSalario();

		// Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
		addAumentoSalario();

		// Imprimir os funcionários, agrupados por função.
		funcByFuncao();

		// Imprimir os funcionários que fazem aniversário no mês 10 e 12.
		funcAniv10And12();

		// Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
		funcMaiorIdade();

		// Imprimir a lista de funcionários por ordem alfabética.
		listFuncAlfabetica();

		// Imprimir o total dos salários dos funcionários.
		totalSalarios();

		// Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
		salariosMinimos();

	}



	// Adicionar todos os elementos na ordem conforme anexado no enunciado
	public static List<Funcionario> addFunc() {
		List<Funcionario> funcionarios = new ArrayList<>();
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
		return funcionarios;
	}


	// Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
	public static Map<String, List<Funcionario>> agruparByFuncMap() {
		return addFunc().stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
	}


	// Remover funcionario João
	public static void removeFuncJoao(String name) {
		addFunc().removeIf(funcionario -> funcionario.getNome().equals(name));
	}


	// Imprimir todos os funcionários com a formatação em data e salário
	public static void listFuncDataSalario() {
		List<Funcionario> funcs = addFunc();
		System.out.println("Funcionários: ");
		for (Funcionario func : funcs) {
			System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %s, Função: %s%n",
					func.getNome(), func.getDataNasc().format(formatter), decimalFormat.format(func.getSalario()), func.getFuncao());
		}
	}


	// Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
	public static void addAumentoSalario() {
		addFunc().forEach(f -> f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1.1))));
	}


	// Imprimir os funcionários, agrupados por função.
	public static void funcByFuncao() {
		Map<String, List<Funcionario>> stringListMap = agruparByFuncMap();
		System.out.println("\nFuncionários por Função");
		stringListMap.forEach((funcao, listaFuncionarios) -> {
			System.out.println("\nFunção: " + funcao);
			listaFuncionarios.forEach(func -> System.out.printf("Nome: %s%n", func.getNome()));
		});
	}


	// Imprimir os funcionários que fazem aniversário no mês 10 e 12.
	public static void funcAniv10And12() {
		System.out.println("\nFuncionários com aniversário no mês 10 e 12:");
		addFunc().stream().filter(f -> f.getDataNasc().getMonthValue() == 10 || f.getDataNasc().getMonthValue() == 12)
				.forEach(f -> System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s%n",
						f.getNome(), f.getDataNasc().format(formatter), decimalFormat.format(f.getSalario()), f.getFuncao()));
	}


	// Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
	public static void funcMaiorIdade() {
		Funcionario funcMaiorIdade = addFunc().stream().min(Comparator.comparing(Funcionario::getDataNasc))
				.orElseThrow(NoSuchElementException::new);
		Integer idade = Period.between(funcMaiorIdade.getDataNasc(), LocalDate.now()).getYears();
		System.out.printf("\nFuncionário com maior idade: \n" +
				"Nome: %s, Idade: %s%n", funcMaiorIdade.getNome(), idade);
	}


	// Imprimir a lista de funcionários por ordem alfabética.
	public static void listFuncAlfabetica() {
		System.out.println("\nFuncionários em ordem alfabética:");
		addFunc().stream().sorted(Comparator.comparing(Funcionario::getNome))
				.forEach(f -> System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %s, Função: %s%n",
						f.getNome(), f.getDataNasc().format(formatter), decimalFormat.format(f.getSalario()), f.getFuncao()));
	}


	// Imprimir o total dos salários dos funcionários.
	public static void totalSalarios() {
		BigDecimal totalSalarios = addFunc().stream().map(Funcionario::getSalario)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.printf("\nTotal dos Salários: %s%n", decimalFormat.format(totalSalarios));
	}


	// Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
	public static void salariosMinimos() {
		BigDecimal salarioMinimo = new BigDecimal("1212.00");
		System.out.println("\nQuantidade de salários mínimo por funcionário:");
		addFunc().forEach(funcionario -> {
			BigDecimal qntSalarioMinimo = funcionario.getSalario().divide(salarioMinimo, RoundingMode.HALF_EVEN);
			System.out.printf("Nome: %s, Salários Mínimo: %, .2f%n", funcionario.getNome(), qntSalarioMinimo);
		});
	}

}
