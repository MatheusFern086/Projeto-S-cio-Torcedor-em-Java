package com.empresa.projeto_p2.controller;
import com.empresa.projeto_p2.model.Gold;
import com.empresa.projeto_p2.model.ListaTorcedores;
import com.empresa.projeto_p2.model.Normal;
import com.empresa.projeto_p2.model.Torcedor;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TorcedorController {
    public boolean cadastrar(int id, int ingresso,String nome, String celular, String email, String cpf, String plano){
        //Verifica qual o Plano utilizado
        if(plano.equals("Normal")){
            //Se for Normal  carrega as informações e retorna uma lista de torcedores do plano normal
            Torcedor torc = new Normal(id,nome,cpf,email,celular,plano,3);
            return ListaTorcedores.getInstance().add(torc);
        }else{
            //Se for Gold carrega as informações e retorna uma lista de torcedores do plano gold
            Torcedor torc = new Gold(id,nome,cpf,email,celular,plano,6);
            return ListaTorcedores.getInstance().add(torc);
        }
    }
    
    public List<Object> pesquisar(int id){
       boolean achou = false;
       int pos = -1;
       
       //Pesquisa o id do Torcedor informado 
        for(int i = 0; i < ListaTorcedores.getInstance().size();i++){
             if(ListaTorcedores.getInstance().get(i).getId() == id){
                achou = true;
                pos = i;
            }
        }
        
        // Se ele foi encontrado carrega as informações nas respectivas variaveis
        if(achou == true){
            String nome = ListaTorcedores.getInstance().get(pos).getNome();
            String celular = ListaTorcedores.getInstance().get(pos).getCelular();
            String email = ListaTorcedores.getInstance().get(pos).getEmail();
            String cpf = ListaTorcedores.getInstance().get(pos).getCpf();
            String plano = ListaTorcedores.getInstance().get(pos).getPlano();
            int ingressos = ListaTorcedores.getInstance().get(pos).getIngresso();
            
            //E retorna um Array List das informações encotradas
            return Arrays.asList(nome,celular,email,cpf,plano,ingressos);
        }else{
            //Senão retorna uma mensagem de erro
            JOptionPane.showMessageDialog(null,"Contato não encontrado!","Aviso",0);
            return null;
        }
    }
   
    public void preencherTabela(JTable jTabela){
        DefaultTableModel dtm = (DefaultTableModel) jTabela.getModel();
       
        dtm.setRowCount(ListaTorcedores.getInstance().size());
        jTabela.setModel(dtm);
        
        int posLinha = 0;
        
        //Pega a lista de torcedores e adicona as informações na tabela 
        for(int i = 0; i<ListaTorcedores.getInstance().size(); i++){
            jTabela.setValueAt(ListaTorcedores.getInstance().get(i).getId(), posLinha, 0);
            jTabela.setValueAt(ListaTorcedores.getInstance().get(i).getNome(), posLinha, 1);
            jTabela.setValueAt(ListaTorcedores.getInstance().get(i).getPlano(), posLinha, 2);
            jTabela.setValueAt(ListaTorcedores.getInstance().get(i).getCelular(), posLinha, 3);
            jTabela.setValueAt(ListaTorcedores.getInstance().get(i).getEmail(), posLinha, 4);
            jTabela.setValueAt(ListaTorcedores.getInstance().get(i).getIngresso(), posLinha, 5);
            
            //Incrementa mais 1 ao indice do loop
            posLinha += 1;
        }
    }
   
    public void excluir(JTable jTabela){
        
       //Começo do tratamento de exceção 
       try{
            //Verifica se o usuario selecionou algum torcedor da tabela
            if(jTabela.getSelectedRow() == -1){
                 //Caso o torcedor não foi selecionado gera uma frase de exceção
                 throw new Exception("Torcedor não Selecionado");
             }else{
                //Se foi selecionado, retorna uma mensagem de confirmação de exclusão
                 int resposta = JOptionPane.showConfirmDialog(null,"Tem Certeza que deseja excluir o contato?","Aviso!",JOptionPane.YES_NO_OPTION);

                 //Se a resposta foi sim remove o contado selcionado da tabela e do ArrayList
                 if(resposta == JOptionPane.YES_OPTION){
                     ListaTorcedores.getInstance().remove(jTabela.getSelectedRow());
                     JOptionPane.showMessageDialog(null, "Contato Excluido com sucesso!","Aviso",1);

                     //Recarrega a tabela com as informações atualizadas
                     preencherTabela(jTabela);
                 }
             }
        }
        catch(Exception ex){
            //Caso a exceção foi lançado por causa que não foi selecionado um torcedor 
            //Cai no cacth e retorna a mensagem de erro 
            JOptionPane.showMessageDialog(null, "Selecione um contato na tabela", "Aviso",0);
        }
    }
   
    public void editar(int id, int ingresso,String nome, String celular, String email, String cpf, String plano){
       //Retorna mensagem para que o usuario escolha se realmente deseja atulizar ainformações do torcedor
       int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja atualizar o contato?","Aviso",JOptionPane.YES_NO_OPTION);
       
       //Se a resposta for sim
       if(resposta == JOptionPane.YES_OPTION){
           //Compara se o plano do torcedor é Normal
           if(plano.equals("Normal")){
               //Caso seja Normal, intancia por meio de Polimorfismo um torcedor do plano Normal
               Torcedor torc = new Normal(id,nome,cpf,email,celular,plano,ingresso);
               //Chama o metodo pesquisarPessoa e informa o id para que seja pesquisado o torcedor
               int posicao = pesquisarPessoa(id);
               //Seta a posição da informação editada do torcedor
               ListaTorcedores.getInstance().set(posicao, torc);
           }else{
               //Senão ele é Gold, e é instancia do um novo Torcedor de plano Gold
               Torcedor torc = new Gold(id,nome,cpf,email,celular,plano,ingresso);
               //Chama o metodo pesquisarPessoa e informa o id para que seja pesquisado o torcedor
               int posicao = pesquisarPessoa(id);
               //Seta a posição da informação editada do torcedor
               ListaTorcedores.getInstance().set(posicao, torc);
           }
            //Retorna mensagem de sucesso ao alterar as informações do Torcedor
            JOptionPane.showMessageDialog(null,"Contato atualizado com sucesso!","Aviso",1);
        }
    }
   
   private int pesquisarPessoa(int id){
        int pos = -1;
        
        //Pesquisa as informações da pessoa conforme o id informado
        for(int i = 0; i < ListaTorcedores.getInstance().size(); i++){
            if(ListaTorcedores.getInstance().get(i).getId() == id){
                pos = i;
            }
        }
        //Retorna a posição da pessoa conforme a lista e o id informado 
        return pos;
    }
   
   public void compraIngresso(int id, int ingresso,String nome, String celular, String email, String cpf, String plano){
       //Exibe ao usuario uma Mensagem para que ele confirme se vai comprar o ingresso ou não
       int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja realizar a compra?","Aviso",JOptionPane.YES_NO_OPTION);
       
       //Se a resposta for sim
       if(resposta == JOptionPane.YES_OPTION){
           //Compara se o plano do torcedor é Normal
           if(plano.equals("Normal")){
               //Se for normal intancia um novo torcedor Normal
               Torcedor torc = new Normal(id,nome,cpf,email,celular,plano,ingresso);
               //Chama o metodo pesquisarPessoa e informa o id para que seja pesquisado o torcedor
               int posicao = pesquisarPessoa(id);
               //Seta a posição da informação editada do torcedor
               ListaTorcedores.getInstance().set(posicao, torc);
           }else{
               //Senão o torcedor é Gold, então é intanciado um novo torcedor Gold
               Torcedor torc = new Gold(id,nome,cpf,email,celular,plano,ingresso);
               //Chama o metodo pesquisarPessoa e informa o id para que seja pesquisado o torcedor
               int posicao = pesquisarPessoa(id);
               //Seta a posição da informação editada do torcedor
               ListaTorcedores.getInstance().set(posicao, torc);
           }
            //Retorna mensagem de sucesso ao alterar as informações do Torcedor
            JOptionPane.showMessageDialog(null,"Compra feita com sucesso!","Aviso",1);
        }
   }
}
