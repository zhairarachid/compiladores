/* Autor.: Zhaira
 * 
 */

package Sintatico;


import java.util.ArrayList;
import java.util.Stack;
import Lexico.Token;

public class Sintatico{

    static Stack<String> pilha = new Stack();
    static ArrayList<Token> tk;
    
    public static void main(ArrayList<Token> token) {
        tk = new ArrayList<>(token);
        pilha.push("$");
        pilha.push("estrutura_programa");

        while(!"$".equals(pilha.peek())){
            Geraproducao(tk.get(0), pilha.peek());
            validador(tk.get(0), pilha.peek());
        }


        pilha.forEach(t -> {
            System.out.println(t);
    
        });

        tk.forEach(t -> {
            System.out.println(t);
        });
    }
    
   
    /**
     * @param objeto
     * @param naoterminais
     * 
     */
    private static void Geraproducao(Token objeto, String naoterminais) { // buscar na pilham// passa o objeto todo "token"
        switch (naoterminais) {
            case "estrutura_programa":
                estrutura_programa(objeto.getToken());
                break;

            case "declaracao_vars":
                declaracao_vars(objeto.getToken());
                break;

            case "lista_vars":
                lista_vars(objeto.getToken());
                break;
            
            case "codigo":
                codigo(objeto.getToken());
                break;
            case "comando":
                comando(objeto.getToken());
                break;
            case "variavel_saida":
                variavel_saida(objeto.getToken());
                break;
            case "lista_comando":
                lista_comando(objeto.getToken());
                break;  
            case "condicao":
                condicao(objeto.getToken());
                break;  
            case "contra_condicao":
                contra_condicao(objeto.getToken());
                break;

            case "expressao_matematica":
                expressao_matematica(objeto.getToken());
                break;
            case "operando":
                operando(objeto.getToken());
                break;
                
            case "operacao_matematica":
                operacao_matematica(objeto.getToken());
                break;
            case "operador_logico":
                operador_logico(objeto.getToken());
                break;

            case "operador":
                operador(objeto.getToken());
                break;
            
            default:
                break;
        } 
    }
//------------------------------------------------------------------- 3
    private static void validador(Token tokenatual, String  topo) {
        switch (topo) {
            case "inicio":
                if (tokenatual.getToken().equals("Inicio")) {
                    Match();
                    break;
                }
            case "declaracao_vars":
                if (tokenatual.getToken().equals("declaracao_vars")) {
                    Match();
                    break;
                }
            case "variavel":
                if (tokenatual.getToken().equals("variavel")) {
                    Match();
                    break;
                }
            case "separador_vars":
                if (tokenatual.getToken().equals("separador_vars")) {
                    Match();
                    break;
                }
            case "fim_linha":
                if (tokenatual.getToken().equals("fim_linha")) {
                    Match();
                    break;
                }
            case "escrita":
                if (tokenatual.getToken().equals("escrita")) {
                    Match();
                    break;
                }

            case "leitura":
                if (tokenatual.getToken().equals("leitura")) {
                    Match();
                    break;
                }
            case "se":
                if (tokenatual.getToken().equals("se")) {
                    Match();
                    break;
                }
            case "senao":
                if (tokenatual.getToken().equals("senao")) {
                    Match();
                    break;
                }
            case "enquanto":
                if (tokenatual.getToken().equals("enquanto")) {
                    Match();
                    break;
                }
            case "abre_parenteses":
                if (tokenatual.getToken().equals("abre_parenteses")) {
                    Match();
                    break;
                }
            case "natural":
                if (tokenatual.getToken().equals("natural")) {
                    Match();
                    break;
                }
            case "frase":
                if (tokenatual.getToken().equals("frase")) {
                    Match();
                    break;
                }
            case "fecha_parenteses":
                if (tokenatual.getToken().equals("fecha_parenteses")) {
                    Match();
                    break;
                }
            case "inteiro":
                if (tokenatual.getToken().equals("inteiro")) {
                    Match();
                    break;
                }

            case "leia":
                if (tokenatual.getToken().equals("leia")) {
                    Match();
                    break;
                }
                case "soma":
                if (tokenatual.getToken().equals("soma")) {
                    Match();
                    break;
                }
                case "subtracao":
                if (tokenatual.getToken().equals("subtracao")) {
                    Match();
                    break;
                }
                case "multiplicacao":
                if (tokenatual.getToken().equals("multiplicacao")) {
                    Match();
                    break;
                }
                case "divisao":
                if (tokenatual.getToken().equals("divisao")) {
                    Match();
                    break;
                }
                case "igualdade":
                if (tokenatual.getToken().equals("igualdade")) {
                    Match();
                    break;
                }
                case "diferenca":
                if (tokenatual.getToken().equals("diferenca")) {
                    Match();
                    break;
                }

                case "abre_chave":
                if (tokenatual.getToken().equals("abre_chave")) {
                    Match();
                    break;
                }
                case "fecha_chave":
                if (tokenatual.getToken().equals("fecha_chave")) {
                    Match();
                    break;
                }

                case "atribuicao":
                if (tokenatual.getToken().equals("atribuicao")) {
                    Match();
                    break;
                }
                
                case "fim":
                    if (tokenatual.getToken().equals("fim")) {
                        Match();
                        break;
                    }
        
            default:
                break;
        }
        
    }
//---------------------------------------------------------------------------- 2
    private static void Match( ) {
        pilha.pop();
        tk.remove(0);

    }



