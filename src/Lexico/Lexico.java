package Lexico;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Format;
import java.util.ArrayList;

public class Lexico {

    static BufferedReader linha = null;
    static String caminho ="/Users/Zhaira/OneDrive/Área de Trabalho/compiladores/gramatica.txt";
    static char fita[] = null;
    static String flag = null;
    static Token token = new Token();
    static String FormaToken = "";
    static ArrayList<String> ListaToken = new ArrayList();

    static ArrayList<Token> tokens = new ArrayList<>(); // guardar informações do tok

    public static ArrayList<Token> main() throws Exception {
        LerArquivo(caminho);
        LerLinha();
        lertoken();

        /*if(!ListaToken.isEmpty()){
            if(ListaToken.size() > 0)
                System.out.println("Foram Encontrados "+ListaToken.size() + " Tokens");
            else
                System.out.println("Foi Encontrado apenas "+ListaToken.size() + " Token");
            
            MostrarTokensEncontrados();
        }

        System.out.println("Total Tokens lista: "+tokens.size());
        */
        return tokens;
    }

    public static void LerArquivo(String caminho){
        
        FileInputStream arq = null;
        try{
            arq = new FileInputStream(caminho);
            InputStreamReader reader = new InputStreamReader(arq);
            linha = new BufferedReader(reader);
        }catch(FileNotFoundException e){
            System.out.println("Erro: Arquivo nao encontrado");
        }
    
    }
    
    public static void LerLinha(){
        try {
            flag = linha.readLine();
            if(flag != null){
                fita = flag.toCharArray();
                token.setLinha(token.getLinha()+1);
                token.setColuna(1);
            }
        } catch (IOException ex) {
            System.out.println("Erro: "+ex);
        }
    }
    
    
    public static void lertoken(){
        int state = 0;
        
        while(flag != null){
            
            if((token.getColuna() -1) < fita.length){   //qual coluna estoy
                state = ProximaState(state, fita[token.getColuna()-1]); //lendo a linha e caracteres e saber onde vai
                token.setColuna(token.getColuna()+1);
            }else{
                LerLinha();
            }
            
        }

    }
    
    public static void MostrarTokensEncontrados(){
        System.out.println("\tToken\t     | \tLexema\t\t   | Linha   | Coluna");
        for(int i = 0; i < ListaToken.size(); i++){
            System.out.println(ListaToken.get(i));
        }
    }
    public static int ProximaState(int newstate, char character){
        switch (newstate) {
            case 0:
                return s0(character);
            case 1:
            return s1(character);
            case 2:
            return s2(character);
            case 3:
            return s3(character);
            case 4:
            return s4(character);
            case 5:
            return s5(character);
            case 6:
            return s6(character);
            case 7:
            return s7(character);
            case 8:
            return s8(character);
            case 9:
            return s9(character);
            case 10:
            return s10(character);
            case 11:
            return s11(character);
            case 12:
            return s12(character);
            case 13:
            return s13(character);
            case 14:
            return s14(character);
            case 15:
            return s15(character);
            case 16:
            return s16(character);
            case 17:
            return s17(character);
            case 18: 
            return s18(character);
            case 19:
            return s19(character);
            case 20:
            return s20(character);
            case 21:
            return s21(character);
            case 22:
            return s22(character);
            case 23:
            return s23(character);
            case 24:
            return s24(character);
            case 25:
            return s25(character);
            case 26:
            return s26(character);
            case 27:
            return s27(character);
            case 28:
            return s28(character);
            case 29:
            return s29(character);
            case 30:
            return s30(character);
            case 31: 
            return s31(character);
            case 32:
            return s32(character);
            case 33:
            return s33(character);
            case 34:
            return s34(character);
            case 35:
            return s35(character);
            
   
            default:
               return 0;
        }

    }

