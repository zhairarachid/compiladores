package Semantico;

import Lexico.Token;
import java.util.ArrayList;


public class semantico {
    
    //static ArrayList<Tokens> tokens = new ArrayList();
    
    static ArrayList<String> declaração_vars    = new ArrayList();
    static boolean isDeclarando                 = false;
    static boolean isValidaDivPorZero           = false;
    
    public static ArrayList<String> Iniciar(ArrayList<Token> tk) {
        tk.forEach((elements) -> {
            
            if(elements.getToken().equals("declaracao_vars")){
                isDeclarando = true;
            }
            if(isDeclarando == true && elements.getToken().equals("fim_linha")){
                isDeclarando = false;
            }
            if(elements.getToken().equals("variavel")){
                // Verificação das variaveis declaradas
                VerificaVarsDeclaradas(elements);
            }
            
            if(elements.getLexema().equals("/")){
                isValidaDivPorZero = true;
            }
            
            if(isValidaDivPorZero == true){
                if(elements.getLexema().equals("0")){
                    System.out.println("Erro.: Não é possivel efetuar divisao por zero");
                }
            }
        });
        
        
        System.out.println("Lista de Variaveis declaradas.: "+declaração_vars.toString());
        
        return declaração_vars;
    }

    private static void VerificaVarsDeclaradas(Token element) {
        
        // Verificando se houve declaracao duplicada de variaveis.
        if(declaração_vars.contains(element.getLexema()) && isDeclarando == true){
            System.out.println("Variavel Declarada mais de uma vez.: "
                    +element.getLexema()+ " Linha.: "+element.getLinha()+
                    " Coluna.: "+element.getColuna());
        }
        // verificando se a variavel chamada foi declarada.
        else if(isDeclarando == false && !declaração_vars.contains(element.getLexema())){
            System.out.println("Variavel não declarada.: "+element.getLexema()
            +" Linha.: "+element.getLinha() + " Coluna.: "+element.getColuna());
        }else{
            // Apenas para nao duplicar a variavel na lista
            if(!declaração_vars.contains(element.getLexema())){
                declaração_vars.add(element.getLexema());
            }
        }
    }
    
}