    private static void estrutura_programa( String tk) {
        switch (tk) {
            case "Inicio":
                producaozero();
                break;
        }   
    }

    
    private static void declaracao_vars( String tk) {
        switch (tk) {
            case "leitura":
                producaovazio();
                break;

            case "escrita":
                producaovazio();
                break;
            
            case "se":
                producaovazio();
                break;
        
            case "enquanto":
                producaovazio();
                break;

            case "declaracao_vars":
                producaoum();
                break;

            case "variavel":
                producaovazio();
                break;
        }   
    }
    private static void lista_vars( String tk) {
        switch (tk) {
            case "separador_vars":
                producaotres();
                break;
            case "fim_linha":
                producaovazio();
                break;

        }   
    }

    private static void codigo( String tk) {
        switch (tk) {
            case "leitura":
                producaocinco();
                break;
            case "escrita":
            producaocinco();
                break;

            case "se":
            producaocinco();
                break;
            case "enquanto":
            producaocinco();
                break;
            case "variavel":
            producaocinco();
                break;   
        }  
    }
    private static void comando( String tk) {
        switch (tk) {
            case "leia":
                producaooito();
                break;
            case "escrita":
                producaonove();
                break;

            case "se":
                producaoquatorze();
                break;
            case "enquanto":
                producaovinte();
                break;
            case "variavel":
                producaovinteeum();
                break;
         }
                
    }
        private static void variavel_saida( String tk) {
            switch (tk) {
                
                case "inteiro":
                producaodoze();
                    break;
                case "natural":
                producaoonze();
                    break;
                    
                case "variavel":
                producaodez();
                    break;
                
                case "frase":
                producaotreze();
                    break;
                    
            }
        }

        private static void lista_comando( String tk) {
            switch (tk) {
                
                case "fim":
                    producaovazio();
                    break;
                case "leia":
                    producaoseis();
                    break;
                    
                case "escrita":
                    producaoseis();
                    break;
                
                case "se":
                    producaoseis();
                    break;
                
                case "enquanto":
                    producaoseis();
                    break;

                case "fecha_chave":
                    producaovazio();
                    break;
                
                case "variavel":
                    producaoseis();
                    break;
                    
            }
        }

        private static void condicao( String tk) {
            switch (tk) {
                case "abre_parenteses":
                    producaoquinze();
                    break;

            }   
        }

        private static void contra_condicao( String tk) {
            switch (tk) {
                case "fim":
                    producaovazio();;
                    break;
                    
                case "leitura":
                    producaovazio();;
                    break;

                case "escrita":
                    producaovazio();;
                    break;

                case "se":
                    producaovazio();;
                    break;
                
                case "enquanto":
                    producaovazio();;
                    break;

                case "fecha_chave":
                    producaovazio();;
                    break;

                case "variavel":
                    producaovazio();;
                    break;

                case "senao":
                    producaodezeseis();;;
                    break;
            }   
        }

        private static void expressao_matematica( String tk) {
            switch (tk) {
                case "abre_parenteses":
                    producaovinteetres();
                    break;
                case "inteiro":
                    producaovinteedois();
                    break;
                case "natural":
                    producaovinteedois();
                    break;
                case "variavel":
                    producaovinteedois();
                    break;
            }   
        }
        private static void operando( String tk) {
            switch (tk) {
                
                case "inteiro":
                    producaovinteeoito();
                    break;
                case "natural":
                    producaovinteesete();
                    break;
                case "variavel":
                    producaovinteeseis();
                    break;
            }   
        }
        private static void operacao_matematica( String tk) {
            switch (tk) {
                
                case "soma":
                    producaovinteequatro();
                    break;
                case "subtracao":
                    producaovinteequatro();
                    break;
                    
                case "multiplicacao":
                    producaovinteequatro();
                    break;
                
                case "divisao":
                    producaovinteequatro();
                    break;
                
                case "igualdade":
                    producaovazio();
                    break;

                case "diferenca":
                    producaovazio();
                    break;
                
                case "fecha_parenteses":
                    producaovazio();
                    break;
                
                    case "fim_linha":
                    producaovazio();
                    break; 
            }
        }

