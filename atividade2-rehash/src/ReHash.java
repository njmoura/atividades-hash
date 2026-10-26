import java.util.Random;

public class ReHash {

    static final int TAM = 100;
    static final int VAZIO = -1;

    static int[] matriz = new int[TAM];
    static int ocupadas = 0;
    static int colisoesTotal = 0;

    static int[] chaves = new int[TAM];
    static int[] colisoes = new int[TAM];
    static int n = 0;

    static int funcaoHash(int chave) {
        return chave % TAM;
    }

    static int reHash(int pos) {
        return (pos + 1) % TAM;
    }

    static boolean inserir(int chave) {
        if (ocupadas >= 90) {
            return false;
        }

        int pos = funcaoHash(chave);
        int c = 0;
        while (matriz[pos] != VAZIO) {
            c++;
            pos = reHash(pos);
        }

        matriz[pos] = chave;
        ocupadas++;
        colisoesTotal = colisoesTotal + c;
        chaves[n] = chave;
        colisoes[n] = c;
        n++;
        return true;
    }

    static int buscar(int chave) {
        int pos = funcaoHash(chave);
        int passos = 0;
        while (passos < TAM && matriz[pos] != VAZIO) {
            if (matriz[pos] == chave) {
                return pos;
            }
            pos = reHash(pos);
            passos++;
        }
        return -1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < TAM; i++) {
            matriz[i] = VAZIO;
        }

        Random rand = new Random(1090);
        for (int i = 0; i < 100; i++) {
            int chave = rand.nextInt(100001);
            if (inserir(chave) == false) {
                System.out.println("Matriz com 90% de ocupacao, " + chave + " nao foi inserido");
            }
        }

        System.out.println();
        System.out.println("Numero total de colisoes: " + colisoesTotal);

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (colisoes[j] < colisoes[j + 1]) {
                    int t = colisoes[j];
                    colisoes[j] = colisoes[j + 1];
                    colisoes[j + 1] = t;
                    t = chaves[j];
                    chaves[j] = chaves[j + 1];
                    chaves[j + 1] = t;
                }
            }
        }

        System.out.println("3 elementos que mais colidiram:");
        for (int i = 0; i < 3; i++) {
            System.out.println(chaves[i] + " -> " + colisoes[i] + " colisoes");
        }

        System.out.print("Posicoes livres: ");
        for (int i = 0; i < TAM; i++) {
            if (matriz[i] == VAZIO) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.println();
        System.out.println("Busca dos 3 que mais colidiram:");
        for (int i = 0; i < 3; i++) {
            int r = buscar(chaves[i]);
            if (r == -1) {
                System.out.println(chaves[i] + " nao encontrado");
            } else {
                System.out.println(chaves[i] + " encontrado na posicao " + r);
            }
        }
    }
}
