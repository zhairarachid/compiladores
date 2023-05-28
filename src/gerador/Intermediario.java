package gerador;

import analise.lexico.Tokens;
import java.util.ArrayList;
import util.UtilStr;

/**
 *
 * @author icaroalencar
 */
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
    
    public static ArrayList<String> Iniciar(ArrayList<Tokens> tk, ArrayList<String> ListaVars) {
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
            CodInt.add("_Var "+variavel.replace("&", ""));
            CodIntLog.add("Declarando a Variavel.: "+variavel.replaceAll("&", ""));
        });
    }

    private static void Conversao(ArrayList<Tokens> tokensList) {
        
        tokensList.forEach((token) -> {
            switch(token.getTokens()){
                case "INICIO":
                    CodInt.add("inicio");
                    CodIntLog.add("Reconhecimento do Inicio do programa");
                    break;
                    
                case "FIM":
                    CodInt.add("fim");
                    CodIntLog.add("Reconhecimento do fim do programa");
                    break;
                    
                case "TIPO_ATRIB":
                    // Registro de Variaveis
                    RegistrarVars(ls);
                    break;
                
                case "LEIA":
                    IsLe = true;
                    Comando = "leia ";
                    break;
                
                case "NOME_ATRIB":
                    Variavel = token.getLexemas().replaceAll("&", "");
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
                    
                case "FRASE": 
                    IsFrase = true;
                    Frase = token.getLexemas();
                    break;
                
                case "NUMERO":
                    IsNum = true;
                    Numero = token.getLexemas();
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
                    
                case "ADICAO":
                    InFix += token.getLexemas();
                    break;
                
                case "SUBTRACAO":
                    InFix += token.getLexemas();
                    break; 
                    
                case "MULTIPLICACAO":
                    InFix += token.getLexemas();
                    break; 
                
                case "DIVISAO":
                    InFix += token.getLexemas();
                    break;    
                  
                case "ESCREVA":
                    IsEsc = true;
                    Comando = "escreva ";
                    break;
                    
                case "ATRIBUICAO":
                    VarAtrib = Variavel;
                    IsAtrib = true;
                    break;
                    
                case "REPITA":
                    IsBis = true;
                    Comando = "repita_ate ";
                    break;
                    
                case "CONDICAO":
                    IsSe = true;
                    Comando = "se ";
                    break;
                
                case "NAOCONDICAO":
                    IsnSe = true;
                    Comando = "senao ";
                    break;            
                            
                case "ABRE_PARENTESES":
                    if(IsBis){
                        ExpBis += token.getLexemas()+" ";
                    }
                    if(IsSe){
                        ExpSe += token.getLexemas()+" ";
                    }
                    if(IsnSe){
                        ExpnSe += token.getLexemas()+" ";
                    }
                    break;
                    
                case "FECHA_PARENTESES":
                    if(IsBis){
                        ExpBis += token.getLexemas()+" ";
                    }
                    if(IsSe){
                        ExpSe += token.getLexemas()+" ";
                    }
                    if(IsnSe){
                        ExpnSe += token.getLexemas()+" ";
                    }
                    break;
                    
                case "DIFERENTE":
                    if(IsBis){
                        ExpBis += token.getLexemas()+" ";
                    }
                    if(IsSe){
                        ExpSe += token.getLexemas()+" ";
                    }
                    if(IsnSe){
                        ExpnSe += token.getLexemas()+" ";
                    }
                    break;
                    
                case "MAIOR":
                    if(IsBis){
                        ExpBis += token.getLexemas()+" ";
                    }
                    if(IsSe){
                        ExpSe += token.getLexemas()+" ";
                    }
                    if(IsnSe){
                        ExpnSe += token.getLexemas()+" ";
                    }
                    break;
                    
                case "MENOR":
                    if(IsBis){
                        ExpBis += token.getLexemas()+" ";
                    }
                    if(IsSe){
                        ExpSe += token.getLexemas()+" ";
                    }
                    if(IsnSe){
                        ExpnSe += token.getLexemas()+" ";
                    }
                    break;
                    
                case "ABRE_CHAVES":
                    if(IsBis){
                        CodInt.add(Comando+ExpBis);
                        CodIntLog.add("Reconhecimento do comando do inicio da repetição(BIS)" );
                    }
                    if(IsSe){
                        CodInt.add(Comando+ExpSe);
                        CodIntLog.add("Reconhecimento do comando do incio da condicional (SE)");
                    }
                    if(IsnSe){
                        CodInt.add(Comando+ExpnSe);
                        CodIntLog.add("Reconhecimento do comando do incio da condicional (~SE)");
                    }
                    break;
                    
                case "FECHA_CHAVES":
                    if(IsBis){
                        CodInt.add("fim_repita");
                        CodIntLog.add("Reconhecimento do comando do final da repetição(BIS)" );
                        IsBis    = false;
                    }
                    if(IsSe){
                        CodInt.add("fim_se");
                        CodIntLog.add("Reconhecimento do comando do final da condificiona(SE)" );
                        IsSe    = false;
                    }
                    if(IsnSe){
                        CodInt.add("fim_senao");
                        CodIntLog.add("Reconhecimento do comando do final da condificiona(~SE)" );
                        IsnSe    = false;
                    }
                    break;
                        
                case "FIM_LINHA":
                    
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
