package utils;


import financiamento.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Simulador {

    public static void iniciar() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Financiamento> financiamentos = new ArrayList<>();

        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\n=== Nova simulação de financiamento ===");

                System.out.print("Valor do imóvel: R$ ");
                double valor = sc.nextDouble();

                System.out.print("Duração (meses): ");
                int meses = sc.nextInt();

                System.out.print("Taxa de juros anual (%): ");
                double taxa = sc.nextDouble();

                System.out.println("Tipo de imóvel (1 - Casa | 2 - Apartamento | 3 - Terreno): ");
                int tipo = sc.nextInt();

                Financiamento f = switch (tipo) {
                    case 1 -> new Casa(valor, meses, taxa);
                    case 2 -> new Apartamento(valor, meses, taxa);
                    case 3 -> new Terreno(valor, meses, taxa);
                    default -> throw new IllegalArgumentException("Tipo inválido.");
                };

                financiamentos.add(f);

                System.out.println("\n" + f.resumo());

                System.out.print("\nDeseja simular outro financiamento? (s/n): ");
                String resp = sc.next();
                continuar = resp.equalsIgnoreCase("s");

            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Use números.");
                sc.nextLine(); // limpar buffer
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                sc.nextLine(); // limpar buffer
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                sc.nextLine();
            }
        }

        // Exibir resumo geral
        double totalImoveis = 0;
        double totalParcelas = 0;

        System.out.println("\n=== Resumo Geral dos Financiamentos ===");

        for (Financiamento f : financiamentos) {
            System.out.println(f.resumo());
            totalImoveis += f.valorImovel;
            totalParcelas += f.calcularParcelas();
        }

        System.out.printf("\nTotal de imóveis financiados: %d\n", financiamentos.size());
        System.out.printf("Soma dos valores dos imóveis: R$ %.2f\n", totalImoveis);
        System.out.printf("Soma das parcelas mensais: R$ %.2f\n", totalParcelas);

        sc.close();
    }
}
double totalImoveis = 0;
double totalParcelasMensais = 0;
double totalGeralPago = 0;

System.out.println("\n=== Resumo Geral dos Financiamentos ===");

for (Financiamento f : financiamentos) {
    double parcela = f.calcularParcelas();
    double totalPago = parcela * f.meses;

    System.out.printf("%s | Total pago: R$%.2f\n", f.resumo(), totalPago);

    totalImoveis += f.valorImovel;
    totalParcelasMensais += parcela;
    totalGeralPago += totalPago;
}

System.out.printf("\nTotal de imóveis financiados: %d\n", financiamentos.size());
System.out.printf("Soma dos valores dos imóveis: R$ %.2f\n", totalImoveis);
System.out.printf("Soma das parcelas mensais: R$ %.2f\n", totalParcelasMensais);
System.out.printf("Valor total a ser pago por todos os financiamentos (somando meses): R$ %.2f\n", totalGeralPago);