    /**
     * @param c
     * @return
     */
    public static int s0(char c) {
        switch(c){
            case 'i':
                if (fita[token.getColuna()] == 'n'){
                    FormaToken += c;
                    return 1;
                }
            
            //case 'e'
            case '_': 
                if ((int)fita[token.getColuna()] >= 97 && (int)fita[token.getColuna()] <= 122 ){
                    FormaToken += c;
                    
                    return 11;
                }else{

                    //colocar erro numerico nao pode colocar no numero
                    return 0;

                } 

            case 'e':
                FormaToken += c; 
                return 12; 
            
             case 'l':
                FormaToken += c;
                return 19;
                
            case 'f':
                FormaToken += c;
                return 32;



            // ESPECIAIS
            case '(':
            FormaToken += c; 
            adicionaToken( FormaToken, "abre_parenteses", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

            case ')':
            FormaToken += c; 
            adicionaToken( FormaToken, "fecha_parenteses", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

            case '{':
            FormaToken += c; 
            adicionaToken( FormaToken, "abre_chave", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

            case '}':
            FormaToken += c; 
            adicionaToken( FormaToken, "fecha_chave", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

            case ';':
                FormaToken += c;
                adicionaToken( FormaToken, "fim_linha", token.getLinha(), token.getColuna());
                FormaToken = "";
                return 0;

            case '=':
                if(fita[token.getColuna()] == '='){
                    FormaToken += c; 
                    return 10;
                }else{
                    FormaToken += c; 
                    adicionaToken( FormaToken, "atribuicao", token.getLinha(), token.getColuna());
                    FormaToken = "";
                    return 0;
                }
            

            //// OPERADORES LOGICOS
            case '!':
            FormaToken += c;
            return 28;

            case ' ':
            return 0;
            
            // OPERADORES MATETICOS
            case '+':
            FormaToken += c; 
            adicionaToken( FormaToken, "soma", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

            case '-':
            FormaToken += c; 
            adicionaToken( FormaToken, "subtracao", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

            case '/':
            FormaToken += c; 
            adicionaToken( FormaToken, "divisao", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;
           
            case '*':
            FormaToken += c; 
            adicionaToken( FormaToken, "multiplicacao", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;
            
            // ler uma frase dentro da aspas 
            case '"':
            FormaToken += c;
            return 18;

            case 's':
                FormaToken += c;
                return 29;

            default:
            if((int)fita[token.getColuna()-1] >= 48 && (int)fita[token.getColuna()-1] <= 57){
                FormaToken += c;
                return 34;
            }
                return 0;
        }
           
    }
    

    public static int s1(char c) {
        switch(c){
            case 'n':
                FormaToken += c;
                return 2;

                default:
                    return 0;
        }
    }
    public static int s2(char c) {
        switch(c){
            case 'i':
                if (fita[token.getColuna()] == 'c'){
                    FormaToken += c;
                    return 3;
                }
            case 't': //t do inteiro
                FormaToken += c;
                return 6;

            default:
                return 0;
        }
    }
    public static int s3(char c) {
        switch(c){
            case 'c':
                if (fita[token.getColuna()] == 'i'){
                    FormaToken += c;
                    return 4;
                }

                default:
                    return 0;
        }
    }
    public static int s4(char c) {
        switch(c){
            case 'i':
                if (fita[token.getColuna()] == 'o'){
                    FormaToken += c;
                    return 5;
                }

                default:
                    return 0;
        }
    }
    public static int s5(char c) {
        switch(c){
            case 'o':
                if (fita[token.getColuna()] == ' '){
                    FormaToken += c;
                    adicionaToken( FormaToken, "Inicio", token.getLinha(), token.getColuna());
                    FormaToken = "";
                    return 0;
                }

                default:
                    return 0;
        }
    }
 

    public static int s6(char c) {
        switch(c){
            case 'e':
                FormaToken += c;
                return 7;

                default:
                    return 0;
        }
 
    }



    public static int s7(char c) {
        switch(c){
            case 'i':
                FormaToken += c;
                return 8;

                default:
                    return 0;
        }
    }

    public static int s8(char c) {
        switch(c){
            case 'r':
                FormaToken += c;
                return 9;

                default:
                    return 0;
        }
    }

    public static int s9(char c) {
        switch(c){
            case 'o':
                FormaToken += c;
                if (fita[token.getColuna()] == ' '){
                    adicionaToken( FormaToken, "declaracao_vars", token.getLinha(), token.getColuna());
                    FormaToken = "";
                    return 0;
                }

                default:
                    return 0;
        }
    }
    
    public static int s10(char c) {
        switch(c){
            case '=':
            FormaToken += c;
            adicionaToken( FormaToken, "igualdade", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;
            
        }

        return 0;
    }

    public static int s11(char c) {
        switch(c){
            case ' ':
            adicionaToken( FormaToken, "variavel", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

                
            case ',':
                //Token Variavel
                adicionaToken( FormaToken, "variavel", token.getLinha(), token.getColuna());
                FormaToken = "";

                // Token separador de variaveis (,)
                FormaToken += c;
                adicionaToken( FormaToken, "separador_vars", token.getLinha(), token.getColuna());
                FormaToken = "";
                return 0;

            case ';':
                // Achou  token variavel
                adicionaToken( FormaToken, "variavel", token.getLinha(), token.getColuna());
                FormaToken = "";

                FormaToken += c;
                adicionaToken( FormaToken, "fim_linha", token.getLinha(), token.getColuna());
                FormaToken = "";
                return 0;

            case ')':;
            adicionaToken( FormaToken, "variavel", token.getLinha(), token.getColuna());
            FormaToken = "";

            FormaToken += c;
            adicionaToken( FormaToken, "fecha_parenteses", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;
            
            default:
            FormaToken += c;
                return 11;
                
        }
    }
    public static int s12(char c) {
        switch(c){
            case 's':
            FormaToken += c;
                return 13;

            case 'n':
                    FormaToken += c;
                    //cria enquanto
                    return 22;
 

                default:
                    return 0;
        }
    }
    
    public static int s13(char c) {
        switch(c){
            case 'c':
            FormaToken += c;
                return 14;

                default:
                    return 0;
        }
    }
    public static int s14(char c) {
        switch(c){
            case 'r':
            FormaToken += c;
                    return 15;
            

                default:
                    return 0;
        }
    }
    public static int s15(char c) {
        switch(c){
            case 'e':
            FormaToken += c;
                return 16;

                default:
                    return 0;
        }
    }
    public static int s16(char c) {
        switch(c){
            case 'v':
            FormaToken += c;
                return 17;

                default:
                    return 0;
        }
    }
    public static int s17(char c) {
        switch(c){
            case 'a':
                FormaToken += c;
                adicionaToken( FormaToken, "escrita", token.getLinha(), token.getColuna());
                FormaToken = "";
                return 0;

            default: 
                return 0;
       
           }   
        }

     public static int s18(char c) {
       switch(c){
        case '"':
            FormaToken += c;
            adicionaToken( FormaToken, "frase", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

        default: 
            FormaToken += c;
            return 18;


       }  
     }

     public static int s19(char c) {
        switch(c){
            case 'e':
            FormaToken += c;
                return 20;

                default:
                 return 0;
        }
    }
    public static int s20(char c) {
        switch(c){
            case 'i':
            FormaToken += c;
                return 21;

                default:
                 return 0;
        }
    }
    public static int s21(char c) {
        switch(c){
            case 'a':
            FormaToken += c;
            adicionaToken( FormaToken, "leia", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

        default: 
            return 0;
        }
    }
    public static int s22(char c) {
        switch(c){
            case 'q':
            FormaToken += c;
                return 23;

                default:
                 return 0;
        }
    }
    public static int s23(char c) {
        switch(c){
            case 'u':
            FormaToken += c;
                return 24;

                default:
                 return 0;
        }
    }
    public static int s24(char c) {
        switch(c){
            case 'a':
            FormaToken += c;
                return 25;

                default:
                 return 0;
        }
    }
    public static int s25(char c) {
        switch(c){
            case 'n':
            FormaToken += c;
                return 26;

                default:
                 return 0;
        }
    }
    public static int s26(char c) {
        switch(c){
            case 't':
            FormaToken += c;
                return 27;

                default:
                 return 0;
        }
    }
    public static int s27(char c) {
        switch(c){
            case 'o':
            FormaToken += c;
            adicionaToken( FormaToken, "enquanto", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

        default: 
            return 0;
        }
    } 
    public static int s28(char c) {
        switch(c){
            case '=':
            FormaToken += c;
            adicionaToken( FormaToken, "diferenca", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

        default: 
            return 0;
        }
    } 

    public static int s29(char c) {
        switch(c){
            case 'e':
                FormaToken += c;
                if (fita[token.getColuna()] =='n' ){
                    return 30;
                }else{
                    adicionaToken( FormaToken, "se", token.getLinha(), token.getColuna());
                    FormaToken = "";
                    return 0;
                }
            default:
                 return 0;
        }
    }

    public static int s30(char c) {
        switch(c){
            case 'n':
            FormaToken += c;
                return 31;

                default:
                 return 0;
        }
    }
    public static int s31(char c) {
        switch(c){
            case 'a':
                FormaToken += c;
                return 35;

            default:
                 return 0;
        }
    }
    public static int s32(char c) {
        switch(c){
            case 'i':
            FormaToken += c;
                return 33;

                default:
                 return 0;
        }
    }
    public static int s33(char c) {
        switch(c){
            case 'm':
            FormaToken += c;
            adicionaToken( FormaToken, "fim", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

                default:
                 return 0;
        }
    }

    public static int s34(char c) { 
       switch(c){
        case ';':
            adicionaToken( FormaToken, "inteiro", token.getLinha(), token.getColuna());
            FormaToken = "";

            FormaToken += c;
            adicionaToken( FormaToken, "fim_linha", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;
        
         case ' ':
            adicionaToken( FormaToken, "inteiro", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;
            
        case ')':
            adicionaToken( FormaToken, "inteiro", token.getLinha(), token.getColuna());
            FormaToken = "";

            FormaToken += c;
            adicionaToken( FormaToken, "fecha_parenteses", token.getLinha(), token.getColuna());
            FormaToken = "";
            return 0;

        default:
            FormaToken += c;
            return 34;
           
       }
       
    }

    public static int s35(char c){
        switch (c) {
            case 'o':
                FormaToken += c;
                adicionaToken( FormaToken, "senao", token.getLinha(), token.getColuna());
                FormaToken = "";
                return 0;
        
            default:
                return 0;
        }
    }

    public static void adicionaToken(String lexema, String token, int linha, int coluna) { 
        Token tk = new Token();
        tk.setLexema(lexema);
        tk.setToken(token);
        tk.setLinha(linha);
        tk.setColuna(coluna);
        ListaToken.add(tk.imprimirTokens());
        tokens.add(tk);
        
    }

}






