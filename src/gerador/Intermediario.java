package gerador;

import Lexico.Token;
import java.util.ArrayList;

public class Intermediario {
    
    static ArrayList<String> CodInt     = new ArrayList();
    static ArrayList<String> CodIntLog  = new ArrayList();
    static ArrayList<String> ls         = new ArrayList();
     
    static String Comando  = "";
    static String Variavel = ""; 
    static String VarAtrib = ""; 
    static String Frase    = "";
    static String ExpBis   = "";
    static String ExpSe    = "";
    static String ExpnSe   = "";
    static String Numero   = "";
    static String InFix    = "";

    static boolean IsLe    =  false;
    static boolean IsVar   =  false;
    static boolean IsEsc   =  false;
    static boolean IsFrase =  false;
    static boolean IsNum   =  false;
    static boolean IsAtrib =  false;
    static boolean IsBis   =  false;
    static boolean IsSe    =  false;
    static boolean IsnSe   =  false;
    
    public static ArrayList<String> Iniciar(ArrayList<Token> tk, ArrayList<String> ListaVars) {
        CodIntLog.add("Inicio da geração do codigo intermediario");
        
        ls = ListaVars;
        
        // Conversão das produções da gramatica
        Conversao(tk);
        
        
        CodInt.forEach((t) -> {
            System.out.println(t);
        });
        
        CodIntLog.forEach((t) -> {
            System.out.println(t);
        });
        
        return CodInt;
    }

    private static void RegistrarVars(ArrayList<String> list) {
        
        list.forEach((variavel) -> {
            CodInt.add("_Var "+variavel.replace("_", ""));
            CodIntLog.add("Declarando a Variavel.: "+variavel.replaceAll("&", ""));
        });
    }

