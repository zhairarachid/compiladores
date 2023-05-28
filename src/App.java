import java.util.ArrayList;
import Lexico.*;
import Sintatico.*;
import Semantico.*;

public class App{
    public static void main(String[] args) throws Exception{
        ArrayList<Token> TokenLista = new ArrayList<>();
        ArrayList<String> semanticolista = new ArrayList<>();
        TokenLista = Lexico.main();
        /* 
        if(!TokenLista.isEmpty()){
            TokenLista.forEach((tk) -> {
                System.out.println(tk.getToken());
            });
        }
        */
        Sintatico.main(TokenLista);
        semanticolista = semantico.Iniciar(TokenLista);
    }
}