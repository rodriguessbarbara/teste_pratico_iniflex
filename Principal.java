import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        //requisito 3.1
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("Joao", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    
        //requisito 3.2
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("Joao"));
        System.out.println("\nFuncionario Joao foi removido!");

        //requisito 3.3
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\nLista de funcionarios: ");
        for (Funcionario funcionario : funcionarios) {
            String dataNascimento = funcionario.getDataNascimento().format(dateFormatter);
            String salario = String.format("%,.2f", funcionario.getSalario());

            System.out.println("Nome: " + funcionario.getNome() + ", Data de nascimento: " + dataNascimento + ", Salario: R$" + salario + ", Funcao: " + funcionario.getFuncao());
        }

        //requisito 3.4
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(novoSalario);
        }
        System.out.println("\n10% de aumento nos salarios dos funcionarios: ");

        //requisito 3.5
        Map<String, List<Funcionario>> funcionariosMap = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosMap.containsKey(funcao)) {
                funcionariosMap.put(funcao, new ArrayList<>());
            }
            funcionariosMap.get(funcao).add(funcionario);
        }

        //requisito 3.6
        System.out.println("\nFuncionarios por funcao: ");
        for (String funcao : funcionariosMap.keySet()) {
            System.out.println("Funcao: " + funcao);
            List<Funcionario> listaFuncionarios = funcionariosMap.get(funcao);
        
            for (Funcionario funcionario : listaFuncionarios) {
                String dataNascimento = funcionario.getDataNascimento().format(dateFormatter);
                String salario = String.format("%,.2f", funcionario.getSalario());
                System.out.println("    Nome: " + funcionario.getNome() + ", Data Nascimento: " + dataNascimento + ", Salario: R$" + salario);
            }
        }

        //requisito 3.8
        System.out.println("\nFuncionarios que fazem aniversario no mes 10 e 12: ");
        for (Funcionario funcionario : funcionarios) {
            int mes = funcionario.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                String dataNascimento = funcionario.getDataNascimento().format(dateFormatter);
                String salario = String.format("%,.2f", funcionario.getSalario());
                System.out.println("Nome: " + funcionario.getNome() + ", Data Nascimento: " + dataNascimento + ", Salario: R$" + salario + ", Funcao: " + funcionario.getFuncao());
            }
        }

        //requisito 3.9
        Funcionario funcionarioMaisVelho = funcionarios.get(0);
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())) {
                funcionarioMaisVelho = funcionario;
            }
        }
        int idade = LocalDate.now().getYear() - funcionarioMaisVelho.getDataNascimento().getYear();
        System.out.println("\nFuncionario com a maior idade: " + funcionarioMaisVelho.getNome() + ", " + idade + " anos");

        //requisito 3.10
        //Não consegui resolver esse requisito :/

        //requisio 3.11
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
            }
        System.out.println("\nTotal dos salarios: R$" + String.format("%,.2f", totalSalarios));
        
        //requisito 3.12
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salarios minimos por funcionario:");
        for (Funcionario funcionario : funcionarios) {
            @SuppressWarnings("deprecation")
            BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println("Nome: " + funcionario.getNome() + ", Salarios Minimos: " + quantidadeSalariosMinimos);
        }
    }
}