package financiamento;


public class Apartamento extends Financiamento {

    public Apartamento(double valorImovel, int meses, double taxaAnual) {
        super(valorImovel, meses, taxaAnual);
    }

    @Override
    public double calcularParcelas() {
        double taxaMensal = taxaAnual / 12 / 100;
        return (valorImovel * taxaMensal) / (1 - Math.pow(1 + taxaMensal, -meses));
    }
}