    private static void Conversao(ArrayList<Token> tokenList) {
        
        tokenList.forEach((token) -> {
            switch(token.getToken()){
                case "Inicio":
                    CodInt.add("inicio");
                    CodIntLog.add("Reconhecimento do Inicio do programa");
                    break;
                    
                case "fim":
                    CodInt.add("fim");
                    CodIntLog.add("Reconhecimento do fim do programa");
                    break;
                    
                case "declaracao_vars":
                    // Registro de Variaveis
                    RegistrarVars(ls);
                    break;
                
                case "leia":
                    IsLe = true;
                    Comando = "leia ";
                    break;
                
                case "variavel":
                    Variavel = token.getLexema().replaceAll("_", "");
                    if(IsAtrib == true){
                        InFix += Variavel;
                    }
                    if(IsBis){
                        ExpBis += Variavel+" ";
                    }
                    if(IsSe){
                        ExpSe += Variavel+" ";
                    }
                    if(IsnSe){
                        ExpnSe += Variavel+" ";
                    }
                    IsVar = true;
                    break;
                    
                case "frase": 
                    IsFrase = true;
                    Frase = token.getLexema();
                    break;
                
                case "inteiro":
                    IsNum = true;
                    Numero = token.getLexema();
                    if(IsAtrib == true){    
                        InFix += Numero;
                    }
                    if(IsBis){
                        ExpBis += Numero+" ";
                    }
                    if(IsSe){
                        ExpSe += Numero+ " ";
                    }
                    if(IsnSe){
                        ExpnSe += Numero+ " ";
                    }
                    break;
                    
                case "soma":
                    InFix += token.getLexema();
                    break;
                
                case "subtracao":
                    InFix += token.getLexema();
                    break; 
                    
                case "multiplicacao":
                    InFix += token.getLexema();
                    break; 
                
                case "divisao":
                    InFix += token.getLexema();
                    break; 
                case "igualdade":
                    InFix += token.getLexema();
                    break;     
                  
                case "escrita":
                    IsEsc = true;
                    Comando = "escreva ";
                    break;
                    
                case "atribuicao":
                    VarAtrib = Variavel;
                    IsAtrib = true;
                    break;
                    
                case "enquanto":
                    IsBis = true;
                    Comando = "repita_ate ";
                    break;
                    
                case "se":
                    IsSe = true;
                    Comando = "se ";
                    break;
                
                case "senao":
                    IsnSe = true;
                    Comando = "senao ";
                    break;            
                            
                case "abre_parenteses":
                    if(IsBis){
                        ExpBis += token.getLexema()+" ";
                    }
                    if(IsSe){
                        ExpSe += token.getLexema()+" ";
                    }
                    if(IsnSe){
                        ExpnSe += token.getLexema()+" ";
                    }
                    break;
                    
                case "fecha_parenteses":
                    if(IsBis){
                        ExpBis += token.getLexema()+" ";
                    }
                    if(IsSe){
                        ExpSe += token.getLexema()+" ";
                    }
                    if(IsnSe){
                        ExpnSe += token.getLexema()+" ";
                    }
                    break;
                    
                case "diferenca":
                    if(IsBis){
                        ExpBis += token.getLexema()+" ";
                    }
                    if(IsSe){
                        ExpSe += token.getLexema()+" ";
                    }
                    if(IsnSe){
                        ExpnSe += token.getLexema()+" ";
                    }
                    break;
                    
                case "abre_chave":
                    if(IsBis){
                        CodInt.add(Comando+ExpBis);
                        CodIntLog.add("Reconhecimento do comando do inicio da repetição(enquanto)" );
                    }
                    if(IsSe){
                        CodInt.add(Comando+ExpSe);
                        CodIntLog.add("Reconhecimento do comando do incio da condicional (SE)");
                    }
                    if(IsnSe){
                        CodInt.add(Comando+ExpnSe);
                        CodIntLog.add("Reconhecimento do comando do incio da condicional (senao)");
                    }
                    break;
                    
                case "fecha_chave":
                    if(IsBis){
                        CodInt.add("fim_repita");
                        CodIntLog.add("Reconhecimento do comando do final da repetição(enquanto)" );
                        IsBis    = false;
                    }
                    if(IsSe){
                        CodInt.add("fim_se");
                        CodIntLog.add("Reconhecimento do comando do final da condicional (SE)" );
                        IsSe    = false;
                    }
                    if(IsnSe){
                        CodInt.add("fim_senao");
                        CodIntLog.add("Reconhecimento do comando do final da condicional (senao)" );
                        IsnSe    = false;
                    }
                    break;
                        
                case "fim_linha":
                    
                    // Converte o comando leia
                    if(IsLe == true && IsVar == true){
                        CodInt.add(Comando+Variavel);
                        CodIntLog.add("Reconhecimento do comando de Leitura da variavel "+Variavel);
                    }
                    
                    // Converte o comando escreva
                    if(IsEsc == true && (IsVar == true || IsFrase == true || IsNum == true)){
                        if(IsVar){
                            CodInt.add(Comando + Variavel);
                            CodIntLog.add("Recinhecimento do comando de Escrita da variavel "+Variavel);
                        }else if(IsFrase){
                            CodInt.add(Comando + Frase);
                            CodIntLog.add("Reconhecimento do comando de Escrita de "+Frase);
                        }else if(IsNum){
                            CodInt.add(Comando + Numero);
                            CodIntLog.add("Reconhecimento do comando de Escrita de "+Numero);
                        }
                    }
                    
                    // Converte Atribuição
                    if(IsVar == true && IsAtrib == true){
                        UtilStr util = new UtilStr();
                        String aux = VarAtrib + " = " +util.InFixToPosFix(InFix);
                        CodInt.add(aux);
                        CodIntLog.add("Reconhecimento da atribuição "+aux);
                    }
                    
                    LimpaVariaveis();
            }
 
        });  
    }

    private static void LimpaVariaveis() {
        IsLe     = false;
        IsVar    = false;
        IsNum    = false;
        IsFrase  = false;
        IsEsc    = false;
        IsAtrib  = false;
        Comando  = "";
        Variavel = "";
        Numero   = "";
        InFix    = "";
        VarAtrib = "";
    }
}
