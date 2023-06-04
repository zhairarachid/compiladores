import java.util.ArrayList;
import Lexico.*;
import Sintatico.*;
import gerador.Intermediario;
import Semantico.*;

public class App{
    public static void main(String[] args) throws Exception{
        ArrayList<Token> TokenLista = new ArrayList<>();
        ArrayList<String> semanticolista = new ArrayList<>();
        boolean imprimirTokens = false;
        boolean erro = false;
        String caminho = " ";

        System.out.println("args.: "+args.length);
        for(int i = 0; i < args.length;i++){
            if(args[i].equals("-lt")){
                 imprimirTokens = true;
            }
            else{
                caminho = args[i];
                
            }
        }
        System.out.println(caminho);
        TokenLista = Lexico.main(caminho);
        
        if(!TokenLista.isEmpty() && imprimirTokens == true){

            TokenLista.forEach((tk) -> {
                System.out.println(tk.getToken());
            });
        }
        if(!TokenLista.isEmpty()){
            erro = Sintatico.main(TokenLista);
            if(!erro){
                semanticolista = semantico.Iniciar(TokenLista);
                if(!semanticolista.isEmpty()){
                    Intermediario.Iniciar(TokenLista, semanticolista); 
                }
            }
            
        }
        
    }
}