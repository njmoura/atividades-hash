public class ListaEncadeada {
    public Elemento inicio;
    public int qtd;

    public ListaEncadeada() {
        inicio = null;
        qtd = 0;
    }

    public void adicionar(int chave) {
        Elemento novo = new Elemento(chave, null);

        if (inicio == null) {
            inicio = novo;
        } else if (chave < inicio.chave) {
            novo.prox = inicio;
            inicio = novo;
        } else {
            Elemento aux = inicio;
            while (aux.prox != null && aux.prox.chave < chave) {
                aux = aux.prox;
            }
            novo.prox = aux.prox;
            aux.prox = novo;
        }
        qtd++;
    }

    public void mostrar() {
        Elemento aux = inicio;
        while (aux != null) {
            System.out.print(aux.chave + " | ");
            aux = aux.prox;
        }
        System.out.println();
    }
}
