import java.util.ArrayList;
import java.util.Stack;

class Sintatico{
    static Stack<String> pilha = new Stack();
    static ArrayList<Token> tk = new ArrayList();
    public static void main(ArrayList<Token> token) {
        tk = token;
        pilha.push("$");
        pilha.push("estrutura_programa");

        while(!"$".equals(pilha.peek())){
            Geraproducao(token.get(0), pilha.peek());
            validador(token.get(0), pilha.peek());
        }
    }
    
    
    private static void validador(Token tokenatual, String  topo) {
        switch (topo) {
            case "estrutura_programa":
            if (tokenatual.getToken().equals("inicio")) {
                Match();
                break;
            }
            default:
                break;
        }
        
    }
    private static void Match( ) {
        pilha.pop();
        tk.remove(0);

    }


    private static void Geraproducao(Token objeto, String naoterminais) { // buscar na pilham// passa o objeto todo "token"
    switch (naoterminais) {
        case "estrutura_programa":
            estrutura_programa(objeto.getToken());
            
            break;
    
        default:
            break;
    }
   

    }
    private static void estrutura_programa( String tk) {
        switch (tk) {
            case "inicio":
            producaozero();
    }
    }
    private static void producaozero() {
        pilha.pop();
        pilha.push("fim");
        pilha.push("codigo");
        pilha.push("declaracao_vars");
        pilha.push("inicio");
    }
}
    