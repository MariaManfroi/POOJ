package financiamento;


public abstract class Financiamento {
    protected double valorImovel;
    protected int meses;
    protected double taxaAnual;

    public Financiamento(double valorImovel, int meses, double taxaAnual) {
        this.valorImovel = valorImovel;
        this.meses = meses;
        this.taxaAnual = taxaAnual;
    }

    public double getValorImovel() {
        return valorImovel;
    }

    public int getMeses() {
        return meses;
    }

    public abstract double calcularParcelas();

    public String resumo() {
        return String.format("Imóvel: R$%.2f | Meses: %d | Juros anual: %.2f%% | Parcela: R$%.2f",
            valorImovel, meses, taxaAnual, calcularParcelas());
    }
}
