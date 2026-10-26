import java.util.Random;

public class HashEncadeado {

    static final int TAM = 100;
    static final int TOTAL = 1000;

    public static void main(String[] args) {
        ListaEncadeada[] matriz = new ListaEncadeada[TAM];
        for (int i = 0; i < TAM; i++) {
            matriz[i] = new ListaEncadeada();
        }

        Random gerador = new Random(1090);
        int listasGeradas = 0;
        int ultimo1 = 0;
        int ultimo2 = 0;
        int ultimo3 = 0;

        for (int i = 0; i < TOTAL; i++) {
            int num = gerador.nextInt(100000 + 1);
            int pos = num % TAM;

            if (matriz[pos].qtd == 1) {
                listasGeradas++;
            }
            if (matriz[pos].qtd >= 1) {
                ultimo1 = ultimo2;
                ultimo2 = ultimo3;
                ultimo3 = num;
            }
            matriz[pos].adicionar(num);
        }

        int maior = 0;
        int vazias = 0;
        for (int i = 0; i < TAM; i++) {
            if (matriz[i].qtd > matriz[maior].qtd) {
                maior = i;
            }
            if (matriz[i].qtd == 0) {
                vazias++;
            }
        }

        System.out.println("Maior lista encadeada (posicao " + maior + ", " + matriz[maior].qtd + " elementos):");
        matriz[maior].mostrar();
        System.out.println("Quantidade de listas encadeadas geradas: " + listasGeradas);
        System.out.println("Quantidade de posicoes vazias: " + vazias);

        System.out.println("Tres ultimos valores inseridos em listas encadeadas:");
        System.out.println(ultimo1);
        System.out.println(ultimo2);
        System.out.println(ultimo3);
    }
}
