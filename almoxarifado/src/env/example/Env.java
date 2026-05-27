package example;

// Environment code for project almoxarifado

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.text.ParseException;
import java.util.logging.*;

import java.util.Random;
/*1) finalizar o guardar peça grande no ambiente (remover a crença que tem peca(grd) no ambiente. 
Atividade pronta.
 
2) colocar como contexto ou condição viagens.... ou seja, um robô só pode guardar peça se tiver viagens em aberto
Atividade pronta.
  */
public class Env extends Environment {

    String sorteaPeca(){
        Random gerador = new Random();
        int sorteado = gerador.nextInt(3);
        if (sorteado == 0){
            return "peca(peq)";
        }
        if(sorteado == 1) {
            return"peca(med)";
        }
        if(sorteado == 2){
            return"peca(grd)";
        }
        return "";
    }

    int viagensr1 = 5;
    int viagensr2 = 5;


    private Logger logger = Logger.getLogger("almoxarifado."+Env.class.getName());
    
    String peca_sorteada = sorteaPeca();

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        try {
            //addPercept(ASSyntax.parseLiteral("dia(quarta)"));
            addPercept(ASSyntax.parseLiteral(peca_sorteada));
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {


        if (agName.equals("r1") && action.toString().equals("guardar(peq)") && viagensr1 > 0) { // you may improve this condition
            logger.info(agName + "está guardando peça pequena....");
            viagensr1 --;
        } else if (agName.equals("r2") && action.toString().equals("guardar(med)") && viagensr2 > 0) {
            logger.info(agName + "está guardando peça media....");
            viagensr2 --;
        } else if (agName.equals("r2") && action.toString().equals("guardar(grd)") && viagensr1 > 0 && viagensr2 > 0) {
            logger.info(agName + "está guardando peça grande....");
            viagensr1 --;
            viagensr2 --;
        }else{
            logger.info("Estou sem viagens disponiveis ");
            
        }

        try{
            removePercept(ASSyntax.parseLiteral(peca_sorteada));
            peca_sorteada = sorteaPeca();

            Thread.sleep(4000);
            logger.info("uma nova peça está sendo colocada no almoxarifado...");
            

            addPercept(ASSyntax.parseLiteral(peca_sorteada));

        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(viagensr1 == 0 && viagensr2 == 0){
                stop();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