        private static void operador_logico( String tk) {
            switch (tk) {
                
                case "igualdade":
                    producaodezoito();
                    break;
                case "diferenca":
                    producaodezenove();
                    break;
            }   
        }

        private static void operador( String tk) {
            switch (tk) {
                
                case "soma":
                    producaovinteenove();
                    break;

                case "subtracao":
                    producaotrinta();
                    break;

                case "multiplicacao":
                    producaotrintaeum();
                    break;

                case "divisao":
                    producaotrintaedois();;
                    break;
            }   
        }
    //----------------------------------------- gera 
    private static void producaovazio() {
        pilha.pop();
    }

    private static void producaozero() {
        pilha.pop();
        pilha.push("fim");
        pilha.push("codigo");
        pilha.push("declaracao_vars");
        pilha.push("inicio");
    }

    private static void producaoum() {
        pilha.pop();
        pilha.push("fim_linha");
        pilha.push("lista_vars");
        pilha.push("variavel");
        pilha.push("declaracao_vars");
    }

    private static void producaotres() {
        pilha.pop();
        pilha.push("lista_vars");
        pilha.push("variavel");
        pilha.push("separador_vars");
        
    }
    private static void producaocinco() {
        pilha.pop();
        pilha.push("lista_comando");
        pilha.push("comando");
           
    }
    
    private static void producaoseis() {
        pilha.pop();
        pilha.push("lista_comando");
        pilha.push("comando");
           
    }


    private static void producaooito() {
        pilha.pop();
        pilha.push("fim_linha");
        pilha.push("fecha_parenteses");
        pilha.push("variavel");
        pilha.push("abre_parenteses");
        pilha.push("leia");
           
    }

    private static void producaonove() {
        pilha.pop();
        pilha.push("fim_linha");
        pilha.push("fecha_parenteses");
        pilha.push("variavel_saida");
        pilha.push("abre_parenteses");
        pilha.push("escrita"); 
        
    }

    private static void producaodez() {
        pilha.pop();
        pilha.push("variavel");
            
    }

    private static void producaoonze() {
        pilha.pop();
        pilha.push("natural");
            
    }

    private static void producaodoze() {
        pilha.pop();
        pilha.push("inteiro");
            
    }

    private static void producaotreze() {
        pilha.pop();
        pilha.push("frase");
            
    }
    
    private static void producaoquatorze() {
        pilha.pop();
        pilha.push("contra_condicao");
        pilha.push("fecha_chave");
        pilha.push("codigo");
        pilha.push("abre_chave");
        pilha.push("condicao");
        pilha.push("se"); 
    }

    private static void producaoquinze() {
        pilha.pop();
        pilha.push("fecha_parenteses");
        pilha.push("expressao_matematica");
        pilha.push("operador_logico");
        pilha.push("expressao_matematica");
        pilha.push("abre_parenteses");
    }

    private static void producaodezeseis() {
        pilha.pop();
        pilha.push("fecha_chave");
        pilha.push("codigo");
        pilha.push("abre_chave");
        pilha.push("senao");
    }

    private static void producaodezoito() {
        pilha.pop();
        pilha.push("igualdade");
    }
    private static void producaodezenove() {
        pilha.pop();
        pilha.push("diferenca");
    }

    private static void producaovinte() {
        pilha.pop();
        pilha.push("fecha_chave");
        pilha.push("codigo");
        pilha.push("abre_chave");
        pilha.push("condicao");
        pilha.push("enquanto"); 
    }
    private static void producaovinteeum() {
        pilha.pop();
        pilha.push("fim_linha");
        pilha.push("expressao_matematica");
        pilha.push("atribuicao");
        pilha.push("variavel"); 
    }

    private static void producaovinteedois() {
        pilha.pop();
        pilha.push("operacao_matematica");
        pilha.push("operando");
        
    }
    private static void producaovinteetres() {
        pilha.pop();
        pilha.push("operacao_matematica");
        pilha.push("fecha_parenteses");
        pilha.push("expressao_matematica");
        pilha.push("abre_parenteses");
        
    }

    private static void producaovinteequatro() {
        pilha.pop();
        pilha.push("expressao_matematica");
        pilha.push("operador");
        
    }
   
    private static void producaovinteeseis() {
        pilha.pop();
        pilha.push("variavel");   
    }
    private static void producaovinteesete() {
        pilha.pop();
        pilha.push("natural");   
    }
    private static void producaovinteeoito() {
        pilha.pop();
        pilha.push("inteiro");   
    }

    private static void producaovinteenove() {
        pilha.pop();
        pilha.push("soma");   
    }

    private static void producaotrinta() {
        pilha.pop();
        pilha.push("subtracao");   
    }
    private static void producaotrintaeum() {
        pilha.pop();
        pilha.push("multiplicacao");   
    }
    private static void producaotrintaedois() {
        pilha.pop();
        pilha.push("inteiro");   
    }
}

    