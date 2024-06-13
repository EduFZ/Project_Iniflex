package br.com.project.iniflex.methods;

import br.com.project.iniflex.entity.Funcionario;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Methods {

    List<Funcionario> funcionarios = new ArrayList<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));

    static {
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
    }
    static DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

}
